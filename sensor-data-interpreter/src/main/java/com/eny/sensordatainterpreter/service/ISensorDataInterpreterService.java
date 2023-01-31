package com.eny.sensordatainterpreter.service;

import com.eny.sensordatainterpreter.payload.request.LocationHistoryRequest;
import com.eny.sensordatainterpreter.payload.response.LocationHistoryResponse;

public interface ISensorDataInterpreterService {

    LocationHistoryResponse getLocationHistory(LocationHistoryRequest request);

}
