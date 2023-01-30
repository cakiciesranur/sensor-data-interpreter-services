package com.eny.sensordataconsumer.service.impl;

import com.eny.sensordataconsumer.client.DeviceServiceClient;
import com.eny.sensordataconsumer.client.payload.OptimalDeviceTemperature;
import com.eny.sensordataconsumer.datamodel.ComputedSensorMessageData;
import com.eny.sensordataconsumer.enums.TemperatureStatus;
import com.eny.sensordataconsumer.payload.request.SensorDataMessage;
import com.eny.sensordataconsumer.repository.IComputedSensorDataMessageRepository;
import com.eny.sensordataconsumer.service.ISensorDataMessageConsumer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class SensorOperationalDataConsumer implements ISensorDataMessageConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensorOperationalDataConsumer.class);

    @Autowired
    private IComputedSensorDataMessageRepository repository;

    @Autowired
    private DeviceServiceClient deviceServiceClient;

    @KafkaListener(id = "operational-listener-id", topics = "${spring.kafka.consumer.topic}",
            groupId = "group-operational", containerFactory = "operationalKafkaListenerContainer")
    @Override
    public void consumeMessage(@Payload @Valid SensorDataMessage message,
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
        if (message.getStatusChange() == null) {
            LOGGER.error("Status changes is empty for messageId={}", message.getMessageId());
            //TODO: handle kafka exceptions.
        } else {
            try {
                OptimalDeviceTemperature optimalTemp = deviceServiceClient.getOptimalTempByDeviceType(message.getVehicleType());
                ComputedSensorMessageData computedSensorMessageData = ComputedSensorMessageData.builder()
                        .temperatureStatus(getTemperatureStatus(optimalTemp.getOptimalTemp(), message.getStatusChange().getTemperature()))
                        .messageId(message.getMessageId())
                        .deviceId(message.getDeviceId())
                        .eventTime(message.getEventTime())
                        .build();
                repository.save(computedSensorMessageData);
                LOGGER.info("saved computed data to db for messageId= {}", message.getMessageId());
            } catch (Exception e) {
                LOGGER.error("Error occurred while computing data with error= {}, cause={}", e.getMessage(), e.getCause());
            }
        }
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
