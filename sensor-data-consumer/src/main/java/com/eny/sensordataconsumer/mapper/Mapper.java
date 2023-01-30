package com.eny.sensordataconsumer.mapper;

public interface Mapper<DTO, Entity> {

    Entity toEntity(DTO dto);
    DTO toDto(Entity dto);
}