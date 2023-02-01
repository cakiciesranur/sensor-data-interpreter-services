package com.eny.sensordatainterpreter.datamodel;

import com.eny.sensordatainterpreter.enums.TemperatureStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "computedSensorDataMessages")
public class ComputedSensorDataMessage {
    private String messageId;
    private String vehicleType;
    private String deviceId;
    private LocalDateTime eventTime;
    private TemperatureStatus temperatureStatus;
}
