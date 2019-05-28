package com.flatxph.base.domain

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
    @Column(nullable = false, length = 50, updatable = false)
    var createdBy: String? = null

    @CreatedDate
    @Column(nullable = false)
    var createdDate: Instant? = Instant.now()

    @LastModifiedBy
    @Column(length = 50)
    var lastModifiedBy: String? = null

    @LastModifiedDate
    @Column
    var lastModifiedDate: Instant? = Instant.now()

    override val fieldList get() = listOf(this::createdBy, this::createdDate, this::lastModifiedBy, this::lastModifiedDate) + super.fieldList

}
