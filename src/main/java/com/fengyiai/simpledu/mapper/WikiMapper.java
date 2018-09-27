package com.fengyiai.simpledu.mapper;

import com.fengyiai.simpledu.model.Wiki;

public interface WikiMapper {
    int deleteByPrimaryKey(Long wikiId);

    int insert(Wiki record);

    int insertSelective(Wiki record);

    Wiki selectByPrimaryKey(Long wikiId);

    int updateByPrimaryKeySelective(Wiki record);

    int updateByPrimaryKey(Wiki record);
}