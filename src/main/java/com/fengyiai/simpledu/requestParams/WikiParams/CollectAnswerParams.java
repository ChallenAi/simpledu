package com.fengyiai.simpledu.requestParams.WikiParams;

import javax.validation.constraints.NotNull;

public class CollectAnswerParams {
    @NotNull
    private Long answerId;

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
