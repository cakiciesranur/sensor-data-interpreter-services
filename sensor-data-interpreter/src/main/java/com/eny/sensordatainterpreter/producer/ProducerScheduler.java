package com.eny.sensordatainterpreter.producer;

import com.eny.sensordatainterpreter.enums.MessageType;
import com.eny.sensordatainterpreter.payload.request.LocationCoordinates;
import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;
import com.eny.sensordatainterpreter.payload.request.StatusChange;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProducerScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerScheduler.class);

    private final SensorDataMessageProducerService sender;

    @Value("${spring.kafka.consumer.topic}")
    private String topicName;

    //it runs after 10 seconds of startup-just one time, you will be dead before it runs again.
    //to send 600 consecutive messages to Kafka servers. 2 messages per device
    @Scheduled(initialDelay = 1000 * 10, fixedDelay = Long.MAX_VALUE)
    private void produceSensorData() {
        LOGGER.info("Starting producing messages...");

        IntStream.range(0, 300).parallel().forEach(i -> {
            String deviceId = UUID.randomUUID().toString();
            String deviceType = DeviceType.values()[new Random().nextInt(DeviceType.values().length)].toString();
            MessageType messageType = MessageType.values()[new Random().nextInt(MessageType.values().length)];
            sender.send(topicName, buildSensorMessage(deviceId, deviceType, messageType));
            sender.send(topicName, buildSensorMessage(deviceId, deviceType, messageType));
        });
        sendFailedMessagesToCheckRetryAndDltHandler();
    }

    private void sendFailedMessagesToCheckRetryAndDltHandler() {
       sender.send(topicName,buildSensorMessage("deviceIdForUnknownDeviceType", "unknownDeviceType", MessageType.OPERATIONAL));
    }

    private SensorDataMessage buildSensorMessage(String deviceId, String deviceType, MessageType messageType) {
        double longitude = Math.random() * Math.PI * 2;
        double latitude = Math.random() * Math.PI * 2;
        return SensorDataMessage.builder()
                .messageId(UUID.randomUUID().toString())
                .messageType(messageType)
                .deviceId(deviceId)
                .vehicleType(deviceType)
                .eventTime(randomLocalDateTime())
                .statusChange(StatusChange.builder()
                        .temperature(Math.random() * 100)
                        .batteryCharge(Math.random() * 100)
                        .deviceLocation(LocationCoordinates.builder()
                                .latitude(latitude)
                                .latitude(longitude)
                                .build())
                        .build())
                .build();
    }

    private LocalDateTime randomLocalDateTime() {
        long minDay = LocalDateTime.of(2021, 1, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
        long maxDay = LocalDateTime.of(2024, 1, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(randomDay), ZoneId.of("UTC"));
    }
}