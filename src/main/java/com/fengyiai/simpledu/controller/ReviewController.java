package com.fengyiai.simpledu.controller;

import com.fengyiai.simpledu.mapper.ReviewMapper;
import com.fengyiai.simpledu.model.Review;
import com.fengyiai.simpledu.requestParams.ReviewParams.AddReviewParams;
import com.fengyiai.simpledu.requestParams.WikiParams.AddWikiParams;
import com.fengyiai.simpledu.util.Constants;
import com.fengyiai.simpledu.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewMapper reviewMapper;

    // 评论或回复评论
    @RequestMapping(value = "/api/review", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String addReview(@RequestAttribute Long userId, @Valid @RequestBody AddReviewParams p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Resp.RespReqFail("参数错误");
        }

        final String type = p.getResourceType();
        if (!type.equals("explain") && !type.equals("answer")) {
            return Resp.RespReqFail("参数错误");
        }

        // 验证resourceId和toUserId是否正确

        Review review = new Review() {{
            setContent(p.getContent());
            setCreaterId(userId);
            setGmtCreate(new Date());
            setToUserId(p.getToUserId());
            setResourceId(p.getResourceId());
            // 重新generate mapper
            setResourceTypeId(Constants.mapParamsToId(p.getResourceType()));
        }};

        // 写入review
        try {
            Integer succ = reviewMapper.insertSelective(review);
            if (succ == 1) {
                return Resp.RespSucc();
            } else {
                return Resp.RespServerFail("评论失败");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail(e.getMessage());
        }
    }

    // 获取评论列表
    @RequestMapping(value = "/oapi/review/list", produces="application/json;charset=UTF-8")
    public String getReviews(@RequestParam Long resourceId, String resourceType) {
        if (!resourceType.equals("explain") && !resourceType.equals("answer")) {
            return Resp.RespReqFail("参数错误");
        }

        HashMap<String, Long> params = new HashMap<>();
        params.put("resourceTypeId", Constants.mapParamsToId(resourceType));
        params.put("resourceId", Long.valueOf(resourceId));

        try {
            System.out.println(params);
            List<Review> reviews = reviewMapper.listReviewByResourceTypeIdAndId(params);
            return Resp.RespData(reviews);
        } catch (Exception e) {
            System.out.println(e);
            return Resp.RespServerFail("获取评论失败");
        }
    }
}
