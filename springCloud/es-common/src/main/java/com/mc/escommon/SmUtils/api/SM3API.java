package com.mc.escommon.SmUtils.api;

import com.mc.escommon.SmUtils.smutils.SM3Util;

/**
 * @version 1.0
 * @Author machao
 * @Date 2024/12/23 13:45
 * @注释
 * 该接口里使用sm3进行数据的操作
 * 计算摘要
 * 验证摘要
 */
public class SM3API {
    //sm3计算摘要
    public static String hashS(String data){
        byte[] p=hexStringToByteArray(data);
        byte[] s= SM3Util.hash(p);
        String q=toHexString(s);
        return q;
    }


    //sm3验证摘要
    public static boolean VH(String data,String hash){
        byte[] d=hexStringToByteArray(data);
        byte[] h=hexStringToByteArray(hash);
        return SM3Util.verify(d,h);
    }

    // 将十六进制字符串转换为 byte 数组
    private static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        if (length % 2 != 0) {
            hexString+="0";
            length+=1;
        }
        byte[] byteArray = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            byteArray[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return byteArray;
    }

    // 将 byte 数组转换为十六进制字符串
    private static String toHexString(byte[] byteArray) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : byteArray) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

}
