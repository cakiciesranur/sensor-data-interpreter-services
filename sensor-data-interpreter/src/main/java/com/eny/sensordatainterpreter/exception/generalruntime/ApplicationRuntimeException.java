package com.eny.sensordatainterpreter.exception.generalruntime;

import lombok.Getter;

public class ApplicationRuntimeException extends RuntimeException {

    @Getter
    private final int errorCode;

    /** Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public ApplicationRuntimeException(String message,int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
