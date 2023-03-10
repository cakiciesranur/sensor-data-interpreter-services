package com.eny.sensordatainterpreter.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LocationCoordinates {
    private double latitude;
    private double longitude;
}
