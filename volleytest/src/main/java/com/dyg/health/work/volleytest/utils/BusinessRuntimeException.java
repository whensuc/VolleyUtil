package com.dyg.health.work.volleytest.utils;

/**
 * Created by wzy on 2015-10-9.
 */
public class BusinessRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 5658310118630120452L;

    private Integer code;

    public BusinessRuntimeException() {
        super();
    }

    public BusinessRuntimeException(String message) {
        super(message);
    }

    public BusinessRuntimeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}

