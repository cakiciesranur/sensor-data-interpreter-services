package com.eny.sensordatainterpreter.exception.customexceptions;


import com.eny.sensordatainterpreter.enums.ErrorMessage;

public class InvalidDateIntervalException extends ApplicationRuntimeException {

    public InvalidDateIntervalException() {
        super(ErrorMessage.INVALID_DATE_INTERVAL_ERROR.getMessage(), ErrorMessage.INVALID_DATE_INTERVAL_ERROR.getCode());
    }
}