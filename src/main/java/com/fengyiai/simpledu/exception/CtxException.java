package com.fengyiai.simpledu.exception;

public class CtxException extends RuntimeException {

//    private static final long serialVersionUID = 1L;

    private String message; //异常信息

    private Integer code;

    public CtxException(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
