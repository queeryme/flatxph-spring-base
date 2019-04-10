package com.flatxph.base.repository

import com.flatxph.base.domain.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseEntityRepository<E : BaseEntity> : JpaRepository<E, Long>, JpaSpecificationExecutor<E>
