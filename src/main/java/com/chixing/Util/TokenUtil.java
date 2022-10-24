package com.chixing.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chixing.pojo.Customer;
import com.chixing.pojo.CustomerTokenDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 小番茄
 * @date 2022/10/1111:28
 */
public class TokenUtil {
    //token到期时间60s
    private static final long EXPIRE_TIME=600*1000;
    //密钥盐
    private static final String TOKEN_SECRET="123456qwertyuiop789";

    /**
     * 创建一个token
     * @param customerTokenDTO
     * @return
     */
    public static String sign(CustomerTokenDTO customerTokenDTO){
        System.out.println("sign customer:" + customerTokenDTO);
        String token = null;
        try{
            Map<String,Object> header = new HashMap<>(2);
            header.put("type","jwt");
            header.put("alg","HS256");
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("token", JSONObject.toJSONString(customerTokenDTO))//存放数据
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException je) {
            je.printStackTrace();
        }
        return token;
    }

    /**
     * 对token进行严重
     * @param token
     * @return
     */
    public static CustomerTokenDTO parseToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        String tokenInfo = decodedJWT.getClaim("token").asString();
        CustomerTokenDTO customerTokenDTO = JSON.parseObject(tokenInfo, CustomerTokenDTO.class);
        System.out.println("获得到token中的信息是:"+customerTokenDTO);
        return customerTokenDTO;
    }

}
