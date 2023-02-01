package com.eny.sensordatainterpreter.api;

import com.eny.sensordatainterpreter.payload.request.LocationCoordinates;
import com.eny.sensordatainterpreter.payload.request.LocationHistoryRequest;
import com.eny.sensordatainterpreter.payload.response.LocationHistoryResponse;
import com.eny.sensordatainterpreter.payload.response.LocationInfo;
import com.eny.sensordatainterpreter.service.ISensorDataInterpreterService;
import com.eny.sensordatainterpreter.util.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SensorDataInterpreterControllerTest {
    public static final String SUCCESSFUL_DEVICE_ID = "successfulDeviceId";

    @InjectMocks
    SensorDataInterpreterController controller;

    @Mock
    ISensorDataInterpreterService sensorDataInterpreterService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnLocationHistoryList() {
        LocationHistoryResponse mockServiceResponse = createMockLocationHistoryResponse();
        when(sensorDataInterpreterService.getLocationHistory(any())).thenReturn(mockServiceResponse);

        LocationHistoryResponse actual = controller.getLocationHistory(createValidRequest());
        assertEquals(1, actual.getLocationInfos().size());
        assertEquals(mockServiceResponse.getDeviceId(), actual.getDeviceId());
        verify(sensorDataInterpreterService, times(1)).getLocationHistory(any());
    }

    private LocationHistoryRequest createInvalidDateIntervalRequest() throws JsonProcessingException {
        return LocationHistoryRequest.builder()
                .deviceId("device-id")
                .startDate(TestUtil.stringToLocalDateTime("2025-01-04T22:00"))
                .endDate(TestUtil.stringToLocalDateTime("2024-02-01T23:54"))
                .build();
    }


    private LocationHistoryRequest createValidRequest() {
        return LocationHistoryRequest.builder()
                .deviceId(SUCCESSFUL_DEVICE_ID)
                .startDate(TestUtil.stringToLocalDateTime("2020-01-04T22:00"))
                .endDate(TestUtil.stringToLocalDateTime("2024-02-01T23:54"))
                .build();
    }

    private LocationHistoryResponse createMockLocationHistoryResponse() {
        return LocationHistoryResponse.builder()
                .deviceId(SUCCESSFUL_DEVICE_ID)
                .locationInfos(createMockLocationInfoList())
                .build();
    }

    private static List<LocationInfo> createMockLocationInfoList() {
        return Collections.singletonList(LocationInfo.builder()
                .locationCoordinates(LocationCoordinates.builder()
                        .latitude(35.67)
                        .latitude(-78.23)
                        .build())
                .build());
    }

}
