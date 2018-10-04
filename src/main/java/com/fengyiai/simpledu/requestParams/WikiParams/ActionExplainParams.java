package com.fengyiai.simpledu.requestParams.WikiParams;

import javax.validation.constraints.NotNull;

public class ActionExplainParams {
    @NotNull
    private Long explainId;

    @NotNull
    private String type;

    public Long getExplainId() {
        return explainId;
    }

    public void setExplainId(Long explainId) {
        this.explainId = explainId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
