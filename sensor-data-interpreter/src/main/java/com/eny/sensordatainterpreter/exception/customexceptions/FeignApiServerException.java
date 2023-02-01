package com.eny.sensordatainterpreter.exception.customexceptions;

import com.eny.sensordatainterpreter.enums.ErrorMessage;

public class FeignApiServerException extends FeignClientProcessException {

    public FeignApiServerException() {
        super(ErrorMessage.FEIGN_API_SERVER_ERROR.getMessage(), ErrorMessage.FEIGN_API_SERVER_ERROR.getCode());
    }
}
