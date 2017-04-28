package com.skynet.exception;

/**
 * @author Skynet
 * @date 2017年04月28日 16:39
 */
public class MyException extends RuntimeException {

    static final long serialVersionUID = -7034897190745766939L;

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}
