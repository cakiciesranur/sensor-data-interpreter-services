package com.eny.sensordataconsumer.converter;

public interface Converter<DTO, Entity> {

    Entity toEntity(DTO dto);
}