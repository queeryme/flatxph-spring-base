package com.flatxph.base.mapper

import com.flatxph.base.domain.AuditingEntity
import com.flatxph.base.dto.AuditingDTO
import org.mapstruct.Mapping
import org.mapstruct.Mappings

interface AuditingEntityMapper<D : AuditingDTO, E : AuditingEntity> : BaseEntityMapper<D, E> {

    @Mappings(
            Mapping(target = "createdBy", ignore = true),
            Mapping(target = "createdDate", ignore = true),
            Mapping(target = "lastModifiedBy", ignore = true),
            Mapping(target = "lastModifiedDate", ignore = true)
    )
    override fun toEntity(dto: D): E

    override fun toDto(entity: E): D

}
