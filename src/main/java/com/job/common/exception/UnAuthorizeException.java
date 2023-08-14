package com.job.common.exception;

public class UnAuthorizeException extends BaseException {
    public UnAuthorizeException() {
    }

    public UnAuthorizeException(String message) {
        super(message);
    }

    public UnAuthorizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthorizeException(Throwable cause) {
        super(cause);
    }

    public UnAuthorizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
