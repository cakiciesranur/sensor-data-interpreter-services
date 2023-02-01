package com.eny.sensordatainterpreter.service;

import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;
import org.springframework.kafka.support.Acknowledgment;

public interface ISensorDataMessageConsumer {
    void consumeMessage(SensorDataMessage payload, String receivedtopic,
                        Long partitionId, Long offset, String groupId, Acknowledgment acknowledgment);
    void processMessage(SensorDataMessage message);
}
