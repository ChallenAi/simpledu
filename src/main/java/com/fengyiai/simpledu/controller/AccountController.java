package com.fengyiai.simpledu.controller;

import com.fengyiai.simpledu.exception.CtxException;
import com.fengyiai.simpledu.mapper.UserMapper;
import com.fengyiai.simpledu.model.User;
import com.fengyiai.simpledu.util.JwtUtil;
import com.fengyiai.simpledu.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    private UserMapper userMapper;

    // 登录: 模拟账户使用hash登录
    @RequestMapping(value = "/oapi/hash_login", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String loginHash(@RequestParam String hash) {
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
            Map<String, Object> data = new HashMap<>();
            data.put("user", user);
            data.put("token", token);
            return Resp.RespData(data);
        }
    }

    // 获取验证码
    @RequestMapping(value = "/oapi/sms", produces="application/json;charset=UTF-8")
    public String getSms() {
        return Resp.RespSucc();
    }

    // 登录: 手机号验证码
    @RequestMapping(value = "/oapi/sms", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String loginSms() {
        return Resp.RespSucc();
    }

    // 登录：微信
    @RequestMapping(value = "/oapi/wechat", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String loginWechat() {
        return Resp.RespSucc();
    }

    // 登录：微博
    @RequestMapping(value = "/oapi/weibo", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String loginWeibo() {
        return Resp.RespSucc();
    }

    // 登录：qq
    @RequestMapping(value = "/oapi/qq", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String loginQQ() {
        return Resp.RespSucc();
    }

    // 注册：管理员(添加管路员)(内部运营平台)
    @RequestMapping(value = "/api/admin", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String registerAdmin() {
        return Resp.RespSucc();
    }

    // 注册：管理员账户的附属账户
    @RequestMapping(value = "/api/admin/user", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String fakeUser() {
        return Resp.RespSucc();
    }

    // 登录：附属账户 (以管理员账户登录附属账户)
    @RequestMapping(value = "/api/admin/user", produces="application/json;charset=UTF-8")
    public String enterAdmin() {
        return Resp.RespSucc();
    }
}
