package com.flatxph.base.criteria

import com.flatxph.base.DataObject
import io.github.jhipster.service.filter.LongFilter
import kotlin.reflect.KProperty0

abstract class BaseEntityCriteria : DataObject() {

    var id: LongFilter? = null

    override val fieldList: List<KProperty0<Any?>>
        get() = listOf(this::id) + super.fieldList
}
