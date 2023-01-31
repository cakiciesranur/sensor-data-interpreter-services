package com.eny.sensordatainterpreter.exception.generalruntime;

import com.eny.sensordatainterpreter.enums.ErrorMessage;

public class ProcessException extends ApplicationRuntimeException {

    public ProcessException() {
        super(ErrorMessage.PROCESS_ERROR.getMessage(), ErrorMessage.PROCESS_ERROR.getCode());
    }
}
