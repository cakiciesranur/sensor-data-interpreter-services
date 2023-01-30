package com.eny.sensordataconsumer.payload.response;

import com.eny.sensordataconsumer.payload.request.LocationCoordinates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationInfo {
    private Date eventTime;
    private LocationCoordinates locationCoordinates;
}
