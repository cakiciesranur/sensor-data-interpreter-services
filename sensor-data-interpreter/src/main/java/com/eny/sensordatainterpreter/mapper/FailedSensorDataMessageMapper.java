package com.eny.sensordatainterpreter.mapper;

import com.eny.sensordatainterpreter.datamodel.FailedSensorDataMessage;
import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;

@org.mapstruct.Mapper(componentModel = "spring")
public interface FailedSensorDataMessageMapper extends Mapper<SensorDataMessage, FailedSensorDataMessage> {

    FailedSensorDataMessage toEntity(SensorDataMessage dto);

}
