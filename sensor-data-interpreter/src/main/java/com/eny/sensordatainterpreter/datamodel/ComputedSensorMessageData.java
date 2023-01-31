package com.eny.sensordatainterpreter.datamodel;

import com.eny.sensordatainterpreter.enums.TemperatureStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComputedSensorMessageData {
    private String messageId;
    private String vehicleType;
    private String deviceId;
    private Date eventTime;
    private TemperatureStatus temperatureStatus;
}