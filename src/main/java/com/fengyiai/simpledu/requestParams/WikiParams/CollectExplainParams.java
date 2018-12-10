package com.fengyiai.simpledu.requestParams.WikiParams;

import javax.validation.constraints.NotNull;

public class CollectExplainParams {
    @NotNull
    private Long explainId;

    public Long getExplainId() {
        return explainId;
    }

    public void setExplainId(Long explainId) {
        this.explainId = explainId;
    }
}
