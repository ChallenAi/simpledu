package com.fengyiai.simpledu.controller;

import com.fengyiai.simpledu.mapper.UserMapper;
import com.fengyiai.simpledu.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // 根据id获取用户信息
    @RequestMapping(value = "/oapi/user", produces="application/json;charset=UTF-8")
    public Map<String, Object> getUserInfo(@RequestParam String userId) {
        User data = userMapper.selectByPrimaryKey(Long.valueOf(userId));
        Map<String, Object> resp = new HashMap<>();
        resp.put("code", HttpStatus.OK);
        resp.put("data", data);
        return resp;
    }

    // 关注用户(留)
    @RequestMapping(value = "/api/user/follow", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String followUser() {
        return "ok";
    }

    // 取消关注用户(留)
    @RequestMapping(value = "/api/user/unfollow", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String unfollowUser() {
        return "ok";
    }

}
