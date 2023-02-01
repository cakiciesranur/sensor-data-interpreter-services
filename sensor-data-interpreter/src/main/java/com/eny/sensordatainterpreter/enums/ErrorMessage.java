package com.eny.sensordatainterpreter.enums;

public enum ErrorMessage {
    INVALID_DATE_FORMAT_ERROR(1, "Invalid date format"),
    INVALID_DATE_INTERVAL_ERROR(2, "Date interval is invalid to search"),
    MISSING_FIELD_IN_REQUEST(3, "Missing field"),
    FEIGN_CLIENT_ERROR(4, "Something happened while calling the feign client"),
    FEIGN_API_SERVER_ERROR(5, "Feign client return server error."),
    FEIGN_API_CLIENT_ERROR(6, "Feign client return client error."),
    STATUS_CHANGES_NOT_FOUND(7, "Status changes is empty in the message"),
    DEVICE_DETAILS_NOT_FOUND(8, "Device or status changes not found in database"),
    NOT_FOUND(404, "Nothing found, something wrong!"),
    UNKNOWN_ERROR(999, "Unknown Error!"),
    VALIDATION_ERROR(1010, "Some required fields are missing");

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
