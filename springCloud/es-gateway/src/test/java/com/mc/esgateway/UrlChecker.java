package com.mc.esgateway;

public class UrlChecker {
    public static void main(String[] args) {
        String url = "/files/wangEditor/1736826586180-386b4838a5254f3490eba10bb2548a4a.jpg";  // 替换成你要检查的 URL
        
        if (url.startsWith("/files/wangEditor/")) {
            System.out.println("这个 URL 是以 \"/files/wangEditor/\" 开头");
        } else {
            System.out.println("这个 URL 不是以 \"/files/wangEditor/\" 开头");
        }
    }
}