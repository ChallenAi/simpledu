package com.fengyiai.simpledu.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

public class Resp {

    public static String RespReqFail(String msg) {
        HashMap<String, Object> resu = new HashMap<>();
        resu.put("code", 0);
        resu.put("data", msg);

        return JSON.toJSONString(resu);
    }

    public static String RespSucc() {
        HashMap<String, Integer> resu = new HashMap<>();
        resu.put("code", 0);
        return JSON.toJSONString(resu);
    }

    public static String RespServerFail(String msg) {
        HashMap<String, Object> resu = new HashMap<>();
        resu.put("code", 500);
        resu.put("data", msg);

        return JSON.toJSONString(resu);
    }

    public static String RespData(Object data) {
        HashMap<String, Object> resu = new HashMap<>();
        resu.put("code", 0);
        resu.put("data", data);

        return JSON.toJSONString(resu);
    }
}
