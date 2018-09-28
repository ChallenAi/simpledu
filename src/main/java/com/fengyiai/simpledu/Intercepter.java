package com.fengyiai.simpledu;

import com.fengyiai.simpledu.exception.CtxException;
import com.fengyiai.simpledu.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Intercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new CtxException(400, "用户未登录");
        }

        String token = authHeader.substring(7);

        Claims claims = JwtUtil.checkToken(token);

        request.setAttribute("userId",claims.getSubject());

        return true;
    }
}
