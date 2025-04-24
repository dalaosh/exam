package com.mc.esuser;

import java.util.Random;

/**
 * @author machao
 * * @datetime 2025/1/9 21:28
 * * inform
 */
public class Timetest {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(String.format("%06d", random.nextInt(1000000)));
        // 获取旧的时间戳
        long oldTimestamp = System.currentTimeMillis() - 10 * 60 * 1000; // 10分钟前
        // 获取新的时间戳
        long newTimestamp = System.currentTimeMillis();

        // 计算时间差（毫秒）
        long differenceInMillis = newTimestamp - oldTimestamp;

        // 将时间差转换为其他单位
        long differenceInSeconds = differenceInMillis / 1000;
        long differenceInMinutes = differenceInSeconds / 60;
        long differenceInHours = differenceInMinutes / 60;
        long differenceInDays = differenceInHours / 24;

        System.out.println("时间差（毫秒）: " + differenceInMillis);
        System.out.println("时间差（秒）: " + differenceInSeconds);
        System.out.println("时间差（分钟）: " + differenceInMinutes);
        System.out.println("时间差（小时）: " + differenceInHours);
        System.out.println("时间差（天）: " + differenceInDays);
    }
}
