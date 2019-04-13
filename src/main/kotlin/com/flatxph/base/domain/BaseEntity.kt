package com.flatxph.base.domain

import com.flatxph.base.DataObject
import java.util.*
import javax.persistence.*
import kotlin.reflect.KProperty0

@MappedSuperclass
abstract class BaseEntity : DataObject() {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    open var id: Long? = null

    override val fieldList: List<KProperty0<Any?>>
        get() = listOf(this::id) + super.fieldList

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || this::class != other::class) {
            return false
        }

        val otherBase = other as BaseEntity
        return !(otherBase.id == null || id == null) && id == otherBase.id
    }

    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }

}
