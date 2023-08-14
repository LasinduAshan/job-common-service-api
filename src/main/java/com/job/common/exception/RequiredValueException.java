package com.job.common.exception;

public class RequiredValueException extends BaseException {
    public RequiredValueException() {
    }

    public RequiredValueException(String message) {
        super(message);
    }

    public RequiredValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredValueException(Throwable cause) {
        super(cause);
    }

    public RequiredValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
