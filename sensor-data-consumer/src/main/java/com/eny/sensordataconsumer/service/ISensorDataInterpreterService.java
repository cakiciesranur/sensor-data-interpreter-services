package com.eny.sensordataconsumer.service;

import com.eny.sensordataconsumer.payload.request.LocationHistoryRequest;
import com.eny.sensordataconsumer.payload.response.LocationHistoryResponse;

public interface ISensorDataInterpreterService {

    LocationHistoryResponse getLocationHistory(LocationHistoryRequest request);

}
