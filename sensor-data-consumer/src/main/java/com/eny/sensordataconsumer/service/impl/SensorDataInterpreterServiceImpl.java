package com.eny.sensordataconsumer.service.impl;

import com.eny.sensordataconsumer.datamodel.SensorStatisticalMessage;
import com.eny.sensordataconsumer.mapper.SensorDataMessageMapper;
import com.eny.sensordataconsumer.payload.request.LocationHistoryRequest;
import com.eny.sensordataconsumer.payload.request.SensorDataMessage;
import com.eny.sensordataconsumer.payload.response.LocationHistoryResponse;
import com.eny.sensordataconsumer.payload.response.LocationInfo;
import com.eny.sensordataconsumer.repository.ISensorStatisticalDataMessageRepository;
import com.eny.sensordataconsumer.service.ISensorDataInterpreterService;
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
