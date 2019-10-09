package com.riverluoo.common.exception;

/**
 * @auther: wangyang
 * @since: 2019-10-09 15:30
 */
public class AuthorizeException extends RuntimeException {
    public AuthorizeException() {
        super();
    }

    public AuthorizeException(String message) {
        super(message);
    }

    public AuthorizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizeException(Throwable cause) {
        super(cause);
    }

    protected AuthorizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
