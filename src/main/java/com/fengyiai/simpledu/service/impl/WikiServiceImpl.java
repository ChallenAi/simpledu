package com.fengyiai.simpledu.service.impl;

import com.fengyiai.simpledu.mapper.AnswerMapper;
import com.fengyiai.simpledu.mapper.ExplainMapper;
import com.fengyiai.simpledu.mapper.QuestionMapper;
import com.fengyiai.simpledu.mapper.WikiMapper;
import com.fengyiai.simpledu.model.Answer;
import com.fengyiai.simpledu.model.Explain;
import com.fengyiai.simpledu.model.Question;
import com.fengyiai.simpledu.model.Wiki;
import com.fengyiai.simpledu.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WikiServiceImpl implements WikiService {
    @Autowired
    private WikiMapper wikiMapper;

    @Autowired
    private ExplainMapper explainMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

    public Map<String, Object> searchWikiOrWikisByKeyword(String keyword) {
        Map<String, Object> resu = new HashMap<>();
        Wiki wiki = wikiMapper.selectByWikiName(keyword);
        if (wiki != null) {
            resu.put("isFind", true);
            resu.put("data", new HashMap<String, Object>(){{
                put("wiki", wiki);
            }});
            return resu;
        } else {
            // 使用like匹配keyword
            List<Wiki> wikis = wikiMapper.selectWikisLikeName(keyword);
            resu.put("isFind", false);
            resu.put("data", new HashMap<String, Object>(){{
                put("wikis", wikis);
            }});
            return resu;
        }
    }

    public List<Explain> getExplainListByWikiId(Long wikiId, Integer limit, Integer offset) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("wikiId", wikiId);
        params.put("limit", limit);
        params.put("offset", offset);

        return explainMapper.selectByWikiId(params);
    }

    public List<Question> getQuestionListByWikiId(Long wikiId, Integer limit, Integer offset) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("wikiId", wikiId);
        params.put("limit", limit);
        params.put("offset", offset);

        return questionMapper.selectByWikiId(params);
    }

    public List<Answer> getAnswerListByQuestionIdList(List<Long> questionIds) {
        return answerMapper.selectByQuestionIds(questionIds);
    }
}
