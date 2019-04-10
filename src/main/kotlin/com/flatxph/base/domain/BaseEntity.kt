package com.flatxph.address.domain

import com.flatxph.base.DataObject
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity : DataObject() {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    open var id: Long? = null

    override val fieldsString: String
        get() = "id='$id'"

}
