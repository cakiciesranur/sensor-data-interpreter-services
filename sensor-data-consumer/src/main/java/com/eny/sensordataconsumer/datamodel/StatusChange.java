package com.eny.sensordataconsumer.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class StatusChange {
    private String deviceId;
    private Date eventTime;
    private String vehicleType;
    private LocationCoordinates deviceLocation;
}
