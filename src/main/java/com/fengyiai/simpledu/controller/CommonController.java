package com.fengyiai.simpledu.controller;

import com.fengyiai.simpledu.model.Wiki;
import com.fengyiai.simpledu.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;
import java.util.Map;

@RestController
public class CommonController {

    @Autowired
    private WikiService wikiService;

    @RequestMapping(value = "/ping")
    public Map<String, Object> pong() {
        Map<String, Object> resu = new Hashtable<>();
        resu.put("code", 0);
        resu.put("data", "pong");
        return resu;
    }

    // 根据关键词搜索所有资源
    @RequestMapping(value = "/oapi/search")
    public Map<String, Object> searchResources(@RequestParam String keyword) {


        Map<String, Object> wikiResu = wikiService.searchWikiOrWikisByKeyword(keyword);

        Map<String, Object> resu = new Hashtable<>();
        resu.put("code", 0);
        resu.put("data", wikiResu);
        return resu;
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
