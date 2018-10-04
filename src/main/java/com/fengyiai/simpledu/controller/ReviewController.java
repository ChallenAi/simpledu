package com.fengyiai.simpledu.controller;

import com.fengyiai.simpledu.requestParams.ReviewParams.AddReviewParams;
import com.fengyiai.simpledu.requestParams.WikiParams.AddWikiParams;
import com.fengyiai.simpledu.util.Resp;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ReviewController {

    // 评论或回复评论
    @RequestMapping(value = "/api/review", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String addReview(@RequestAttribute Long userId, @Valid @RequestBody AddWikiParams p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Resp.RespReqFail("参数错误");
        }
        return Resp.RespSucc();
    }

    // 获取评论列表
    @RequestMapping(value = "/oapi/review/list", produces="application/json;charset=UTF-8")
    public String getReviews() {
        return Resp.RespSucc();
    }
}
