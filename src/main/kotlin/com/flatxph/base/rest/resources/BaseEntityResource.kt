package com.flatxph.base.rest.resources

import com.flatxph.base.criteria.BaseEntityCriteria
import com.flatxph.base.domain.BaseEntity
import com.flatxph.base.dto.BaseDTO
import com.flatxph.base.rest.errors.BadRequestAlertException
import com.flatxph.base.rest.util.HeaderUtil
import com.flatxph.base.rest.util.PaginationUtil
import com.flatxph.base.service.definition.BaseEntityService
import com.flatxph.base.service.query.BaseEntityQueryService
import io.github.jhipster.web.util.ResponseUtil
import org.slf4j.Logger
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.net.URI

fun <E : BaseEntity, D : BaseDTO, C : BaseEntityCriteria> readAllMixin(
        queryService: BaseEntityQueryService<E, D, C>,
        entityName: String,
        log: Logger,
        criteria: C,
        pageable: Pageable
): ResponseEntity<List<D>> {
    log.debug("REST request to get $entityName by criteria: $criteria, $pageable")
    val page = queryService.findByCriteria(criteria, pageable)
    val headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/$entityName")
    return ResponseEntity(page.content, headers, HttpStatus.OK)
}

fun <D : BaseDTO> readOneMixin(
        service: BaseEntityService<D>,
        entityName: String,
        log: Logger,
        id: Long
): ResponseEntity<D> {
    log.debug("REST request to get $entityName : $id")
    val dto = service.findOne(id)
    return ResponseUtil.wrapOrNotFound(dto)
}

fun <D : BaseDTO> createMixin(
        service: BaseEntityService<D>,
        entityName: String,
        log: Logger,
        dto: D
): ResponseEntity<D> {
    log.debug("REST request to save $entityName : $dto")
    if (dto.id != null) {
        throw BadRequestAlertException("A new $entityName cannot already have an ID", entityName, "idExists")
    }
    val result = service.save(dto)
    return ResponseEntity.created(URI("/api/$entityName/" + result.id!!))
            .headers(HeaderUtil.createEntityCreationAlert(entityName, result.id!!.toString()))
            .body(result)
}

fun <D : BaseDTO> updateMixin(
        service: BaseEntityService<D>,
        entityName: String,
        log: Logger,
        dto: D
): ResponseEntity<D> {
    log.debug("REST request to update $entityName : $dto")
    if (dto.id == null) {
        throw BadRequestAlertException("Invalid id", entityName, "idNull")
    }
    val result = service.save(dto)
    return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(entityName, dto.id!!.toString()))
            .body(result)
}

fun <D : BaseDTO> deleteMixin(
        service: BaseEntityService<D>,
        entityName: String,
        log: Logger,
        id: Long
): ResponseEntity<Void> {
    log.debug("REST request to delete $entityName : $id")
    service.delete(id)
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(entityName, id.toString())).build()
}
