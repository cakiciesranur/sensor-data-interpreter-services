package com.eny.sensordatainterpreter.datamodel;

import com.eny.sensordatainterpreter.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sensorStatisticalMessages")
public class SensorStatisticalMessage implements Serializable {
    @Id
    private String id;
    private String messageId;
    private MessageType messageType;
    private String deviceId;
    private String vehicleType;
    private Date eventTime;
    private StatusChange statusChange;
}
