package com.fengyiai.simpledu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    // 评论或回复评论
    @RequestMapping(value = "/api/review", method = RequestMethod.POST)
    public String addReview() {
        return "ok";
    }

    // 获取评论列表
    @RequestMapping(value = "/oapi/review/list")
    public String getReviews() {
        return "ok";
    }
}
