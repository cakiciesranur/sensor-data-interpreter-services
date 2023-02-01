package com.eny.sensordatainterpreter.mapper;

import com.eny.sensordatainterpreter.datamodel.StatisticalSensorDataMessage;
import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;

@org.mapstruct.Mapper(componentModel = "spring")
public interface StatisticalSensorDataMessageMapper extends Mapper<SensorDataMessage, StatisticalSensorDataMessage> {

    StatisticalSensorDataMessage toEntity(SensorDataMessage dto);

    SensorDataMessage toDto(StatisticalSensorDataMessage entity);

}
