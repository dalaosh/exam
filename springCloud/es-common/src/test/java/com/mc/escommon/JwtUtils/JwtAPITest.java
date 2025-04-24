package com.mc.escommon.JwtUtils;

import com.mc.escommon.result.TimeEnum;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author machao
 * * @datetime 2025/1/12 15:51
 * * inform
 */
class JwtAPITest {
    public static void main(String[] args) {
        String token=JwtAPI.getToken("123","machao","token_key", TimeEnum.HOURS.time);
        System.out.println(JwtAPI.verify(token));
        System.out.println("______");
        System.out.println(JwtAPI.JWTGetName(token));
    }

}