package com.eny.sensordataconsumer.service.impl;

import com.eny.sensordataconsumer.payload.SensorDataMessage;
import com.eny.sensordataconsumer.service.ISensorDataConsumer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorOperationalDataConsumer implements ISensorDataConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensorOperationalDataConsumer.class);

    @KafkaListener(id = "operational-listener-id", topics = "${spring.kafka.consumer.topic}",
            groupId = "group-operational", containerFactory = "operationalKafkaListenerContainer")
    @Override
    public void consumeMessage(@Payload SensorDataMessage message,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Long partitionId,
                               @Header(KafkaHeaders.OFFSET) Long offset,
                               @Header(KafkaHeaders.GROUP_ID) String groupId,
                               Acknowledgment acknowledgment) {
        try {
            LOGGER.info("Received consumer=operational group={} payload='{}' from partitionId@offset='{}'", groupId, message, partitionId + "@" + offset);
            processMessage(message);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void processMessage(SensorDataMessage message) {
        //TODO: implement
    }
}
