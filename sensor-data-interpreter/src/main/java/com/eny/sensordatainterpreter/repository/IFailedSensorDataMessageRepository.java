package com.eny.sensordatainterpreter.repository;

import com.eny.sensordatainterpreter.datamodel.FailedSensorDataMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFailedSensorDataMessageRepository extends MongoRepository<FailedSensorDataMessage, String> {

}
