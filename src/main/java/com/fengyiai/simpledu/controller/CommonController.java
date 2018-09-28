package com.fengyiai.simpledu.controller;

import com.fengyiai.simpledu.model.Wiki;
import com.fengyiai.simpledu.service.WikiService;
import com.fengyiai.simpledu.util.RespSucc;
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
    public RespSucc pong() {
        return new RespSucc("pong");
    }

    // 根据关键词搜索所有资源
    @RequestMapping(value = "/oapi/search")
    public RespSucc searchResources(@RequestParam String keyword) {
        RespSucc resp = new RespSucc();

        // 返回值{find; wiki或wikis}
        // 如果是由匹配到的wiki(isFind==true),直接用wikiId并发取解释/问题和回答；await都完成(用latch)并整合结果返回resu
        // 如果是wikis,即find是false, 直接返回wikis列表，用于展示搜索结果
        Map<String, Object> wikiResu = wikiService.searchWikiOrWikisByKeyword(keyword);

        if ((Boolean) wikiResu.get("isFind")) {
            resp.setData(wikiResu.get("data"));
        } else {
            resp.setData(wikiResu.get("data"));
        }

        return resp;
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
