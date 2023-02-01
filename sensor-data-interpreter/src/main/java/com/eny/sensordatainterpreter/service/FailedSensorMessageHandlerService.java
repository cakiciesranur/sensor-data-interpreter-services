package com.eny.sensordatainterpreter.service;

import com.eny.sensordatainterpreter.datamodel.FailedSensorDataMessage;
import com.eny.sensordatainterpreter.mapper.FailedSensorDataMessageMapper;
import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;
import com.eny.sensordatainterpreter.repository.IFailedSensorDataMessageRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FailedSensorMessageHandlerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FailedSensorMessageHandlerService.class);

    private final IFailedSensorDataMessageRepository failedMessageRepository;
    private final FailedSensorDataMessageMapper mapper;

    public void savedFailedMessageToRepository(SensorDataMessage message) {
        FailedSensorDataMessage failedSensorMessage = mapper.toEntity(message);
        failedMessageRepository.insert(failedSensorMessage);
        LOGGER.info("DLQ message with messageId={} saved to repository", failedSensorMessage.getMessageId());
    }

}
