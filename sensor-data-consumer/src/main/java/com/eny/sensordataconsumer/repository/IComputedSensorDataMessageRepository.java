package com.eny.sensordataconsumer.repository;

import com.eny.sensordataconsumer.datamodel.ComputedSensorMessageData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IComputedSensorDataMessageRepository extends MongoRepository<ComputedSensorMessageData, String> {
}
