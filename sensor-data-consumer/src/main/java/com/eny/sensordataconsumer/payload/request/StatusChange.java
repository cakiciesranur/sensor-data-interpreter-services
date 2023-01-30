package com.eny.sensordataconsumer.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@Builder
@Jacksonized
public class StatusChange {
    private Double temperature;
    private Double batteryCharge;
    private LocationCoordinates deviceLocation;
}