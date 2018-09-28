package com.fengyiai.simpledu.util;

public class RespSucc {
    final private Integer code = 0;
    private Object data;

    public RespSucc(Object data) {
        this.data = data;
    }

    public RespSucc() {}

    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}