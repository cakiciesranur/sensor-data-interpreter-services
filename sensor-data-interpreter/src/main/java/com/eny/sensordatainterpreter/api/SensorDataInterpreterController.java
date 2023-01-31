package com.eny.sensordatainterpreter.api;

import com.eny.sensordatainterpreter.payload.request.LocationHistoryRequest;
import com.eny.sensordatainterpreter.payload.response.GenericResponse;
import com.eny.sensordatainterpreter.payload.response.LocationHistoryResponse;
import com.eny.sensordatainterpreter.service.GenericResponseService;
import com.eny.sensordatainterpreter.service.ISensorDataInterpreterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.eny.sensordatainterpreter.enums.ErrorMessage.VALIDATION_ERROR;

@RestController
@RequestMapping("/sensorData")
@RequiredArgsConstructor
public class SensorDataInterpreterController {

    private final ISensorDataInterpreterService sensorDataInterpreterService;
    private final GenericResponseService genericResponseService;

    @PostMapping("/device/locations")
    public GenericResponse getLocationHistory(@Valid @RequestBody LocationHistoryRequest request) {
        if (isInvalidRequest(request)) {
            return genericResponseService.createResponseWithError("Invalid time interval to search", VALIDATION_ERROR);
        }
        LocationHistoryResponse locationHistory = sensorDataInterpreterService.getLocationHistory(request);
        return genericResponseService.createResponseNoError(locationHistory);
    }

    private boolean isInvalidRequest(LocationHistoryRequest request) {
        return request.getStartDate().after(request.getEndDate());
    }
}
