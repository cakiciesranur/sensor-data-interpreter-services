package com.eny.sensordataconsumer.exception.generalruntime;

import com.eny.sensordataconsumer.enums.ErrorMessage;

public class ProcessException extends ApplicationRuntimeException {

    public ProcessException() {
        super(ErrorMessage.PROCESS_ERROR.getMessage(), ErrorMessage.PROCESS_ERROR.getCode());
    }
}
