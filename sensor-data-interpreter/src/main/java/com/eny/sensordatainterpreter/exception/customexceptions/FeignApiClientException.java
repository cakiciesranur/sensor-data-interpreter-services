package com.eny.sensordatainterpreter.exception.customexceptions;

import com.eny.sensordatainterpreter.enums.ErrorMessage;

public class FeignApiClientException extends FeignClientProcessException {

    public FeignApiClientException() {
        super(ErrorMessage.FEIGN_API_CLIENT_ERROR.getMessage(), ErrorMessage.FEIGN_CLIENT_ERROR.getCode());
    }
}
