package com.eny.sensordataconsumer.payload.response;

import com.eny.sensordataconsumer.enums.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class GenericResponse<T> {

    private ResponseType responseType;
    private int errorCode;
    private String message;
    private T data;

    public GenericResponse() {

    }
}
