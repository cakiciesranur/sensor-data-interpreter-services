package com.eny.sensordatainterpreter.service.impl;

import com.eny.sensordatainterpreter.datamodel.SensorStatisticalMessage;
import com.eny.sensordatainterpreter.mapper.SensorDataMessageMapper;
import com.eny.sensordatainterpreter.payload.request.LocationHistoryRequest;
import com.eny.sensordatainterpreter.payload.request.SensorDataMessage;
import com.eny.sensordatainterpreter.payload.response.LocationHistoryResponse;
import com.eny.sensordatainterpreter.payload.response.LocationInfo;
import com.eny.sensordatainterpreter.repository.ISensorStatisticalDataMessageRepository;
import com.eny.sensordatainterpreter.service.ISensorDataInterpreterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorDataInterpreterServiceImpl implements ISensorDataInterpreterService {
    private final ISensorStatisticalDataMessageRepository repository;

    private final SensorDataMessageMapper mapper;

    @Override
    public LocationHistoryResponse getLocationHistory(LocationHistoryRequest request) {
        LocationHistoryResponse locationHistoryResponse = LocationHistoryResponse.builder()
                .deviceId(request.getDeviceId())
                .build();

        List<SensorStatisticalMessage> messageList = repository.findByDeviceIdAndEventTimeBetweenOrderByEventTime(request.getDeviceId(),
                request.getStartDate(), request.getEndDate());

        if (messageList.isEmpty()) {
            return locationHistoryResponse;
        }

        List<LocationInfo> locationInfos = extractLocationInfos(messageList);
        locationHistoryResponse.setLocationInfos(locationInfos);

        return locationHistoryResponse;
    }

    private List<LocationInfo> extractLocationInfos(List<SensorStatisticalMessage> messageListFromDb) {
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
}
