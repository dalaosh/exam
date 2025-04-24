package com.mc.escommon.result;

/**
 * @author machao
 * * @datetime 2025/1/7 0:25
 * * inform
 */
public enum TimeEnum {
    SECONDS("二十秒",20),
    MINUTES("一分钟",60),
    HOURS("一小时",60*60),
    DAYS("一天",60*60*24),
    WEEKS("一周",60*60*24*7),
    ;
    public final String name;
    public final int time;

    TimeEnum(String name, int time) {
        this.name = name;
        this.time = time;
    }
}
