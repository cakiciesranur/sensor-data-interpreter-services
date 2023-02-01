package com.eny.sensordatainterpreter.mapper;

import com.eny.sensordatainterpreter.datamodel.OperationalSensorDataMessage;
import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;

@org.mapstruct.Mapper(componentModel = "spring")
public interface OperationalSensorDataMessageMapper extends Mapper<SensorDataMessage, OperationalSensorDataMessage> {

    OperationalSensorDataMessage toEntity(SensorDataMessage dto);

    SensorDataMessage toDto(OperationalSensorDataMessage entity);

}
