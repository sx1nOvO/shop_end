package com.mty.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mty.entity.User;
import com.mty.service.UserService;
import com.mty.util.Result;
import com.mty.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();

        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 执行认证
        if (token == null) {
            returnJson(response,"无token，请重新登录");
            return false;
        }
        // 验证 token 是否过期
        boolean isVarify = TokenUtils.verifyToken(token);
        if(!isVarify){
            returnJson(response,"token过期，请重新登陆");
            return false;
        }
        //校验
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TokenUtils.TOKEN_SECRET)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            returnJson(response,"认证失败，请重新登录");
            return false;
        }
        return true;
    }


    private void returnJson(HttpServletResponse response, String message){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            Result ret = new Result();
            ret.setCode(-1);
            ret.setMsg(message);
            writer.print(JSON.toJSONString(ret, SerializerFeature.WriteMapNullValue));
        } catch (IOException e){
            System.out.println("拦截器输出流异常:"+e);
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}