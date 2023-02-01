package com.eny.sensordatainterpreter.service.impl;

import com.eny.sensordatainterpreter.datamodel.StatisticalSensorDataMessage;
import com.eny.sensordatainterpreter.mapper.StatisticalSensorDataMessageMapper;
import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;
import com.eny.sensordatainterpreter.repository.IStatisticalSensorDataMessageRepository;
import com.eny.sensordatainterpreter.service.FailedSensorMessageHandlerService;
import com.eny.sensordatainterpreter.service.ISensorDataMessageConsumer;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@EnableKafka
public class StatisticalSensorDataConsumer implements ISensorDataMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticalSensorDataConsumer.class);

    private final StatisticalSensorDataMessageMapper mapper;
    private final IStatisticalSensorDataMessageRepository repository;
    private final FailedSensorMessageHandlerService handlerService;

    @RetryableTopic(backoff = @Backoff(delay = 1000, multiplier = 2.0, maxDelay = 3000),
            autoCreateTopics = "false", topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            retryTopicSuffix = "-statistical", dltTopicSuffix = "-statistical-dlt",
            exclude = ValidationException.class)
    @KafkaListener(id = "statistical-listener-id", topics = "${spring.kafka.consumer.topic}",
            groupId = "group-statistical", containerFactory = "staticalKafkaListenerContainer")
    @Override
    public void consumeMessage(@Payload @Valid SensorDataMessage message,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String receivedTopic,
                               @Header(KafkaHeaders.RECEIVED_PARTITION) Long partitionId,
                               @Header(KafkaHeaders.OFFSET) Long offset,
                               @Header(KafkaHeaders.GROUP_ID) String groupId,
                               Acknowledgment acknowledgment) {
        LOGGER.info("Received consumer=statistical group={} payload='{}' from partitionId@offset='{}'", groupId, message, partitionId + "@" + offset);
        processMessage(message);
        acknowledgment.acknowledge();
    }

    @DltHandler
    public void deadLetterHandler(SensorDataMessage message,
                                  @Header(KafkaHeaders.OFFSET) String offset,
                                  @Header(KafkaHeaders.GROUP_ID) String groupId,
                                  @Header(KafkaHeaders.ORIGINAL_TOPIC) String originalTopic, Acknowledgment acknowledgment) {
        try {
            LOGGER.info("Consuming DLQ message={} for originalTopic={} with offset={} and groupId={}", message, originalTopic, offset, groupId);
            handlerService.savedFailedMessageToRepository(message);
        } catch (Exception e) {
            LOGGER.error("Unable to process DLQ message={}", message);
        } finally {
            acknowledgment.acknowledge();
        }
    }

    @Override
    public void processMessage(SensorDataMessage message) {
        StatisticalSensorDataMessage sensorStatisticalMessage = mapper.toEntity(message);
        repository.insert(sensorStatisticalMessage);
        LOGGER.info("Saved statistical sensor data message with messageId={} to database", message.getMessageId());
    }
}
