package com.fengyiai.simpledu.util;
import com.fengyiai.simpledu.exception.CtxException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

public class JwtUtil {
    // 注意私月千万别达成中文，会抛错java.lang.ArrayIndexOutOfBoundsException难排查
    private final static String base64EncodedSecretKey = "test"; //私钥

    // 过期时间,测试使用100000分钟(2个月左右)
    private final static long TOKEN_EXP = 1000 * 60 * 100000;

    public static String getToken(String userId, boolean faked) {
        return Jwts
                .builder()
                .setSubject(userId)
                .claim("faked", faked) // 自定义字段名
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP))
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    public static Claims checkToken(String token) throws ServletException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
            System.out.println(claims);
            return claims;
        } catch (ExpiredJwtException e1) {
            throw new CtxException(400, "登录信息过期，请重新登录");
        } catch (Exception e2) {
            throw new CtxException(400, "用户未登录，请重新登录");
        }
    }

}
