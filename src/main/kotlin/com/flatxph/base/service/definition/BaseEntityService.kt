package com.flatxph.base.service.definition

import com.flatxph.base.domain.BaseEntity
import com.flatxph.base.dto.BaseDTO
import com.flatxph.base.mapper.BaseEntityMapper

interface BaseEntityService<E : BaseEntity> : ReadEntityService<E> {

    fun <D : BaseDTO> save(dto: D, mapper: BaseEntityMapper<D, E>): D

    fun save(entity: E): E

    fun delete(id: Long)

}
