package com.flatxph.address.domain

import org.hibernate.envers.Audited
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.Column
import javax.persistence.MappedSuperclass

/**
 * Base abstract class for entities which will hold definitions for created, last modified by and created,
 * last modified by date.
 */
@MappedSuperclass
@Audited
abstract class AuditingEntity : BaseEntity() {

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    var createdBy: String? = null

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    var createdDate: Instant? = Instant.now()

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    var lastModifiedBy: String? = null

    @LastModifiedDate
    @Column(name = "last_modified_date")
    var lastModifiedDate: Instant? = Instant.now()

    override val fieldsString: String
        get() = "createdBy='$createdBy', createdDate='$createdDate', lastModifiedBy='$lastModifiedBy', " +
                "lastModifiedDate='$lastModifiedDate'"

}