package com.fengyiai.simpledu.mapper;

import com.fengyiai.simpledu.model.Answer;

public interface AnswerMapper {
    int deleteByPrimaryKey(Long answerId);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Long answerId);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
}