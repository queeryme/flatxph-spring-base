package com.flatxph.base.rest.resources

import com.flatxph.base.criteria.BaseEntityCriteria
import com.flatxph.base.domain.BaseEntity
import com.flatxph.base.dto.BaseDTO
import com.flatxph.base.rest.errors.BadRequestAlertException
import com.flatxph.base.rest.util.HeaderUtil
import com.flatxph.base.rest.util.PaginationUtil
import com.flatxph.base.service.definition.ReadEntityService
import com.flatxph.base.service.query.BaseEntityQueryService
import io.github.jhipster.web.util.ResponseUtil
import org.slf4j.Logger
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.net.URI
import com.flatxph.base.mapper.BaseEntityMapper as Mapper
import com.flatxph.base.service.definition.BaseEntityService as Service

fun <E : BaseEntity, D : BaseDTO, C : BaseEntityCriteria> readPagedMixin(
        queryService: BaseEntityQueryService<E, C>,
        entityName: String,
        log: Logger,
        mapper: Mapper<D, E>,
        criteria: C?,
        pageable: Pageable?
): ResponseEntity<List<D>> {
    log.debug("REST request to get $entityName by criteria: $criteria, $pageable")
    val page = queryService.findPagedByCriteria(mapper, criteria, pageable)
    val headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/$entityName")
    return ResponseEntity(page.content, headers, HttpStatus.OK)
}

fun <E : BaseEntity, D : BaseDTO, C : BaseEntityCriteria> readAllMixin(
        queryService: BaseEntityQueryService<E, C>,
        entityName: String,
        log: Logger,
        mapper: Mapper<D, E>,
        criteria: C?
): ResponseEntity<List<D>> {
    log.debug("REST request to get $entityName by criteria: $criteria")
    val list = queryService.findByCriteria(mapper, criteria)
    return ResponseEntity(list, HttpStatus.OK)
}

fun <E : BaseEntity, D: BaseDTO> readOneMixin(
        service: ReadEntityService<E>,
        entityName: String,
        log: Logger,
        id: Long,
        mapper: Mapper<D, E>
): ResponseEntity<D> {
    log.debug("REST request to get $entityName : $id")
    val dto = service.findOne(id, mapper)
    return ResponseUtil.wrapOrNotFound(dto)
}

fun <E : BaseEntity, D: BaseDTO> createMixin(
        service: Service<E>,
        entityName: String,
        log: Logger,
        dto: D,
        mapper: Mapper<D, E>
): ResponseEntity<D> {
    log.debug("REST request to save $entityName : $dto")
    if (dto.id != null) {
        throw BadRequestAlertException("A new $entityName cannot already have an ID", entityName, "idExists")
    }
    val result = service.save(dto, mapper)
    return ResponseEntity.created(URI("/api/$entityName/" + result.id!!))
            .headers(HeaderUtil.createEntityCreationAlert(entityName, result.id!!.toString()))
            .body(result)
}

fun <E : BaseEntity, D: BaseDTO> updateMixin(
        service: Service<E>,
        entityName: String,
        log: Logger,
        dto: D,
        mapper: Mapper<D, E>
): ResponseEntity<D> {
    log.debug("REST request to update $entityName : $dto")
    if (dto.id == null) {
        throw BadRequestAlertException("Invalid id", entityName, "idNull")
    }
    val result = service.save(dto, mapper)
    return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(entityName, dto.id!!.toString()))
            .body(result)
}

fun <E : BaseEntity> deleteMixin(
        service: Service<E>,
        entityName: String,
        log: Logger,
        id: Long
): ResponseEntity<Void> {
    log.debug("REST request to delete $entityName : $id")
    service.delete(id)
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(entityName, id.toString())).build()
}
