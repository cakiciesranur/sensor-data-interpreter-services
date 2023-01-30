package com.eny.sensordataconsumer.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@Builder
@Jacksonized
public class LocationCoordinates {
    private double latitude;
    private double longitude;
}
