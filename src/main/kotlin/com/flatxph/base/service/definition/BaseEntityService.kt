package com.flatxph.base.service.definition

import com.flatxph.base.dto.BaseDTO

interface BaseEntityService<D : BaseDTO> : ReadEntityService<D> {

    fun save(dto: D): D

    fun delete(id: Long)

}
