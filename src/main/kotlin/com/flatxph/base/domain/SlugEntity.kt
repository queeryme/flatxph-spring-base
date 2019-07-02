package com.flatxph.base.domain

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class SlugEntity: AuditingEntity() {

    @Column
    var slug: String? = null

    @Column
    var metaTitle: String? = null

    @Column
    var metaDescription: String? = null

    @Column
    var metaKeywords: String? = null
}
