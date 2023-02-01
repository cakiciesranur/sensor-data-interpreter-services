package com.eny.sensordatainterpreter.producer;

import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SensorDataMessageProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorDataMessageProducerService.class);
    private final KafkaTemplate<String, SensorDataMessage> kafkaTemplate;

    public void send(String topic, SensorDataMessage payload) {
        LOGGER.info("Sending payload='{}' to topic='{}", payload, topic);
        final ProducerRecord<String, SensorDataMessage> record = new ProducerRecord<>(topic, payload);

        CompletableFuture<SendResult<String, SensorDataMessage>> future = kafkaTemplate.send(record);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOGGER.info("Sent payload='{}' to topic-partition@offset='{}'", payload, record);
            } else {
                LOGGER.info("Sending payload='{}' to topic='{}' failed with error={}", payload, topic, ex.getMessage());
            }
        });
    }
}
