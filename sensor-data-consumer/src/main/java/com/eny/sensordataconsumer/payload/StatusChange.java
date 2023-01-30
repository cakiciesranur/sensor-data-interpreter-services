package com.eny.sensordataconsumer.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Jacksonized
public class StatusChange {
    private String deviceId;
    private Date eventTime;
    private String vehicleType; //TODO: bunu enum yapmak gerekebilir yaptıgın logice göre
    private LocationCoordinates deviceLocation;
}
