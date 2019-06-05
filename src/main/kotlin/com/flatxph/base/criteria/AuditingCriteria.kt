package com.flatxph.base.criteria

import io.github.jhipster.service.filter.InstantFilter
import io.github.jhipster.service.filter.StringFilter

abstract class AuditingCriteria : BaseEntityCriteria() {

    var createdBy: StringFilter? = null

    var createdDate: InstantFilter? = null

    var lastModifiedBy: StringFilter? = null

    var lastModifiedDate: InstantFilter? = null

    override val fieldList
        get() = listOf(this::createdBy,
                this::createdDate,
                this::lastModifiedBy,
                this::lastModifiedDate) + super.fieldList

}
