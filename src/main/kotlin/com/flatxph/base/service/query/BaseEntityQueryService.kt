package com.flatxph.base.service.query


import com.flatxph.base.SpecificationProxy
import com.flatxph.base.criteria.BaseEntityCriteria
import com.flatxph.base.domain.BaseEntity
import com.flatxph.base.domain.BaseEntity_
import com.flatxph.base.dto.BaseDTO
import io.github.jhipster.service.QueryService
import org.slf4j.Logger
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import com.flatxph.base.mapper.BaseEntityMapper as Mapper
import com.flatxph.base.repository.BaseEntityRepository as Repository

abstract class BaseEntityQueryService<E : BaseEntity, C : BaseEntityCriteria>(
        val repository: Repository<E>,
        val log: Logger
) : QueryService<E>() {

    fun findByCriteria(criteria: C?): List<E> {
        log.debug("${this::class.simpleName} ${this::class.java.enclosingMethod}: $criteria")
        return repository.findAll(createSpecification(criteria))
    }

    fun findByCriteria(criteria: C?, page: Pageable): Page<E> {
        log.debug("${this::class.simpleName} ${this::class.java.enclosingMethod}: $criteria, $page")
        return repository.findAll(createSpecification(criteria), page)
    }

    fun <D : BaseDTO> findByCriteria(mapper: Mapper<D, E>, criteria: C) = mapper.toDto(findByCriteria(criteria))

    fun <D : BaseDTO> findByCriteria(mapper: Mapper<D, E>, criteria: C, page: Pageable) = findByCriteria(criteria, page).map { mapper.toDto(it) }

    fun createSpecification(criteria: C?): Specification<E> {
        var specification = SpecificationProxy.where<E>(null)
        if (criteria != null) {
            if (criteria.id != null) specification = specification.and(buildSpecification(criteria.id!!, BaseEntity_.id))
        }
        return specification
    }

}
