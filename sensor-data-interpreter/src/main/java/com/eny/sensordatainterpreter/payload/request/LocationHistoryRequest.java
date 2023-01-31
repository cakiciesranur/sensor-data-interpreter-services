package com.eny.sensordatainterpreter.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationHistoryRequest {
    @NotNull
    private String deviceId;
    @NotNull
    private Date startDate; //TODO: format kontrolü ekle
    @NotNull
    private Date endDate;
}
