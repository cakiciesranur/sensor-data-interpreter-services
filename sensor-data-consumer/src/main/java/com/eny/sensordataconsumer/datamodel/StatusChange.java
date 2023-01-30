package com.eny.sensordataconsumer.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StatusChange {
    private Double temperature;
    private Double batteryCharge;
    private LocationCoordinates deviceLocation;
}
