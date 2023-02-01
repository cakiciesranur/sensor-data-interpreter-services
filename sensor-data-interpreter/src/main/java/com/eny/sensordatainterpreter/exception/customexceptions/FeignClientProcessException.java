package com.eny.sensordatainterpreter.exception.customexceptions;

import com.eny.sensordatainterpreter.enums.ErrorMessage;

public class FeignClientProcessException extends ApplicationRuntimeException {

    public FeignClientProcessException() {
        super(ErrorMessage.FEIGN_CLIENT_ERROR.getMessage(), ErrorMessage.FEIGN_CLIENT_ERROR.getCode());
    }

    public FeignClientProcessException(String message, int errorCode) {
        super(message, errorCode);
    }
}
