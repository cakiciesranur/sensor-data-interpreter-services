package com.eny.sensordataconsumer.producer;

import com.eny.sensordataconsumer.enums.MessageType;
import com.eny.sensordataconsumer.payload.request.LocationCoordinates;
import com.eny.sensordataconsumer.payload.request.SensorDataMessage;
import com.eny.sensordataconsumer.payload.request.StatusChange;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProducerScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerScheduler.class);

    private final MultiPartitionMessageProducer sender;

    @Value("${spring.kafka.consumer.topic}")
    private String topicName;

    // it runs after 10 seconds of startup-just one time, you will be dead before it runs again.
    //to send 300 consecutive messages to 10 partition of Kafka server
    @Scheduled(initialDelay = 1000 * 10, fixedDelay = Long.MAX_VALUE)
    private void produceSensorData() {
        LOGGER.info("Starting producing messages...");
        for (int i = 0; i < 30; ++i) {
            for(int partitionKey = 1; partitionKey<=10; ++ partitionKey) {
                double longitude = Math.random() * Math.PI * 2;
                double latitude = Math.acos(Math.random() * 2 - 1);
                sender.send(topicName, "key"+partitionKey, SensorDataMessage.builder()
                        .messageId(UUID.randomUUID().toString())
                        .messageType(MessageType.values()[new Random().nextInt(MessageType.values().length)])
                        .deviceId(UUID.randomUUID().toString())
                        .vehicleType(DeviceType.values()[new Random().nextInt(DeviceType.values().length)].toString())
                        .eventTime(new Date())
                        .statusChange(StatusChange.builder()
                                .temperature(Math.random() * 100)
                                .batteryCharge(Math.random() * 100)
                                .deviceLocation(LocationCoordinates.builder()
                                        .latitude(latitude)
                                        .latitude(longitude)
                                        .build())
                                .build())
                        .build());
            }
        }
    }
}