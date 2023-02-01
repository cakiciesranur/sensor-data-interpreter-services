package com.eny.sensordatainterpreter.service.impl;

import com.eny.sensordatainterpreter.datamodel.StatisticalSensorDataMessage;
import com.eny.sensordatainterpreter.exception.customexceptions.InvalidDateIntervalException;
import com.eny.sensordatainterpreter.exception.customexceptions.NotFoundException;
import com.eny.sensordatainterpreter.mapper.StatisticalSensorDataMessageMapper;
import com.eny.sensordatainterpreter.payload.request.LocationHistoryRequest;
import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;
import com.eny.sensordatainterpreter.payload.response.LocationHistoryResponse;
import com.eny.sensordatainterpreter.payload.response.LocationInfo;
import com.eny.sensordatainterpreter.repository.IStatisticalSensorDataMessageRepository;
import com.eny.sensordatainterpreter.service.ISensorDataInterpreterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.eny.sensordatainterpreter.enums.ErrorMessage.DEVICE_DETAILS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SensorDataInterpreterServiceImpl implements ISensorDataInterpreterService {
    private final IStatisticalSensorDataMessageRepository repository;

    private final StatisticalSensorDataMessageMapper mapper;

    @Override
    public LocationHistoryResponse getLocationHistory(LocationHistoryRequest request) {
        if (isInvalidRequest(request)) {
            throw new InvalidDateIntervalException();
        }
        LocationHistoryResponse locationHistoryResponse = LocationHistoryResponse.builder()
                .deviceId(request.getDeviceId())
                .build();

        List<StatisticalSensorDataMessage> messageList = repository.findByDeviceIdAndEventTimeBetweenOrderByEventTime(request.getDeviceId(),
                request.getStartDate(), request.getEndDate());

        locationHistoryResponse.setLocationInfos(extractLocationInfos(messageList));

        return locationHistoryResponse;
    }

    private List<LocationInfo> extractLocationInfos(List<StatisticalSensorDataMessage> messageListFromDb) {
        if (messageListFromDb.isEmpty()) {
            throw new NotFoundException(DEVICE_DETAILS_NOT_FOUND);
        }

        return messageListFromDb.stream()
                .filter(message -> Objects.nonNull(message) && Objects.nonNull(message.getStatusChange()))
                .map(messageFromDb -> {
                    SensorDataMessage sensorDataMessage = mapper.toDto(messageFromDb);
                    return LocationInfo.builder()
                            .locationCoordinates(sensorDataMessage.getStatusChange().getDeviceLocation())
                            .eventTime(sensorDataMessage.getEventTime())
                            .build();
                }).collect(Collectors.toList());
    }

    private boolean isInvalidRequest(LocationHistoryRequest request) {
        return request.getStartDate().isAfter(request.getEndDate());
    }
}
