package com.eny.sensordatainterpreter.producer;

import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

@Service
@RequiredArgsConstructor
public class MultiPartitionMessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiPartitionMessageProducer.class);
    private final KafkaTemplate<String, SensorDataMessage> kafkaTemplate;

    public void send(String topic, String key, SensorDataMessage payload) {
        LOGGER.info("Sending payload='{}' to topic='{}", payload, topic);
        ListenableFuture<SendResult<String, SensorDataMessage>> future = kafkaTemplate.send(topic, key, payload);
        SuccessCallback<SendResult<String, SensorDataMessage>> successCallback = sendResult ->
                LOGGER.info("Sent payload='{}' to topic-partition@offset='{}'", payload, sendResult.getRecordMetadata().toString());
        FailureCallback failureCallback = throwable ->
                LOGGER.info("Sending payload='{}' to topic='{}' failed!!!", payload, topic);
        future.addCallback(successCallback, failureCallback);
    }
}
