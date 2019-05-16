package com.flatxph.base.criteria

import io.github.jhipster.service.filter.StringFilter
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Join
import javax.persistence.criteria.Predicate
import javax.persistence.metamodel.SingularAttribute

class JoinBuilder<X, Y>(
        private val join: Join<X, Y>,
        private val cb: CriteriaBuilder
) {

    fun stringFilterBuilder(stringFilter: StringFilter, attribute: SingularAttribute<Y, String>): List<Predicate> {
        val predicates = ArrayList<Predicate>()
        if (stringFilter.contains != null) {
            val predicate = cb.like(
                    cb.lower(join.get(attribute)),
                    "%" + stringFilter.contains.toLowerCase() + "%"
            )
            predicates.add(predicate)
        }
        val isSpecified = stringFilter.specified
        if (isSpecified != null) {
            val predicate = cb.isNotNull(join.get(attribute))
            if (!isSpecified) {
                predicate.not()
            }
            predicates.add(predicate)
        }

        val inList = stringFilter.getIn()
        if (inList != null) {
            val predicate = cb.`in`(join.get(attribute))
            predicate.`in`(inList)
            predicates.add(predicate)
        }

        val stringValue = stringFilter.equals
        if (stringValue != null) {
            val predicate = cb.equal(join.get(attribute), stringValue)
            predicates.add(predicate)
        }
        return predicates
    }

}
