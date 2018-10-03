package com.fengyiai.simpledu.requestParams.WikiParams;

import javax.validation.constraints.NotNull;

public class AddAnswerParams {
    @NotNull
    private String content;

    @NotNull
    private Long questionId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
