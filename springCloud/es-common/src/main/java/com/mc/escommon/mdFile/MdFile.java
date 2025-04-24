package com.mc.escommon.mdFile;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;

import java.io.*;

/**
 * @author machao
 * * @datetime 2025/1/14 23:29
 * * inform 对markdown进行操作
 * 创建md文档
 * 读取md文档
 * 重写md文档
 * 删除md文档
 */
public class MdFile {

    /**
     * 创建文件
     */
    public static String setMdFile(String content){
        String filePath = System.getProperty("user.dir") + "/files/markdown/";
        String flag = System.currentTimeMillis() + "";
        String random= RandomUtil.randomNumbers(8);
        String Name = RandomUtil.randomNumbers(8)+".md";
        String fileName=filePath+flag+"-"+random+"-"+Name;
        File directory = new File(filePath);
        try {
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("目录 " + filePath + " 创建成功");
                } else {
                    System.out.println("无法创建目录 " + filePath);
                }
            } else {
                System.out.println("目录 " + filePath + " 已存在");
            }
        } catch (SecurityException e) {
            System.out.println("创建目录时出现安全异常: " + e.getMessage());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // 将 Markdown 内容写入文件
            writer.write(content);
            System.out.println("Markdown 文件 " + fileName + " 已成功创建。");
        } catch (IOException e) {
            System.out.println("创建 Markdown 文件时出现错误: " + e.getMessage());
        }
        return "/files/markdown/"+flag+"-"+random+"-"+Name;
    }

    /**
     * 读取文件
     */
    public static String getMdFile(String fileName){
        String filePath=System.getProperty("user.dir")+fileName;
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine())!= null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            System.out.println("读取文件时发生错误: " + e.getMessage());
            return null;
        }
    }

    /**
     * 重写文档
     */
    public static void rewriteFile(String fileName, String newContent) {
        String filePath=System.getProperty("user.dir")+fileName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(newContent);
            System.out.println("文件已成功重写。");
        } catch (IOException e) {
            System.out.println("重写文件时出现错误: " + e.getMessage());
        }
    }
    /**
     * 删除文件
     */

    public static void deleteFile(String fileName) {
        String filePath=System.getProperty("user.dir")+fileName;
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("文件 " + filePath + " 已成功删除。");
            } else {
                System.out.println("文件 " + filePath + " 删除失败。");
            }
        } else {
            System.out.println("文件 " + filePath + " 不存在。");
        }
    }
}
