package com.flatxph.base.feign.domain

import com.flatxph.base.DataObject
import java.util.*

abstract class BaseFeignEntity : DataObject() {
    var id: Long? = null

    @Suppress("SuspiciousVarProperty")
    var objectID: Long? = null
        get() = id

    override val fieldList get() = listOf(this::id) + super.fieldList

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        val otherBase = other as BaseFeignEntity
        return !(otherBase.id == null || id == null) && id == otherBase.id
    }

    override fun hashCode() = Objects.hashCode(id)

}
