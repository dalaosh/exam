package com.mc.escommon.SmUtils.api;

/**
 * @version 1.0
 * @Author machao
 * @Date 2024/12/23 13:47
 * @注释
 */
class SM3APITest {
    public static void main(String[] args) {
        //信息
        String message="你们好";

        //计算摘要
        String s1 = SM3API.hashS(message);
        String s2 = SM3API.hashS(message);
        String s3 = SM3API.hashS(message);
        String s4 = SM3API.hashS(message);


        //验证摘要
        boolean vh = SM3API.VH(message, s1);

        System.out.println("**********************签名和验签**********************");
        System.out.println("数字摘要："+s1);
        System.out.println("数字摘要："+s2);
        System.out.println("数字摘要："+s3);
        System.out.println("数字摘要："+s4);


        System.out.println("验证结果："+vh);
        System.out.println("*****************************************************");

    }

}