package com.fengyiai.simpledu.util;

public class RespServerFail {
    final private Integer code = 500;
    private String msg;

    public RespServerFail(String msg) {
        this.msg = msg;
    }

    public RespServerFail() {}

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
