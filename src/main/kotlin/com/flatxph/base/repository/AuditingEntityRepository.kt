package com.flatxph.base.repository

import com.flatxph.base.domain.AuditingEntity
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface AuditingEntityRepository<A : AuditingEntity> : BaseEntityRepository<A>
