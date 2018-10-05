package com.fengyiai.simpledu.mapper;

import com.fengyiai.simpledu.model.Review;

import java.util.List;
import java.util.Map;

public interface ReviewMapper {
    int deleteByPrimaryKey(Long reviewId);

    int insert(Review record);

    int insertSelective(Review record);

    Review selectByPrimaryKey(Long reviewId);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);

    List<Review> listReviewByResourceTypeIdAndId(Map params);
}