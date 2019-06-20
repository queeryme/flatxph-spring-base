package com.flatxph.base.feign.domain

import java.time.Instant

/**
 * Base abstract class for entities which will hold definitions for created, last modified by and created,
 * last modified by date.
 */
abstract class AuditingFeignEntity : BaseFeignEntity() {
    var createdBy: String? = null
    var createdDate: Instant? = Instant.now()
    var lastModifiedBy: String? = null
    var lastModifiedDate: Instant? = Instant.now()
}
