package com.fengyiai.simpledu.model;

import java.util.Date;

public class Wiki {
    private Long wikiId;

    private Long createrId;

    private String name;

    private Long parentId;

    private String treeCode;

    private Date gmtCreate;

    private Date gmtModified;

    private String description;

    private String alias;

    private String englishName;

    private Long view;

    private Integer finishRatio;

    private String relativeWikiIds;

    private String relativeArticleIds;

    private String username;

    public Long getWikiId() {
        return wikiId;
    }

    public void setWikiId(Long wikiId) {
        this.wikiId = wikiId;
    }

    public Long getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode == null ? null : treeCode.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    public Long getView() {
        return view;
    }

    public void setView(Long view) {
        this.view = view;
    }

    public Integer getFinishRatio() {
        return finishRatio;
    }

    public void setFinishRatio(Integer finishRatio) {
        this.finishRatio = finishRatio;
    }

    public String getRelativeWikiIds() {
        return relativeWikiIds;
    }

    public void setRelativeWikiIds(String relativeWikiIds) {
        this.relativeWikiIds = relativeWikiIds == null ? null : relativeWikiIds.trim();
    }

    public String getRelativeArticleIds() {
        return relativeArticleIds;
    }

    public void setRelativeArticleIds(String relativeArticleIds) {
        this.relativeArticleIds = relativeArticleIds == null ? null : relativeArticleIds.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}