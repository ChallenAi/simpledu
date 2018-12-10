package com.fengyiai.simpledu.mapper;

import com.fengyiai.simpledu.model.Wiki;

import java.util.List;

public interface WikiMapper {
    int deleteByPrimaryKey(Long wikiId);

    int insert(Wiki record);

    int insertSelective(Wiki record);

    Wiki selectByPrimaryKey(Long wikiId);

    int updateByPrimaryKeySelective(Wiki record);

    int updateByPrimaryKey(Wiki record);

    Wiki selectByWikiName(String keyword);

    List<Wiki> selectWikisLikeName(String keyword);
}