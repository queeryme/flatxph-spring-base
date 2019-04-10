package com.flatxph.base.criteria

import com.flatxph.base.empty
import io.github.jhipster.service.filter.InstantFilter
import io.github.jhipster.service.filter.StringFilter

abstract class AuditingCriteria : BaseEntityCriteria() {

    var createdBy: StringFilter? = null

    var createdDate: InstantFilter? = null

    var lastModifiedBy: StringFilter? = null

    var lastModifiedDate: InstantFilter? = null

    override val fieldsString: String
        get() = empty(this::createdBy) +
                empty(this::createdDate) +
                empty(this::lastModifiedBy) +
                empty(this::lastModifiedDate)

}
