package com.eny.sensordatainterpreter.exception.customexceptions;


import com.eny.sensordatainterpreter.enums.ErrorMessage;

public class NotFoundException extends ApplicationRuntimeException {

    public NotFoundException() {
        super(ErrorMessage.NOT_FOUND.getMessage(), ErrorMessage.NOT_FOUND.getCode());
    }

    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage(), errorMessage.getCode());
    }
}