package com.eny.sensordatainterpreter.client.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptimalDeviceTemperature {

    private long id;
    private String deviceType;
    private double optimalTemp;

}
