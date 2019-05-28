package com.flatxph.base.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.ReadOnlyProperty
import java.time.Instant

abstract class AuditingDTO : BaseDTO() {

    @ReadOnlyProperty
    var createdBy: String? = null

    @ReadOnlyProperty
    var createdDate: Instant? = Instant.now()

    var lastModifiedBy: String? = null

    var lastModifiedDate: Instant? = Instant.now()

    override val fieldList
        @JsonIgnore
        get() = listOf(this::createdBy, this::createdDate, this::lastModifiedBy, this::lastModifiedDate) + super.fieldList

}
