package com.fengyiai.simpledu.controller;

import com.fengyiai.simpledu.exception.CtxException;
import com.fengyiai.simpledu.mapper.QuestionMapper;
import com.fengyiai.simpledu.mapper.UserMapper;
import com.fengyiai.simpledu.mapper.ExplainMapper;
import com.fengyiai.simpledu.mapper.WikiMapper;
import com.fengyiai.simpledu.model.Explain;
import com.fengyiai.simpledu.model.Question;
import com.fengyiai.simpledu.model.Wiki;
import com.fengyiai.simpledu.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WikiController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExplainMapper explainMapper;

    @Autowired
    private WikiMapper wikiMapper;

    @Autowired
    private QuestionMapper questionMapper;

    // 新增词条
    @RequestMapping(value = "/api/wiki", method = RequestMethod.POST)
    public Object addWiki(@RequestAttribute Long userId, @RequestBody Map<String, String> payload) {

        if (payload.get("parentId") == null || payload.get("name") == null) {
            return Resp.RespReqFail("缺少请求参数");
        }

        Long parentId = Long.valueOf(payload.get("parentId"));

        // 如果不是根词条，就需要验证他的父标签是否存在
        if (parentId != 0L) {
            Wiki wikiParent = wikiMapper.selectByPrimaryKey(parentId);
            if (wikiParent == null) {
                return Resp.RespReqFail("请求参数错误");
            }
        }

        Wiki wiki = new Wiki();
        wiki.setName(payload.get("name"));
        wiki.setAlias(payload.get("alias"));
        wiki.setEnglishName(payload.get("englishName"));
        wiki.setCreaterId(userId);
        wiki.setGmtCreate(new Date());
        wiki.setGmtModified(new Date());
        wiki.setParentId(parentId);

        try {
            Integer succ = wikiMapper.insertSelective(wiki);
            if (succ == 1) {
                return Resp.RespSucc("ok");
            } else {
                return Resp.RespServerFail("新增词条失败");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail(e.getMessage());
        }
    }

    // 新增解释
    @RequestMapping(value = "/api/explain", method = RequestMethod.POST)
    public Object addExplain(@RequestAttribute Long userId, @RequestBody Map<String, String> payload) {
        if (payload.get("content") == null || payload.get("wikiId") == null) {
            return Resp.RespReqFail("缺少请求参数");
        }

        Long wikiId = Long.valueOf(payload.get("wikiId"));

        // 查看wikiId是否有对应词条
        Wiki wikiParent = wikiMapper.selectByPrimaryKey(wikiId);
        if (wikiParent == null) {
            return Resp.RespReqFail("请求参数错误");
        }

        Explain explain = new Explain();
        explain.setContent(payload.get("content"));
        explain.setWikiId(wikiId);
        explain.setCreaterId(userId);
        explain.setGmtCreate(new Date());
        explain.setGmtModified(new Date());

        try {
            Integer succ = explainMapper.insertSelective(explain);
            if (succ == 1) {
                return Resp.RespSucc("ok");
            } else {
                return Resp.RespServerFail("新增解释失败");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail(e.getMessage());
        }
    }

    // 新增问题
    @RequestMapping(value = "/api/question", method = RequestMethod.POST)
    public Object addQuestion(@RequestAttribute Long userId, @RequestBody Map<String, String> payload) {
        if (payload.get("content") == null || payload.get("wikiId") == null) {
            return Resp.RespReqFail("缺少请求参数");
        }

        Long wikiId = Long.valueOf(payload.get("wikiId"));

        // 查看wikiId是否有对应词条
        Wiki wikiParent = wikiMapper.selectByPrimaryKey(wikiId);
        if (wikiParent == null) {
            return Resp.RespReqFail("请求参数错误");
        }

        Question question = new Question();
        question.setContent(payload.get("content"));
        question.setWikiId(wikiId);
        question.setCreaterId(userId);
        question.setGmtCreate(new Date());

        try {
            Integer succ = questionMapper.insertSelective(question);
            if (succ == 1) {
                return Resp.RespSucc("ok");
            } else {
                return Resp.RespServerFail("新增问题失败");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail(e.getMessage());
        }
    }

    // 新增回答
    @RequestMapping(value = "/api/answer", method = RequestMethod.POST)
    public String addAnswer(@RequestAttribute Long userId, @RequestBody Map<String, String> payload) {
        return Resp.RespSucc("ok");
    }

    // 评价解释(点赞或踩, enum type{up/down})
    @RequestMapping(value = "/api/explain/action", method = RequestMethod.POST)
    public String explainAction(@RequestAttribute Long userId, @RequestBody Map<String, String> payload) {
        return Resp.RespSucc("ok");
    }

    // 收藏解释
    @RequestMapping(value = "/api/explain_collect", method = RequestMethod.POST)
    public String explainCollect(@RequestAttribute Long userId, @RequestBody Map<String, String> payload) {
        return Resp.RespSucc("ok");
    }

    // 分享解释(留)
    @RequestMapping(value = "/api/explain_share", method = RequestMethod.POST)
    public String explainShare() {
        return Resp.RespSucc("ok");
    }

    // 评价回答(点赞或踩, enum type{up/down})
    @RequestMapping(value = "/api/answer/action", method = RequestMethod.POST)
    public String answerAction(@RequestAttribute Long userId, @RequestBody Map<String, String> payload) {
        return Resp.RespSucc("ok");
    }

    // 收藏回答
    @RequestMapping(value = "/api/answer_collect", method = RequestMethod.POST)
    public String answerCollect() {
        return Resp.RespSucc("ok");
    }

    // 搜索词条(留)
    @RequestMapping(value = "/oapi/wiki/search")
    public String searchWikis(@RequestParam String keyword) {
        return Resp.RespSucc("ok");
    }

    // 搜索问题
    @RequestMapping(value = "/oapi/question/search")
    public String searchQuestions(@RequestParam String keyword) {
        return Resp.RespSucc("ok");
    }

    // web词条首页信息
    @RequestMapping(value = "/oapi/wiki/web")
    public String wikiWeb() {
        return Resp.RespSucc("ok");
    }

    // app词条首页信息(留)，根据id获取词条信息
    @RequestMapping(value = "/oapi/wiki/app")
    public String wikiApp() {
        return Resp.RespSucc("ok");
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
        return Resp.RespSucc("ok");
    }
}
