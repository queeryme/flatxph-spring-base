package com.flatxph.base.criteria

import io.github.jhipster.service.filter.InstantFilter
import io.github.jhipster.service.filter.StringFilter
import kotlin.reflect.KMutableProperty0

abstract class AuditingCriteria : BaseEntityCriteria() {

    var createdBy: StringFilter? = null

    var createdDate: InstantFilter? = null

    var lastModifiedBy: StringFilter? = null

    var lastModifiedDate: InstantFilter? = null

    override val fieldList: List<KMutableProperty0<out Any?>>
        get() = listOf(this::createdBy,
                this::createdDate,
                this::lastModifiedBy,
                this::lastModifiedDate) + super.fieldList

}
