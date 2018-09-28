package com.fengyiai.simpledu.service;

import com.fengyiai.simpledu.model.Answer;
import com.fengyiai.simpledu.model.Explain;
import com.fengyiai.simpledu.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

// wiki服务，包含词条，解释，问题回答等服务。Mapper是分开的，服务是以wiki整体为粒度的
public interface WikiService {

    // 把keyword作为wiki name查询wiki，如果找到，返回wiki
    // 如果没有找到，使用keyword模糊匹配词条，返回wikis
    // 返回值{find; wiki或wikis}
    public Map<String, Object> searchWikiOrWikisByKeyword(String keyword);

    public List<Explain> getExplainListByWikiId(Long wikiId, Integer limit, Integer offset);

    public List<Question> getQuestionListByWikiId(Long wikiId, Integer limit, Integer offset);

    public List<Answer> getAnswerListByQuestionIdList(List<Long> questionIds);
}
