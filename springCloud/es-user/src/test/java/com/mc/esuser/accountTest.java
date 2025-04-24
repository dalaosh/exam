package com.mc.esuser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author machao
 * * @datetime 2025/1/15 23:19
 * * inform
 */
public class accountTest {
    public static void main(String[] args) {
        // 获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = sdf.format(new Date());

        // 生成随机数，范围 000 到 999
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        String randomStr = String.format("%03d", randomNumber);

        // 生成账号
        String account = timeStamp + randomStr;
        System.out.println("生成的账号: " + account);
    }
}
