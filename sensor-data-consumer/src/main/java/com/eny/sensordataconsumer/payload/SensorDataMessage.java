package com.eny.sensordataconsumer.payload;

import com.eny.sensordataconsumer.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class SensorDataMessage {
    @Id
    private String id;
    private MessageType messageType;
    private Double temperature;
    private Double batteryCharge;
    private List<StatusChange> statusChanges;
}
