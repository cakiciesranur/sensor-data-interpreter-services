package com.eny.sensordatainterpreter.repository;

import com.eny.sensordatainterpreter.datamodel.StatisticalSensorDataMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IStatisticalSensorDataMessageRepository extends MongoRepository<StatisticalSensorDataMessage, String> {

    List<StatisticalSensorDataMessage> findByDeviceIdAndEventTimeBetweenOrderByEventTime(String deviceId, LocalDateTime from, LocalDateTime to);
}
