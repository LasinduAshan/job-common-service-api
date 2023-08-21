package com.job.common.exception;

public enum ExceptionConstant {
    ACCESS_DENIED("Access denied!"),
    ERROR_MESSAGE_TEMPLATE(""),
    LIST_JOIN_DELIMITER(","),
    FIELD_ERROR_SEPARATOR(": "),
    ERRORS_FOR_PATH("errors {} for path {}"),
    ERRORS("error"),
    STATUS("status"),
    ERROR_MESSAGE("errorMessage"),
    ERROR_CODE("errorCode"),
    TIMESTAMP("timestamp"),
    TYPE("type"),
    INVALID_REQUEST("Invalid request"), MESSAGE("message");


    private final String string;

    ExceptionConstant(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
