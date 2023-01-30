package com.eny.sensordataconsumer.repository;

import com.eny.sensordataconsumer.datamodel.SensorDataMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ISensorDataMessageRepository extends MongoRepository<SensorDataMessage, String> {
}
