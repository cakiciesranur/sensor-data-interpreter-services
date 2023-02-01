package com.eny.sensordatainterpreter.exception.customexceptions;


import com.eny.sensordatainterpreter.enums.ErrorMessage;

public class InvalidDateFormatException extends ApplicationRuntimeException {

    public InvalidDateFormatException() {
        super(ErrorMessage.INVALID_DATE_FORMAT_ERROR.getMessage(), ErrorMessage.INVALID_DATE_FORMAT_ERROR.getCode());
    }
}