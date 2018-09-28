package com.fengyiai.simpledu.controller;

import com.fengyiai.simpledu.mapper.UserMapper;
import com.fengyiai.simpledu.mapper.ExplainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WikiController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExplainMapper explainMapper;

    // 新增词条
    @RequestMapping(value = "/api/wiki", method = RequestMethod.POST)
    public Object addWiki() {
        return "ok";
    }

    // 新增解释
    @RequestMapping(value = "/api/explain", method = RequestMethod.POST)
    public String addExplain() {
        return "ok";
    }

    // 新增问题
    @RequestMapping(value = "/api/question", method = RequestMethod.POST)
    public String addQuestion() {
        return "ok";
    }

    // 新增回答
    @RequestMapping(value = "/api/answer", method = RequestMethod.POST)
    public String addAnswer() {
        return "ok";
    }

    // 点赞解释
    @RequestMapping(value = "/api/explain_up", method = RequestMethod.POST)
    public String explainUp() {
        return "ok";
    }

    // 踩解释
    @RequestMapping(value = "/api/explain_down", method = RequestMethod.POST)
    public String explainDown() {
        return "ok";
    }

    // 收藏解释
    @RequestMapping(value = "/api/explain_collect", method = RequestMethod.POST)
    public String explainCollect() {
        return "ok";
    }

    // 分享解释(留)
    @RequestMapping(value = "/api/explain_share", method = RequestMethod.POST)
    public String explainShare() {
        return "ok";
    }

    // 点赞回答
    @RequestMapping(value = "/api/answer_good", method = RequestMethod.POST)
    public String answerGood() {
        return "ok";
    }

    // 收藏回答
    @RequestMapping(value = "/api/answer_collect", method = RequestMethod.POST)
    public String answerCollect() {
        return "ok";
    }

    // 搜索词条(留)
    @RequestMapping(value = "/oapi/wiki/search")
    public String searchWikis(@RequestParam String keyword) {
        return "ok";
    }

    // 搜索问题
    @RequestMapping(value = "/oapi/question/search")
    public String searchQuestions(@RequestParam String keyword) {
        return "ok";
    }

    // web词条首页信息
    @RequestMapping(value = "/oapi/wiki/web")
    public String wikiWeb() {
        return "ok";
    }

    // app词条首页信息(留)，根据id获取词条信息
    @RequestMapping(value = "/oapi/wiki/app")
    public String wikiApp() {
        return "ok";
    }

    // 获取解释列表(点击加载更多)
    @RequestMapping(value = "/oapi/explain/list")
    public HashMap<String, Object> getExplains() {
        // @RequestParam String pageSize, String pageNumber
//        List<Map> data = explainMapper.findExplainsByWikiId(1);

//        System.out.println(data);
        HashMap<String, Object> resp = new HashMap<>();
        resp.put("code", HttpStatus.OK);
//        resp.put("data", data);
        System.out.println(resp);
        return resp;
    }

    // 获取问题列表(点击加载更多)
    @RequestMapping(value = "/oapi/question/list")
    public String getQuestions() {
        return "ok";
    }
}
