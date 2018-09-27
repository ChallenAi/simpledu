package com.fengyiai.simpledu.service;

import org.springframework.stereotype.Service;

import java.util.Map;

public interface WikiService {

    // 把keyword作为wiki name查询wiki，如果找到，返回wiki
    // 如果没有找到，使用keyword模糊匹配词条，返回wikis
    public Map<String, Object> searchWikiOrWikisByKeyword(String keyword);
}
