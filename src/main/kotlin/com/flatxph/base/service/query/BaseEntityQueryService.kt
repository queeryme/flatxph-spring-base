package com.flatxph.base.service.query


import com.flatxph.base.SpecificationProxy
import com.flatxph.base.criteria.BaseEntityCriteria
import com.flatxph.base.domain.BaseEntity
import com.flatxph.base.domain.BaseEntity_
import com.flatxph.base.dto.BaseDTO
import com.flatxph.base.mapper.BaseEntityMapper
import com.flatxph.base.repository.BaseEntityRepository
import io.github.jhipster.service.QueryService
import org.slf4j.Logger
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.transaction.annotation.Transactional

abstract class BaseEntityQueryService<E : BaseEntity, D : BaseDTO, C : BaseEntityCriteria>(
        val repository: BaseEntityRepository<E>,
        val mapper: BaseEntityMapper<D, E>,
        val log: Logger
) : QueryService<E>() {

    @Suppress("RedundantModalityModifier")
    @Transactional(readOnly = true)
    open fun findByCriteria(criteria: C): List<D> {
        log.debug("find by criteria : $criteria")
        val specification = createSpecification(criteria)
        return mapper.toDto(repository.findAll(specification))
    }

    @Suppress("RedundantModalityModifier")
    @Transactional(readOnly = true)
    open fun findByCriteria(criteria: C, page: Pageable): Page<D> {
        log.debug("find by criteria : $criteria, page: $page")
        val specification = createSpecification(criteria)
        val result = repository.findAll(specification, page)
        return result.map { mapper.toDto(it) }
    }

    fun createSpecification(criteria: C?): Specification<E> {
        var specification = SpecificationProxy.where<E>(null)
        if (criteria != null) {
            if (criteria.id != null) {
                specification = specification.and(buildSpecification(criteria.id!!, BaseEntity_.id))
            }
        }
        return specification
    }

}
