package com.fengyiai.simpledu.requestParams.ReviewParams;

import javax.validation.constraints.NotNull;

public class AddReviewParams {
    @NotNull
    private String content;

    @NotNull
    private String resourceType;

    @NotNull
    private Long resourceId;

    // 回复谁，不必须
    private Long toUserId;
}
