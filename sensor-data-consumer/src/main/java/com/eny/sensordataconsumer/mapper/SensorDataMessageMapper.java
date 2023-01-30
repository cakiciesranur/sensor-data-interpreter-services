package com.eny.sensordataconsumer.mapper;

import com.eny.sensordataconsumer.datamodel.SensorStatisticalMessage;
import com.eny.sensordataconsumer.payload.request.SensorDataMessage;

@org.mapstruct.Mapper(componentModel = "spring")
public interface SensorDataMessageMapper extends Mapper<SensorDataMessage, SensorStatisticalMessage> {

    SensorStatisticalMessage toEntity(SensorDataMessage dto);
    SensorDataMessage toDto(SensorStatisticalMessage entity);

}
