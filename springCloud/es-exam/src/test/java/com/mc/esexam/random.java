package com.mc.esexam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author machao
 * * @datetime 2025/2/15 23:33
 * * inform
 */
public class random {
    public static void main(String[] args) {
        // 定义大写字母、小写字母和数字的字符集合
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";

        Random random = new Random();
        List<Character> codeList = new ArrayList<>();

        // 从每种字符集合中随机选取一个字符
        codeList.add(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        codeList.add(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        codeList.add(digits.charAt(random.nextInt(digits.length())));

        // 定义包含所有字符的集合
        String allCharacters = upperCaseLetters + lowerCaseLetters + digits;

        // 生成剩余的 5 个字符
        for (int i = 0; i < 5; i++) {
            codeList.add(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        // 打乱字符顺序
        Collections.shuffle(codeList);

        // 将字符列表转换为字符串
        StringBuilder codeBuilder = new StringBuilder();
        for (char c : codeList) {
            codeBuilder.append(c);
        }

        System.out.println(codeBuilder.toString());
    }
}
