package com.eny.sensordatainterpreter.repository;

import com.eny.sensordatainterpreter.datamodel.ComputedSensorMessageData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IComputedSensorDataMessageRepository extends MongoRepository<ComputedSensorMessageData, String> {
}
