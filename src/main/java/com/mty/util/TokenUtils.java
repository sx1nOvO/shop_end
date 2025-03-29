package com.mty.util;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    //设置过期时间（毫秒为单位）
    public static final long EXPIRE_DATE=60*60*1000;
    //token秘钥（可以自定义一个随机字符串就行，避免中文）
    public static final String TOKEN_SECRET = "ZCEQIUBFKSJBFJH2020BQWE";


    /**
     *
     * @param username  传入用户名
     * @param password  传入密码
     * @return
     */
    public static String getToken(String username, String password, String type) {
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password和用户ID信息，生成签名，按照需求而定
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username", username).withClaim("type", type)
                    .withClaim("password", password).withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    /**
     * 校验Token是否过期
     * @param token
     * @return
     */
    public static boolean verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }


    /**
     * 解析Token
     * @param key     需要解析的key字符串（username、userId等等，就是携带数据生成token的Key）
     * @param token   传入token
     * @return
     */
    public static String getInfoByKey(String token,String key){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        }catch (JWTDecodeException e){
            e.printStackTrace();
        }
        return null;
    }



}
