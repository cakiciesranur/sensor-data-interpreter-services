package com.eny.sensordatainterpreter.service.impl;

import com.eny.sensordatainterpreter.client.DeviceServiceClient;
import com.eny.sensordatainterpreter.client.payload.OptimalDeviceTemperature;
import com.eny.sensordatainterpreter.datamodel.ComputedSensorDataMessage;
import com.eny.sensordatainterpreter.enums.ErrorMessage;
import com.eny.sensordatainterpreter.enums.TemperatureStatus;
import com.eny.sensordatainterpreter.exception.customexceptions.FeignApiClientException;
import com.eny.sensordatainterpreter.exception.customexceptions.NotFoundException;
import com.eny.sensordatainterpreter.mapper.OperationalSensorDataMessageMapper;
import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;
import com.eny.sensordatainterpreter.repository.IComputedSensorDataMessageRepository;
import com.eny.sensordatainterpreter.repository.IOperationalSensorDataMessageRepository;
import com.eny.sensordatainterpreter.service.FailedSensorMessageHandlerService;
import com.eny.sensordatainterpreter.service.ISensorDataMessageConsumer;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
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
public class OperationalSensorDataConsumer implements ISensorDataMessageConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationalSensorDataConsumer.class);

    private final IComputedSensorDataMessageRepository computedMessageRepository;
    private final IOperationalSensorDataMessageRepository operationalSensorDataMessageRepository;
    private final OperationalSensorDataMessageMapper mapper;
    private final FailedSensorMessageHandlerService handlerService;
    private final DeviceServiceClient deviceServiceClient;

    @RetryableTopic(
            backoff = @Backoff(delay = 1000, multiplier = 2.0, maxDelay = 2000),
            autoCreateTopics = "false", topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            retryTopicSuffix = "-operational", dltTopicSuffix = "-operational-dlt",
            //exclude = {FeignApiClientException.class, NotFoundException.class, ValidationException.class})
            exclude = {NotFoundException.class, ValidationException.class})
    @KafkaListener(id = "operational-listener-id", topics = "${spring.kafka.consumer.topic}",
            groupId = "group-operational", containerFactory = "operationalKafkaListenerContainer")
    @Override
    public void consumeMessage(@Payload @Valid SensorDataMessage message,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String receivedTopic,
                               @Header(KafkaHeaders.RECEIVED_PARTITION) Long partitionId,
                               @Header(KafkaHeaders.OFFSET) Long offset,
                               @Header(KafkaHeaders.GROUP_ID) String groupId,
                               Acknowledgment acknowledgment) {

        LOGGER.info("Received consumer=operational group={} payload='{}' from partitionId@offset='{}'", groupId, message, partitionId + "@" + offset);
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
        if (message.getStatusChange() == null) {
            LOGGER.error("Status changes is empty for messageId={}", message.getMessageId());
            throw new NotFoundException(ErrorMessage.STATUS_CHANGES_NOT_FOUND);
        } else {
            OptimalDeviceTemperature optimalTemp = deviceServiceClient.getOptimalTempByDeviceType(message.getVehicleType());
            ComputedSensorDataMessage computedSensorMessageData = ComputedSensorDataMessage.builder()
                    .temperatureStatus(getTemperatureStatus(optimalTemp.getOptimalTemp(), message.getStatusChange().getTemperature()))
                    .messageId(message.getMessageId())
                    .deviceId(message.getDeviceId())
                    .eventTime(message.getEventTime())
                    .build();
            computedMessageRepository.save(computedSensorMessageData);
            LOGGER.info("Saved computed data to repository for messageId= {}", message.getMessageId());
        }
        saveOperationalSensorMessageToRepository(message);
    }

    private void saveOperationalSensorMessageToRepository(SensorDataMessage message) {
        operationalSensorDataMessageRepository.save(mapper.toEntity(message));
    }

    private TemperatureStatus getTemperatureStatus(double optimalTemp, Double temperature) {
        if (temperature > optimalTemp) {
            return TemperatureStatus.HIGH;
        } else if (temperature < (optimalTemp * 0.05)) {
            return TemperatureStatus.LOW;
        } else {
            return TemperatureStatus.OPTIMUM;
        }
    }
}
