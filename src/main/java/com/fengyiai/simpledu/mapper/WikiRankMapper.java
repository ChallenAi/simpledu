package com.fengyiai.simpledu.mapper;

import com.fengyiai.simpledu.model.WikiRank;

public interface WikiRankMapper {
    int deleteByPrimaryKey(Long wikiRankId);

    int insert(WikiRank record);

    int insertSelective(WikiRank record);

    WikiRank selectByPrimaryKey(Long wikiRankId);

    int updateByPrimaryKeySelective(WikiRank record);

    int updateByPrimaryKey(WikiRank record);
}