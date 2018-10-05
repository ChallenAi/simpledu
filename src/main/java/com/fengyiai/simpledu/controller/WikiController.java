package com.fengyiai.simpledu.controller;

import com.fengyiai.simpledu.mapper.*;
import com.fengyiai.simpledu.model.*;
import com.fengyiai.simpledu.requestParams.WikiParams.*;
import com.fengyiai.simpledu.util.Constants;
import com.fengyiai.simpledu.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.Valid;
import java.util.Date;

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

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private WikiRankMapper wikiRankMapper;

    @Autowired
    private WikiCollectMapper wikiCollectMapper;

    // 新增词条
    @RequestMapping(value = "/api/wiki", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String addWiki(@RequestAttribute Long userId, @Valid @RequestBody AddWikiParams p, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Resp.RespReqFail("参数错误");
        }

        final Long parentId = p.getParentId();

        // 如果不是根词条，就需要验证他的父标签是否存在
        if (parentId != 0L) {
            Wiki wikiParent = wikiMapper.selectByPrimaryKey(parentId);
            if (wikiParent == null) {
                return Resp.RespReqFail("请求参数错误");
            }
        }

        Wiki wiki = new Wiki();
        wiki.setName(p.getName());
        wiki.setAlias(p.getAlias());
        wiki.setEnglishName(p.getEnglishName());
        wiki.setCreaterId(userId);
        wiki.setGmtCreate(new Date());
        wiki.setGmtModified(new Date());
        wiki.setParentId(parentId);

        try {
            Integer succ = wikiMapper.insertSelective(wiki);
            if (succ == 1) {
                return Resp.RespSucc();
            } else {
                return Resp.RespServerFail("新增词条失败");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail(e.getMessage());
        }
    }

    // 新增解释
    @RequestMapping(value = "/api/explain", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public Object addExplain(@RequestAttribute Long userId, @Valid @RequestBody AddExplainParams p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Resp.RespReqFail("参数错误");
        }

        Explain explain = new Explain();
        explain.setContent(p.getContent());
        explain.setWikiId(p.getWikiId());
        explain.setCreaterId(userId);
        explain.setGmtCreate(new Date());
        explain.setGmtModified(new Date());

        try {
            Integer succ = explainMapper.insertSelective(explain);
            if (succ == 1) {
                return Resp.RespSucc();
            } else {
                return Resp.RespServerFail("新增解释失败");
            }
        } catch (DataIntegrityViolationException ex) {
            return Resp.RespReqFail("无效的wikiId");
        } catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail(e.getMessage());
        }
    }

    // 新增问题
    @RequestMapping(value = "/api/question", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public Object addQuestion(@RequestAttribute Long userId, @RequestBody AddQuestionParams p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Resp.RespServerFail("参数错误");
        }

        Question question = new Question();
        question.setContent(p.getContent());
        question.setWikiId(p.getWikiId());
        question.setCreaterId(userId);
        question.setGmtCreate(new Date());

        try {
            Integer succ = questionMapper.insertSelective(question);
            if (succ == 1) {
                return Resp.RespSucc();
            } else {
                return Resp.RespServerFail("新增问题失败");
            }
        } catch (DataIntegrityViolationException ex) {
            return Resp.RespReqFail("无效的wikiId");
        }  catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail(e.getMessage());
        }
    }

    // 新增回答
    @RequestMapping(value = "/api/answer", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String addAnswer(@RequestAttribute Long userId, @Valid @RequestBody AddAnswerParams p, BindingResult bindingResult) {

//        // 查看questionId是否有对应问题，还是靠数据库fk保证吧，因为wiki都是一个整体服务
//        Question question = questionMapper.selectByPrimaryKey(questionId);
//        if (question == null) {
//            return Resp.RespReqFail("请求参数错误");
//        }

        if (bindingResult.hasErrors()) {
            return Resp.RespServerFail("参数错误");
        }

        Answer answer = new Answer();
        answer.setContent(p.getContent());
        answer.setQuestionId(p.getQuestionId());
        answer.setCreaterId(userId);
        answer.setGmtCreate(new Date());
        answer.setGmtModified(new Date());

        try {
            Integer succ = answerMapper.insertSelective(answer);
            if (succ == 1) {
                return Resp.RespSucc();
            } else {
                return Resp.RespServerFail("新增回答失败");
            }
        } catch (DataIntegrityViolationException ex) {
            return Resp.RespReqFail("无效的问题id");
        } catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail(e.getMessage());
        }
    }

    // 评价解释(点赞或踩, enum type{action_up/action_down})
    @RequestMapping(value = "/api/explain/action", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String explainAction(@RequestAttribute Long userId, @RequestBody ActionExplainParams p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Resp.RespReqFail("参数错误");
        }

        final String type = p.getType();

        if (!type.equals("action_up") && !type.equals("action_down")) {
            return Resp.RespReqFail("参数错误");
        }

        // 验证explainId是否对应有解释
        Explain explain = explainMapper.selectByPrimaryKey(p.getExplainId());
        if (explain == null) {
            return Resp.RespReqFail("explainId参数错误");
        }

        WikiRank wikiRank = new WikiRank();
        wikiRank.setResourceId(p.getExplainId());
        // 资源类型id是wiki类型的id
        wikiRank.setResourceTypeId(Constants.mapParamsToId("explain"));
        wikiRank.setRankTypeId(Constants.mapParamsToId(type));
        wikiRank.setUserId(userId);

        try {
            Integer succ = wikiRankMapper.insertSelective(wikiRank);
            if (succ == 1) {
                return Resp.RespSucc();
            } else {
                return Resp.RespServerFail("评价失败");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail(e.getMessage());
        }
    }

    // 收藏解释
    @RequestMapping(value = "/api/explain_collect", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String explainCollect(@RequestAttribute Long userId, @RequestBody ActionExplainParams p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Resp.RespReqFail("参数错误");
        }

        // 验证explainId是否对应有解释
        Explain explain = explainMapper.selectByPrimaryKey(p.getExplainId());
        if (explain == null) {
            return Resp.RespReqFail("explainId参数错误");
        }

        WikiCollect wikiCollect = new WikiCollect();
        wikiCollect.setResourceId(p.getExplainId());
        // 资源类型id是wiki类型的id
        wikiCollect.setResourceTypeId(Constants.mapParamsToId("explain"));
        wikiCollect.setUserId(userId);

        try {
            Integer succ = wikiCollectMapper.insertSelective(wikiCollect);
            if (succ == 1) {
                return Resp.RespSucc();
            } else {
                return Resp.RespServerFail("收藏失败");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail(e.getMessage());
        }
    }

    // 分享解释(留)
    @RequestMapping(value = "/api/explain_share", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String explainShare() {
        return Resp.RespSucc();
    }

    // 评价回答(点赞或踩, enum type{up/down})
    @RequestMapping(value = "/api/answer/action", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String answerAction(@RequestAttribute Long userId, @RequestBody ActionAnsweParams p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Resp.RespReqFail("参数错误");
        }

        final String type = p.getType();

        if (type.equals("action_up") && type.equals("action_down")) {
            return Resp.RespReqFail("参数错误");
        }

        // 验证explainId是否对应有解释
        Answer answer = answerMapper.selectByPrimaryKey(p.getAnswerId());
        if (answer == null) {
            return Resp.RespReqFail("answerId参数错误");
        }

        WikiRank wikiRank = new WikiRank();
        wikiRank.setResourceId(p.getAnswerId());
        // 资源类型id是wiki类型的id
        wikiRank.setResourceTypeId(Constants.mapParamsToId("answer"));
        wikiRank.setRankTypeId(Constants.mapParamsToId(type));
        wikiRank.setUserId(userId);

        try {
            Integer succ = wikiRankMapper.insertSelective(wikiRank);
            if (succ == 1) {
                return Resp.RespSucc();
            } else {
                return Resp.RespServerFail("评价失败");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail(e.getMessage());
        }
    }

    // 收藏回答
    @RequestMapping(value = "/api/answer_collect", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String answerCollect(@RequestAttribute Long userId, @RequestBody CollectAnswerParams p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Resp.RespReqFail("参数错误");
        }

        // 验证explainId是否对应有解释
        Explain explain = explainMapper.selectByPrimaryKey(p.getAnswerId());
        if (explain == null) {
            return Resp.RespReqFail("explainId参数错误");
        }

        WikiCollect wikiCollect = new WikiCollect();
        wikiCollect.setResourceId(p.getAnswerId());
        // 资源类型id是wiki类型的id
        wikiCollect.setResourceTypeId(Constants.mapParamsToId("explain"));
        wikiCollect.setUserId(userId);

        try {
            Integer succ = wikiCollectMapper.insertSelective(wikiCollect);
            if (succ == 1) {
                return Resp.RespSucc();
            } else {
                return Resp.RespServerFail("收藏失败");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail(e.getMessage());
        }
    }

    // 搜索词条(留)
    @RequestMapping(value = "/oapi/wiki/search", produces="application/json;charset=UTF-8")
    public String searchWikis(@RequestParam String keyword) {
        return Resp.RespSucc();
    }

    // 搜索问题
    @RequestMapping(value = "/oapi/question/search", produces="application/json;charset=UTF-8")
    public String searchQuestions(@RequestParam String keyword) {
        return Resp.RespSucc();
    }

    // web词条首页信息
    @RequestMapping(value = "/oapi/wiki/web", produces="application/json;charset=UTF-8")
    public String wikiWeb() {
        return Resp.RespSucc();
    }

    // app词条首页信息(留)，根据id获取词条信息
    @RequestMapping(value = "/oapi/wiki/app", produces="application/json;charset=UTF-8")
    public String wikiApp() {
        return Resp.RespSucc();
    }

    // 获取解释列表(点击加载更多)
    @RequestMapping(value = "/oapi/explain/list", produces="application/json;charset=UTF-8")
    public String getExplains() {
        // @RequestParam String pageSize, String pageNumber
//        List<Map> data = explainMapper.findExplainsByWikiId(1);
        return Resp.RespSucc();
    }

    // 获取问题列表(点击加载更多)
    @RequestMapping(value = "/oapi/question/list", produces="application/json;charset=UTF-8")
    public String getQuestions() {
        return Resp.RespSucc();
    }
}
