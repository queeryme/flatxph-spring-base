package com.flatxph.base.service.query


import com.flatxph.base.ApplicationConstants
import com.flatxph.base.SpecificationProxy
import com.flatxph.base.domain.BaseEntity
import com.flatxph.base.domain.BaseEntity_
import com.flatxph.base.dto.BaseDTO
import io.github.jhipster.service.QueryService
import io.github.jhipster.service.filter.RangeFilter
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import javax.persistence.metamodel.SetAttribute
import javax.persistence.metamodel.SingularAttribute
import com.flatxph.base.criteria.BaseEntityCriteria as Criteria
import com.flatxph.base.mapper.BaseEntityMapper as Mapper
import com.flatxph.base.repository.BaseEntityRepository as Repository

abstract class BaseEntityQueryService<E : BaseEntity, C : Criteria>(val repository: Repository<E>) : QueryService<E>() {

    protected fun <OTHER> buildBaseReferringSpecification(
            filter: RangeFilter<Long>,
            reference: SetAttribute<E, OTHER>,
            valueField: SingularAttribute<in OTHER, Long>): Specification<E> {
        return buildReferringEntitySpecification<OTHER, E, Long>(filter, { root -> root.join(reference) }, { entity -> entity.get(valueField) })
    }

    fun findByCriteria(criteria: C?): List<E> = repository.findAll(createSpecification(criteria))

    fun <D : BaseDTO> findByCriteria(mapper: Mapper<D, E>, criteria: C?): List<D> = mapper.toDto(findByCriteria(criteria))

    fun findPagedByCriteria(criteria: C?, page: Pageable?): Page<E> = repository.findAll(createSpecification(criteria), page
            ?: PageRequest.of(0, ApplicationConstants.PAGE_SIZE))

    fun <D : BaseDTO> findPagedByCriteria(mapper: Mapper<D, E>, criteria: C?, page: Pageable?): Page<D> = findPagedByCriteria(criteria, page).map { mapper.toDto(it) }

    fun createSpecification(criteria: C?): Specification<E> {
        var specification = SpecificationProxy.where<E>(null)
        if (criteria != null) {
            if (criteria.id != null) specification = specification.and(buildSpecification(criteria.id!!, BaseEntity_.id))
        }
        return specification
    }

}
