package com.eny.sensordataconsumer.service;

import com.eny.sensordataconsumer.payload.SensorDataMessage;
import org.springframework.kafka.support.Acknowledgment;

public interface ISensorDataConsumer {
    void consumeMessage(SensorDataMessage payload, Long partitionId, Long offset, String groupId,
                        Acknowledgment acknowledgment);
    void processMessage(SensorDataMessage message);
}
