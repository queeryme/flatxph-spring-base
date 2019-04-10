package com.flatxph.base.criteria

import com.flatxph.base.DataObject
import com.flatxph.base.empty
import io.github.jhipster.service.filter.LongFilter

abstract class BaseEntityCriteria : DataObject() {

    var id: LongFilter? = null

    override val fieldsString: String
        get() = empty(this::id)
}
