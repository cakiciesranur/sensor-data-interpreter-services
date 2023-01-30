package com.eny.sensordataconsumer.datamodel;

import com.eny.sensordataconsumer.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sensorDataMessages")
public class SensorDataMessage implements Serializable {
    @Id
    private String id;
    private String messageId;
    private MessageType messageType;
    private Double temperature;
    private Double batteryCharge;
    private List<StatusChange> statusChanges;
}
