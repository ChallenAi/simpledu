package com.fengyiai.simpledu.mapper;

import com.fengyiai.simpledu.model.Explain;

public interface ExplainMapper {
    int deleteByPrimaryKey(Long explainId);

    int insert(Explain record);

    int insertSelective(Explain record);

    Explain selectByPrimaryKey(Long explainId);

    int updateByPrimaryKeySelective(Explain record);

    int updateByPrimaryKey(Explain record);
}