package com.fengyiai.simpledu.mapper;

import com.fengyiai.simpledu.model.Question;

import java.util.List;
import java.util.Map;

public interface QuestionMapper {
    int deleteByPrimaryKey(Long questionId);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Long questionId);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> selectByWikiId(Map params);
}