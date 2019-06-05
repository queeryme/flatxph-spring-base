package com.flatxph.base.service.definition

import com.flatxph.base.domain.BaseEntity
import com.flatxph.base.dto.BaseDTO
import com.flatxph.base.mapper.BaseEntityMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface ReadEntityService<E: BaseEntity> {

    fun <D : BaseDTO> findAll(pageable: Pageable, mapper: BaseEntityMapper<D, E>): Page<D>

    fun <D : BaseDTO> findOne(id: Long, mapper: BaseEntityMapper<D, E>): Optional<D>

    fun findAll(pageable: Pageable): Page<E>

    fun findOne(id: Long): Optional<E>
}
