package com.fengyiai.simpledu.mapper;

import com.fengyiai.simpledu.model.WikiCollect;

public interface WikiCollectMapper {
    int deleteByPrimaryKey(Long wikiCollectId);

    int insert(WikiCollect record);

    int insertSelective(WikiCollect record);

    WikiCollect selectByPrimaryKey(Long wikiCollectId);

    int updateByPrimaryKeySelective(WikiCollect record);

    int updateByPrimaryKey(WikiCollect record);
}