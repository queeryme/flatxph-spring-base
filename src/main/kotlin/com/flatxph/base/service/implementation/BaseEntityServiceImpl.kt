package com.flatxph.base.service.implementation

import com.flatxph.base.domain.AuditingEntity
import com.flatxph.base.dto.BaseDTO
import com.flatxph.base.mapper.BaseEntityMapper
import com.flatxph.base.repository.BaseEntityRepository
import com.flatxph.base.service.definition.AuditingEntityService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
abstract class BaseEntityServiceImpl<E : AuditingEntity>(
        protected val repository: BaseEntityRepository<E>
) : AuditingEntityService<E> {

    override fun <D : BaseDTO> save(dto: D, mapper: BaseEntityMapper<D, E>) = mapper.toDto(save(mapper.toEntity(dto)))

    override fun save(entity: E): E = repository.save(entity)

    @Transactional(readOnly = true)
    override fun <D : BaseDTO> findAll(pageable: Pageable, mapper: BaseEntityMapper<D, E>): Page<D> = repository.findAll(pageable).map { mapper.toDto(it) }

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Page<E> = repository.findAll(pageable)

    @Transactional(readOnly = true)
    override fun <D : BaseDTO> findOne(id: Long, mapper: BaseEntityMapper<D, E>): Optional<D> = findOne(id).map { mapper.toDto(it) }

    override fun findOne(id: Long): Optional<E> = repository.findById(id)

    override fun delete(id: Long) = repository.deleteById(id)

}
