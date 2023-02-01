package com.eny.sensordatainterpreter.repository;

import com.eny.sensordatainterpreter.datamodel.OperationalSensorDataMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IOperationalSensorDataMessageRepository extends MongoRepository<OperationalSensorDataMessage, String> {

}
