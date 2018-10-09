package com.fengyiai.simpledu.controller;

import com.fengyiai.simpledu.exception.CtxException;
import com.fengyiai.simpledu.model.Answer;
import com.fengyiai.simpledu.model.Explain;
import com.fengyiai.simpledu.model.Question;
import com.fengyiai.simpledu.model.Wiki;
import com.fengyiai.simpledu.service.WikiService;
import com.fengyiai.simpledu.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class CommonController {

    @Autowired
    private WikiService wikiService;

    @RequestMapping(value = "/ping", produces="application/json;charset=UTF-8")
    public String pong() {
        return Resp.RespData("pong");
    }

    // 根据关键词搜索所有资源
    @RequestMapping(value = "/oapi/search", produces="application/json;charset=UTF-8")
    public String searchResources(@RequestParam String keyword) {
        String resp;

        // 返回值{find; wiki或wikis}
        // 如果是由匹配到的wiki(isFind==true),直接用wikiId并发取解释/问题和回答；await都完成(用latch)并整合结果返回resu
        // 如果是wikis,即find是false, 直接返回wikis列表，用于展示搜索结果
        Map<String, Object> wikiResu = wikiService.searchWikiOrWikisByKeyword(keyword);

        if ((Boolean) wikiResu.get("isFind")) {
            final Wiki wiki = (Wiki) ((Map) wikiResu.get("data")).get("wiki");
            Map<String, Object> data = new HashMap<>();
            data.put("wiki", wiki);
            // latch计数器
            CountDownLatch latch = new CountDownLatch(2);
            // 线程池大小
            ExecutorService pool = Executors.newFixedThreadPool(2);
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<Question> questions = wikiService.getQuestionListByWikiId(wiki.getWikiId(), 3, 0);
                        // 拉取所有回答，并拼接回答
                        List<Long> questionIds = new ArrayList<>();
                        questions.forEach(i -> {
                            questionIds.add(i.getQuestionId());
                        });
                        List<Answer> answers = wikiService.getAnswerListByQuestionIdList(questionIds);
                        HashMap<Long, LinkedList> idIndexMapper = new HashMap<>();
                        answers.forEach((Answer answer) -> {
                            if (idIndexMapper.get(answer.getQuestionId()) != null) {
                                idIndexMapper.get(answer.getQuestionId()).add(answer);
                            } else {
//                                LinkedList<Answer> temp = new LinkedList<>();
//                                temp.add(answer);
                                idIndexMapper.put(answer.getQuestionId(), new LinkedList<Answer>() {{ add(answer); }});
                            }
                        });
                        questions.forEach((Question q) -> {
                            q.setAnswers(idIndexMapper.get(q.getQuestionId()));
                        });
                        data.put("questions", questions);
//                        data.put("answers", answers);
                    } catch (Exception e) {
                        data.put("error", true);
                        data.put("msg", e.getMessage());
                    } finally {
                        latch.countDown();
                    }
                }
            });
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<Explain> explains = wikiService.getExplainListByWikiId(wiki.getWikiId(), 3, 0);
                        data.put("explains", explains);
                    } catch (Exception e) {
                        data.put("error", true);
                        data.put("msg", e.getMessage());
                    } finally {
                        latch.countDown();
                    }
                }
            });
            try {
                latch.await();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            // 如果有线程执行错误
            if (data.get("error") != null) {
                System.out.println(data.get("msg"));
                throw new CtxException(500, "服务查询数据错误");
            }
            resp = Resp.RespData(data);
        } else {
            resp = Resp.RespData(wikiResu.get("data"));
        }

        return resp;
    }


    @RequestMapping(value = "/", produces="application/json;charset=UTF-8")
    public String index() {
//        Integer a = 1;
//        if (a != 2) {
//            throw new CtxException(123, "自定义错误");
//        }

        return Resp.RespSucc();
    }
}
