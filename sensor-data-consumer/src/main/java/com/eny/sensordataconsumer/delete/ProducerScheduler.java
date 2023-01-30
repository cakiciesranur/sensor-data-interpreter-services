package com.eny.sensordataconsumer.delete;

import com.eny.sensordataconsumer.enums.MessageType;
import com.eny.sensordataconsumer.payload.LocationCoordinates;
import com.eny.sensordataconsumer.payload.SensorDataMessage;
import com.eny.sensordataconsumer.payload.StatusChange;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProducerScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerScheduler.class);

    private final MultiPartitionMessageProducer sender;

    @Value("${spring.kafka.consumer.topic}")
    private String topicName;

    // it runs after 10 seconds of startup, you will be dead before it runs again.
    @Scheduled(initialDelay = 1000 * 10, fixedDelay = Long.MAX_VALUE)
    private void produceSensorData() {
        LOGGER.info("Starting producing messages...");
        for (int i = 0; i < 100; ++i) {
            double longitude = Math.random() * Math.PI * 2;
            double latitude = Math.acos(Math.random() * 2 - 1);
            sender.send(topicName, SensorDataMessage.builder()
                    .id(UUID.randomUUID().toString())
                    .messageType(MessageType.STATISTICAL)
                    .temperature(Math.random() * 100)
                    .batteryCharge(Math.random() * 100)
                    .statusChanges(Collections.singletonList(StatusChange.builder()
                            .deviceId(UUID.randomUUID().toString())
                            .eventTime(new Date())
                            .vehicleType("vehicle-type" + i)
                            .deviceLocation(LocationCoordinates.builder()
                                    .latitude(latitude)
                                    .latitude(longitude)
                                    .build())
                            .build()))
                    .build());
        }
    }
}

