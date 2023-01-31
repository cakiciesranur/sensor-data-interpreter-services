package com.eny.sensordatainterpreter.exception.generalruntime;


import com.eny.sensordatainterpreter.enums.ErrorMessage;

public class NotFoundException extends ApplicationRuntimeException {

    public NotFoundException() {
        super(ErrorMessage.NOT_FOUND.getMessage(), ErrorMessage.NOT_FOUND.getCode());
    }
}