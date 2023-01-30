package com.eny.sensordataconsumer.converter;

import com.eny.sensordataconsumer.payload.SensorDataMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SensorDataMessageMapper extends Converter<SensorDataMessage, com.eny.sensordataconsumer.datamodel.SensorDataMessage> {

    @Mapping(target = "messageId", source = "dto.id")
    com.eny.sensordataconsumer.datamodel.SensorDataMessage toEntity(SensorDataMessage dto);
}
