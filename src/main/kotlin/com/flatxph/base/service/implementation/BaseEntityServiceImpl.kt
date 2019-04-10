package com.flatxph.base.service.implementation

import com.flatxph.base.domain.AuditingEntity
import com.flatxph.base.dto.AuditingDTO
import com.flatxph.base.mapper.BaseEntityMapper
import com.flatxph.base.repository.BaseEntityRepository
import com.flatxph.base.service.definition.AuditingEntityService
import org.slf4j.Logger
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
abstract class BaseEntityServiceImpl<D : AuditingDTO, E : AuditingEntity>(
        private val repository: BaseEntityRepository<E>,
        private val mapper: BaseEntityMapper<D, E>,
        private val log: Logger,
        private val entityName: String
) : AuditingEntityService<D> {

    override fun save(dto: D): D {
        log.debug("Request to save $entityName : $dto")
        var country = mapper.toEntity(dto)
        country = repository.save(country)
        return mapper.toDto(country)
    }

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Page<D> {
        log.debug("Request to get all $entityName")
        return repository.findAll(pageable).map { mapper.toDto(it) }
    }

    @Transactional(readOnly = true)
    override fun findOne(id: Long): Optional<D> {
        log.debug("Request to get $entityName : $id")
        return repository.findById(id).map { mapper.toDto(it) }
    }

    override fun delete(id: Long) {
        log.debug("Request to delete $entityName : $id")
        repository.deleteById(id)
    }

}
