package com.fengyiai.simpledu.model;

public class WikiRank {
    private Long wikiRankId;

    private Long userId;

    private Long resourceId;

    private Long resourceTypeId;

    private Long rankTypeId;

    public Long getWikiRankId() {
        return wikiRankId;
    }

    public void setWikiRankId(Long wikiRankId) {
        this.wikiRankId = wikiRankId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getResourceTypeId() {
        return resourceTypeId;
    }

    public void setResourceTypeId(Long resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }

    public Long getRankTypeId() {
        return rankTypeId;
    }

    public void setRankTypeId(Long rankTypeId) {
        this.rankTypeId = rankTypeId;
    }
}