package com.eny.sensordatainterpreter.payload.request;

import com.eny.sensordatainterpreter.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class SensorDataMessage {
    @NotNull
    @JsonProperty("id")
    private String messageId;
    @NotNull
    private MessageType messageType;
    @NotNull
    private String deviceId;
    private String vehicleType;
    private Date eventTime;
    private StatusChange statusChange;
}
