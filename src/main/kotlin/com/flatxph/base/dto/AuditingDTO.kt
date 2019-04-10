package com.flatxph.address.dto

import org.springframework.data.annotation.ReadOnlyProperty
import java.time.Instant

abstract class AuditingDTO : BaseDTO() {

    @ReadOnlyProperty
    var createdBy: String? = null

    @ReadOnlyProperty
    var createdDate: Instant? = Instant.now()

    var lastModifiedBy: String? = null

    var lastModifiedDate: Instant? = Instant.now()

    override val fieldsString: String
        get() = "createdBy='$createdBy', createdDate='$createdDate', lastModifiedBy='$lastModifiedBy', " +
                "lastModifiedDate='$lastModifiedDate'"

}
