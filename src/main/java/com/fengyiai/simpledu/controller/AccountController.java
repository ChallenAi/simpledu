package com.fengyiai.simpledu.controller;

import com.fengyiai.simpledu.exception.CtxException;
import com.fengyiai.simpledu.mapper.UserMapper;
import com.fengyiai.simpledu.model.User;
import com.fengyiai.simpledu.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    private UserMapper userMapper;

    // 登录: 模拟账户使用hash登录
    @RequestMapping(value = "/oapi/hash_login", method = RequestMethod.POST)
    public Map<String, Object> loginHash(@RequestParam String hash) {
        // 根据hash兑换userId

        // mock userId = 5 ，是受限子用户
        final Long userId = 5L;

        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            System.out.println(user);
            throw new CtxException(402, "错误的登录码");
        } else if (!user.getIsFake()) {
            System.out.println("=====");
            throw new CtxException(402, "错误的登录码");
        } else {
            String token = JwtUtil.getToken(user.getUserId().toString(), true);
            Map<String, Object> resu = new Hashtable<>();
            resu.put("code", 0);
            resu.put("data", user);
            resu.put("token", token);
            return resu;
        }
    }

    // 获取验证码
    @RequestMapping(value = "/oapi/sms")
    public String getSms() {
        return "ok";
    }

    // 登录: 手机号验证码
    @RequestMapping(value = "/oapi/sms", method = RequestMethod.POST)
    public String loginSms() {
        return "ok";
    }

    // 登录：微信
    @RequestMapping(value = "/oapi/wechat", method = RequestMethod.POST)
    public String loginWechat() {
        return "ok";
    }

    // 登录：微博
    @RequestMapping(value = "/oapi/weibo", method = RequestMethod.POST)
    public String loginWeibo() {
        return "ok";
    }

    // 登录：qq
    @RequestMapping(value = "/oapi/qq", method = RequestMethod.POST)
    public String loginQQ() {
        return "ok";
    }

    // 注册：管理员(添加管路员)(内部运营平台)
    @RequestMapping(value = "/api/admin", method = RequestMethod.POST)
    public String registerAdmin() {
        return "ok";
    }

    // 注册：管理员账户的附属账户
    @RequestMapping(value = "/api/admin/user", method = RequestMethod.POST)
    public String fakeUser() {
        return "ok";
    }

    // 登录：附属账户 (以管理员账户登录附属账户)
    @RequestMapping(value = "/api/admin/user")
    public String enterAdmin() {
        return "ok";
    }
}
