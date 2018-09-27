package com.fengyiai.simpledu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;
import java.util.Map;

@RestController
public class CommonController {

    @RequestMapping(value = "/ping")
    public String pong() {
        return "pong";
    }

    @RequestMapping(value = "/")
    public Map<String, Object> index() {
//        Integer a = 1;
//        if (a != 2) {
//            throw new CtxException(123, "自定义错误");
//        }
        Map<String, Object> resu = new Hashtable<>();
        resu.put("code", 0);
        resu.put("message", "ok");

        return resu;
    }
}
