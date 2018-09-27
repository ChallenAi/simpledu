package com.fengyiai.simpledu.service.impl;

import com.fengyiai.simpledu.mapper.WikiMapper;
import com.fengyiai.simpledu.model.Wiki;
import com.fengyiai.simpledu.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WikiServiceImpl implements WikiService {
    @Autowired
    private WikiMapper wikiMapper;

    public Map<String, Object> searchWikiOrWikisByKeyword(String keyword) {
        Map<String, Object> resu = new HashMap<>();
        Wiki wiki = wikiMapper.selectByWikiName(keyword);
        if (wiki != null) {
            resu.put("isFind", true);
            resu.put("data", new HashMap<String, Object>(){{
                put("wiki", wiki);
            }});
            return resu;
        } else {
            // 使用like匹配keyword
            List<Wiki> wikis = wikiMapper.selectWikisLikeName(keyword);
            resu.put("isFind", false);
            resu.put("data", new HashMap<String, Object>(){{
                put("wikis", wikis);
            }});
            return resu;
        }
    }
}
