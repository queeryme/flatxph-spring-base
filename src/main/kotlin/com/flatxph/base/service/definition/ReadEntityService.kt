package com.flatxph.base.service.definition

import com.flatxph.base.dto.BaseDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface ReadEntityService<D : BaseDTO> {

    fun findAll(pageable: Pageable): Page<D>

    fun findOne(id: Long): Optional<D>

}
