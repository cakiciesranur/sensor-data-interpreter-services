package com.eny.sensordatainterpreter.mapper;

public interface Mapper<DTO, Entity> {

    Entity toEntity(DTO dto);
    DTO toDto(Entity dto);
}