package com.eny.sensordataconsumer.enums;

public enum ErrorMessage {
    NO_ERROR(-1, "No error"),
    PARSE_ERROR(2, "Something wrong in your information!"),
    PROCESS_ERROR(3, "Something happened while process. Please try again"),
    BAD_CREDENTIALS_ERROR(5, "Username or password wrong!"),
    NOT_FOUND(404, "Nothing found, something wrong!"),
    UNKNOWN_ERROR(999, "Unknown Error!"),
    VALIDATION_ERROR(1010, "Some required fields are missing!");
    private final int code;
    private final String message;

    ErrorMessage(int errorCode, String message) {
        this.code = errorCode;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
