package com.eny.sensordatainterpreter.repository;

import com.eny.sensordatainterpreter.datamodel.ComputedSensorDataMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IComputedSensorDataMessageRepository extends MongoRepository<ComputedSensorDataMessage, String> {
}
