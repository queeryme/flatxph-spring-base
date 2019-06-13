package com.flatxph.base.domain

import com.flatxph.base.DataObject
import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity : DataObject() {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    var id: Long? = null

    @Suppress("SuspiciousVarProperty")
    @Transient
    var objectID: Long? = null
        get() = id

    override val fieldList get() = listOf(this::id) + super.fieldList

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        val otherBase = other as BaseEntity
        return !(otherBase.id == null || id == null) && id == otherBase.id
    }

    override fun hashCode() = Objects.hashCode(id)

}
