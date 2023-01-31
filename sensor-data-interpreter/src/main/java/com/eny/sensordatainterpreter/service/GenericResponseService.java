package com.eny.sensordatainterpreter.service;

import com.eny.sensordatainterpreter.enums.ErrorMessage;
import com.eny.sensordatainterpreter.enums.ResponseType;
import com.eny.sensordatainterpreter.payload.response.GenericResponse;
import org.springframework.stereotype.Service;

@Service
public class GenericResponseService {

    public <T> GenericResponse createResponse(ResponseType responseType, String message, T data, int errorCode) {
        return new GenericResponse<T>()
                .setData(data)
                .setResponseType(responseType)
                .setMessage(message)
                .setErrorCode(errorCode);
    }

    public <T> GenericResponse createResponseNoError(String message, T data) {
        return createResponse(ResponseType.SUCCESS, message, data, ErrorMessage.NO_ERROR.getCode());
    }

    public <T> GenericResponse createResponseNoError(T data) {
        return createResponse(ResponseType.SUCCESS, null, data, ErrorMessage.NO_ERROR.getCode());
    }

    public <T> GenericResponse createResponseWithError(String message) {
        return createResponse(ResponseType.ERROR, message, null, ErrorMessage.UNKNOWN_ERROR.getCode());
    }

    public <T> GenericResponse createResponseWithError(String message, T data) {
        return createResponse(ResponseType.ERROR, message, data, ErrorMessage.UNKNOWN_ERROR.getCode());
    }

    public <T> GenericResponse createResponseWithError(String message, int errorCode) {
        return createResponse(ResponseType.ERROR, message, null, errorCode);
    }

    public <T> GenericResponse createResponseWithError(int errorCode) {
        return createResponse(ResponseType.ERROR, null, null, errorCode);
    }

    public <T> GenericResponse createResponseWithRedirect(String newURl, T data) {
        return createResponse(ResponseType.REDIRECT, newURl, data, ErrorMessage.NO_ERROR.getCode());
    }

    public <T> GenericResponse createResponseWithRedirect(String relativeNewURL) {
        return createResponse(ResponseType.REDIRECT, relativeNewURL, null, ErrorMessage.NO_ERROR.getCode());
    }
}