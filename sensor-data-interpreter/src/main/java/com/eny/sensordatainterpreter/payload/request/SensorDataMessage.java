package com.eny.sensordatainterpreter.payload.request;

import com.eny.sensordatainterpreter.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventTime;
    private StatusChange statusChange;
}
