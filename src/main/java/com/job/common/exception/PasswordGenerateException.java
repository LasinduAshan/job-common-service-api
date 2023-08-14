package com.job.common.exception;

public class PasswordGenerateException extends BaseException {
    public PasswordGenerateException() {
    }

    public PasswordGenerateException(String message) {
        super(message);
    }

    public PasswordGenerateException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordGenerateException(Throwable cause) {
        super(cause);
    }

    public PasswordGenerateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
