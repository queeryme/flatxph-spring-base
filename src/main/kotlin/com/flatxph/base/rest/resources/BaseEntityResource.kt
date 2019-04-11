package com.flatxph.base.rest.resources

import com.flatxph.base.rest.errors.BadRequestAlertException
import com.flatxph.base.rest.util.HeaderUtil
import com.flatxph.base.rest.util.PaginationUtil
import com.flatxph.base.criteria.BaseEntityCriteria
import com.flatxph.base.domain.BaseEntity
import com.flatxph.base.dto.BaseDTO
import com.flatxph.base.service.definition.BaseEntityService
import com.flatxph.base.service.query.BaseEntityQueryService
import io.github.jhipster.web.util.ResponseUtil
import io.micrometer.core.annotation.Timed
import org.slf4j.Logger
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.net.URISyntaxException
import javax.validation.Valid

abstract class BaseEntityResource<E : BaseEntity, D : BaseDTO, C : BaseEntityCriteria>(
        val service: BaseEntityService<D>,

        @Suppress("MemberVisibilityCanBePrivate")
        val queryService: BaseEntityQueryService<E, D, C>,

        @Suppress("MemberVisibilityCanBePrivate")
        val log: Logger,

        @Suppress("MemberVisibilityCanBePrivate")
        val entityName: String
) {

    @GetMapping
    @Timed
    protected open fun readAll(criteria: C, pageable: Pageable): ResponseEntity<List<D>> {
        log.debug("REST request to get $entityName by criteria: $criteria, $pageable")
        val page = queryService.findByCriteria(criteria, pageable)
        val headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/$entityName")
        return ResponseEntity(page.content, headers, HttpStatus.OK)
    }

    @GetMapping("{id}")
    @Timed
    protected open fun readOne(@PathVariable id: Long): ResponseEntity<D> {
        log.debug("REST request to get $entityName : $id")
        val dto = service.findOne(id)
        return ResponseUtil.wrapOrNotFound(dto)
    }

    @PostMapping
    @Timed
    @Throws(URISyntaxException::class)
    protected open fun create(@Valid @RequestBody dto: D): ResponseEntity<D> {
        log.debug("REST request to save $entityName : $dto")
        if (dto.id != null) {
            throw BadRequestAlertException("A new $entityName cannot already have an ID", entityName, "idExists")
        }
        val result = service.save(dto)
        return ResponseEntity.created(URI("/api/$entityName/" + result.id!!))
                .headers(HeaderUtil.createEntityCreationAlert(entityName, result.id!!.toString()))
                .body(result)
    }

    @PutMapping
    @Timed
    @Throws(URISyntaxException::class)
    protected open fun update(@Valid @RequestBody dto: D): ResponseEntity<D> {
        log.debug("REST request to update $entityName : $dto")
        if (dto.id == null) {
            throw BadRequestAlertException("Invalid id", entityName, "idNull")
        }
        val result = service.save(dto)
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(entityName, dto.id!!.toString()))
                .body(result)
    }

    @DeleteMapping("{id}")
    @Timed
    protected open fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        log.debug("REST request to delete $entityName : $id")
        service.delete(id)
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(entityName, id.toString())).build()
    }
}
