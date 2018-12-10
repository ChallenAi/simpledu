package com.fengyiai.simpledu.requestParams.WikiParams;

import javax.validation.constraints.NotNull;

public class AddQuestionParams {
    @NotNull
    private String content;

    @NotNull
    private Long wikiId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getWikiId() {
        return wikiId;
    }

    public void setWikiId(Long wikiId) {
        this.wikiId = wikiId;
    }
}
