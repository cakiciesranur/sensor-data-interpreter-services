package com.eny.sensordatainterpreter.mapper;

import com.eny.sensordatainterpreter.datamodel.SensorStatisticalMessage;
import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;

@org.mapstruct.Mapper(componentModel = "spring")
public interface SensorDataMessageMapper extends Mapper<SensorDataMessage, SensorStatisticalMessage> {

    SensorStatisticalMessage toEntity(SensorDataMessage dto);

    SensorDataMessage toDto(SensorStatisticalMessage entity);

}
