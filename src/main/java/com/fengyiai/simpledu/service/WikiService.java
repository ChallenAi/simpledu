package com.fengyiai.simpledu.service;

import com.fengyiai.simpledu.model.Answer;
import com.fengyiai.simpledu.model.Explain;
import com.fengyiai.simpledu.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface WikiService {

    public Map<String, Object> searchWikiOrWikisByKeyword(String keyword);

    public List<Explain> getExplainListByWikiId(Long wikiId, Integer limit, Integer offset);

    public List<Question> getQuestionListByWikiId(Long wikiId, Integer limit, Integer offset);

    public List<Answer> getAnswerListByQuestionIdList(List<Long> questionIds);
}
