package com.fengyiai.simpledu.model;

public class WikiCollect {
    private Long wikiCollectId;

    private Long userId;

    private Long resourceId;

    private Long resourceTypeId;

    public Long getWikiCollectId() {
        return wikiCollectId;
    }

    public void setWikiCollectId(Long wikiCollectId) {
        this.wikiCollectId = wikiCollectId;
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
}