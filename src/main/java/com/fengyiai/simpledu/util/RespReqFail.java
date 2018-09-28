package com.fengyiai.simpledu.util;

public class RespReqFail {
    final private Integer code = 400;
    private String msg;

    public RespReqFail(String msg) {
        this.msg = msg;
    }

    public RespReqFail() {}

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
