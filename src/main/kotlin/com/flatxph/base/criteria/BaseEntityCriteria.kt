package com.flatxph.base.criteria

import com.flatxph.base.DataObject
import io.github.jhipster.service.filter.LongFilter
import kotlin.reflect.KMutableProperty0

abstract class BaseEntityCriteria : DataObject() {

    var id: LongFilter? = null

    override val fieldList: List<KMutableProperty0<out Any?>>
        get() = listOf(this::id) + super.fieldList
}
