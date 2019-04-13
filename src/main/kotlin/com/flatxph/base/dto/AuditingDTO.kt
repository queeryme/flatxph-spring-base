package com.flatxph.base.dto

import org.springframework.data.annotation.ReadOnlyProperty
import java.time.Instant
import kotlin.reflect.KMutableProperty0

abstract class AuditingDTO : BaseDTO() {

    @ReadOnlyProperty
    var createdBy: String? = null

    @ReadOnlyProperty
    var createdDate: Instant? = Instant.now()

    var lastModifiedBy: String? = null

    var lastModifiedDate: Instant? = Instant.now()

    override val fieldList: List<KMutableProperty0<out Any?>>
        get() = listOf(this::createdBy, this::createdDate, this::lastModifiedBy, this::lastModifiedDate) + super.fieldList

}
