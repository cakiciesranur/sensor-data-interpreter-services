package com.eny.sensordatainterpreter.payload.response;

import com.eny.sensordatainterpreter.payload.request.LocationCoordinates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationInfo {
    private LocalDateTime eventTime;
    private LocationCoordinates locationCoordinates;
}
