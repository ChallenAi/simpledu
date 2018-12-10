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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }
}
