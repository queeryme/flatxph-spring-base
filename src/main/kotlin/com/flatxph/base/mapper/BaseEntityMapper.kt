package com.flatxph.base.mapper

import com.flatxph.base.domain.BaseEntity
import com.flatxph.base.dto.BaseDTO

interface BaseEntityMapper<D : BaseDTO, E : BaseEntity> {

    fun toEntity(dto: D): E

    fun toDto(entity: E): D

    fun toEntity(dtoList: List<D>): List<E>

    fun toDto(entityList: List<E>): List<D>
}
