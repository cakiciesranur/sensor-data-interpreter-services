package com.eny.sensordatainterpreter.repository;

import com.eny.sensordatainterpreter.datamodel.SensorStatisticalMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ISensorStatisticalDataMessageRepository extends MongoRepository<SensorStatisticalMessage, String> {

    List<SensorStatisticalMessage> findByDeviceIdAndEventTimeBetweenOrderByEventTime(String deviceId, Date from, Date to);
}
