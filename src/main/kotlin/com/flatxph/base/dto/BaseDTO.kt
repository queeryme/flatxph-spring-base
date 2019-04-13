package com.flatxph.base.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.flatxph.base.DataObject
import java.util.*
import kotlin.reflect.KProperty0


abstract class BaseDTO : DataObject() {
    var id: Long? = null

    override val fieldList: List<KProperty0<Any?>>
        @JsonIgnore
        get() = listOf(this::id) + super.fieldList

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || this::class != other::class) {
            return false
        }

        val otherBase = other as BaseDTO
        return !(otherBase.id == null || id == null) && id == otherBase.id
    }

    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }

}
