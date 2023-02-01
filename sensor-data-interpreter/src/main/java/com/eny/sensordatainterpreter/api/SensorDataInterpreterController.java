package com.eny.sensordatainterpreter.api;

import com.eny.sensordatainterpreter.payload.request.LocationHistoryRequest;
import com.eny.sensordatainterpreter.payload.response.LocationHistoryResponse;
import com.eny.sensordatainterpreter.service.ISensorDataInterpreterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensorData")
@RequiredArgsConstructor
@Tag(name = "SensorDataInterpreterController", description = "Sensor data messages related resources")
public class SensorDataInterpreterController {

    private final ISensorDataInterpreterService sensorDataInterpreterService;

    @PostMapping("/device/locations")
    @Operation(summary = "Retrieve device location based on date time", description = "This returns locations by device id and date interval")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Device or status changes not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    public LocationHistoryResponse getLocationHistory(@Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
            @ExampleObject(
                    name = "LocationHistoryRequest sample",
                    description = "Retrieve location request example",
                    value = "{\"deviceId\": \"device-id\",\"startDate\": \"2021-01-19T19:54\",\"endDate\": \"2024-01-19T23:00\"}")
    })) @RequestBody LocationHistoryRequest request) {
        return sensorDataInterpreterService.getLocationHistory(request);
    }
}
