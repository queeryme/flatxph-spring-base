package com.flatxph.base.service.query


import com.flatxph.base.SpecificationProxy
import com.flatxph.base.criteria.AuditingCriteria
import com.flatxph.base.domain.AuditingEntity
import com.flatxph.base.domain.AuditingEntity_
import org.slf4j.Logger
import org.springframework.data.jpa.domain.Specification
import org.springframework.transaction.annotation.Transactional
import com.flatxph.base.repository.BaseEntityRepository as Repository

@Transactional(readOnly = true)
abstract class AuditingEntityQueryService<E : AuditingEntity, C : AuditingCriteria>(
        repository: Repository<E>,
        log: Logger
) : BaseEntityQueryService<E, C>(repository, log) {

    override fun createSpecification(criteria: C?): Specification<E> {
        var specification = SpecificationProxy.where<E>(null)
        if (criteria != null) {
            if (criteria.createdBy != null) {
                specification = specification.and(buildStringSpecification(criteria.createdBy!!, AuditingEntity_.createdBy))
            }
            if (criteria.lastModifiedBy != null) {
                specification = specification.and(buildStringSpecification(criteria.lastModifiedBy!!, AuditingEntity_.lastModifiedBy))
            }
            if (criteria.createdDate != null) {
                specification = specification.and(buildRangeSpecification(criteria.createdDate!!, AuditingEntity_.createdDate))
            }
            if (criteria.lastModifiedDate != null) {
                specification = specification.and(buildRangeSpecification(criteria.lastModifiedDate!!, AuditingEntity_.lastModifiedDate))
            }
            specification = specification.and(super.createSpecification(criteria))
        }
        return specification
    }

}
