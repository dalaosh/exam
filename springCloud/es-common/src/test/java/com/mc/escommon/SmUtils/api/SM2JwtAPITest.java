package com.mc.escommon.SmUtils.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author machao
 * @Date 2024/12/23 19:02
 * @注释
 */
class SM2JwtAPITest {
    public static void main(String[] args) {
        String a="F:\\ExamSystem\\SystemCode\\springCloud\\files\\sm2File\\admin\\202501162325247237\\user\\jwt.sm2.cer";
        String b="F:\\ExamSystem\\SystemCode\\springCloud\\files\\sm2File\\admin\\202501162325247237\\user\\jwt.sm2.cer";
        System.out.println(a.equals(b));
        String name="admin\\202501162325247237\\user";
        HashMap<String, String> map = new HashMap<>();
        map.put("name","Alice");
        map.put("age","23");
        map.put("专业","计算机科学与技术");

        SM2JwtAPI sm2JwtAPI=new SM2JwtAPI(name);
        String jwt = sm2JwtAPI.genToken(map, 1);

        Map<String, String> map1 = null;

        try {
            map1=sm2JwtAPI.verifyToken(jwt);
        }catch (TokenExpiredException e){//超时
            System.out.println("jwt超时");
            System.out.println(e.getMessage());
        }catch (JWTDecodeException e){//第一段或第二段错误
            System.out.println("jwt编码错误");
        }catch (SignatureVerificationException e){
            System.out.println("验签失败");//第三段错误
        }

        Date exp = JWT.decode(jwt).getClaim("exp").asDate();
        System.out.println("**********************jwt的生成与解析**********************");
        System.out.println("jwt生成："+jwt);
        System.out.println("jwt解析："+map1);
        System.out.println("时间："+exp);
        System.out.println("*****************************************************");



    }

}