package com.eny.sensordatainterpreter.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class GenericErrorResponse {

    private String message;
    private int errorCode;
    public GenericErrorResponse() {

    }
}
