package com.fengyiai.simpledu.controller;

import com.fengyiai.simpledu.util.Resp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    // 评论或回复评论
    @RequestMapping(value = "/api/review", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String addReview() {
        return Resp.RespSucc();
    }

    // 获取评论列表
    @RequestMapping(value = "/oapi/review/list", produces="application/json;charset=UTF-8")
    public String getReviews() {
        return Resp.RespSucc();
    }
}
