package com.eny.sensordataconsumer.exception.generalruntime;


import com.eny.sensordataconsumer.enums.ErrorMessage;

public class NotFoundException extends ApplicationRuntimeException {

    public NotFoundException() {
        super(ErrorMessage.NOT_FOUND.getMessage(), ErrorMessage.NOT_FOUND.getCode());
    }
}