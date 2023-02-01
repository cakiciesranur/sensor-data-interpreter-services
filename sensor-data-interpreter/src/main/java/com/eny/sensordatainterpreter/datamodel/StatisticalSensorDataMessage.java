package com.eny.sensordatainterpreter.datamodel;

import com.eny.sensordatainterpreter.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "statisticalSensorDataMessages")
public class StatisticalSensorDataMessage implements Serializable {
    @Id
    private String id;
    private String messageId;
    private MessageType messageType;
    private String deviceId;
    private String vehicleType;
    private LocalDateTime eventTime;
    private StatusChange statusChange;
}
