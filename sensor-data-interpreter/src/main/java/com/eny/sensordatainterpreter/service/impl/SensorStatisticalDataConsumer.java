package com.eny.sensordatainterpreter.service.impl;

import com.eny.sensordatainterpreter.datamodel.SensorStatisticalMessage;
import com.eny.sensordatainterpreter.mapper.SensorDataMessageMapper;
import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;
import com.eny.sensordatainterpreter.repository.ISensorStatisticalDataMessageRepository;
import com.eny.sensordatainterpreter.service.ISensorDataMessageConsumer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class SensorStatisticalDataConsumer implements ISensorDataMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorStatisticalDataConsumer.class);

    private final SensorDataMessageMapper mapper;
    private final ISensorStatisticalDataMessageRepository repository;

    @KafkaListener(id = "statistical-listener-id", topics = "${spring.kafka.consumer.topic}",
            groupId = "group-statistical", containerFactory = "staticalKafkaListenerContainer")
    public void consumeMessage(@Payload @Valid SensorDataMessage message,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Long partitionId,
                               @Header(KafkaHeaders.OFFSET) Long offset,
                               @Header(KafkaHeaders.GROUP_ID) String groupId,
                               Acknowledgment acknowledgment) {
        try {
            LOGGER.info("Received consumer=statistical group={} payload='{}' from partitionId@offset='{}'", groupId, message, partitionId + "@" + offset);
            processMessage(message);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void processMessage(SensorDataMessage message) {
        try {
            SensorStatisticalMessage sensorStatisticalMessage = mapper.toEntity(message);
            repository.insert(sensorStatisticalMessage);
            LOGGER.info("MessageId={} saved to database", message.getMessageId());
        } catch (Exception e) {
            LOGGER.error("Error occurred while saving messageId={} with error={}", message.getMessageId(), e.getMessage());
        }
    }
}
