package com.github.pampas.common.exception;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-12
 */
public class NoServiceInstanceFoundException extends PampasException {

    public NoServiceInstanceFoundException(Throwable ex) {
        super(ex);
    }

    public NoServiceInstanceFoundException(String message) {
        super(message);
    }

    public NoServiceInstanceFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
