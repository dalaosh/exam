package com.mc.esexam.utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class CppJavaUtils {
    public static String JavaRunCpp(String infile,String exeFile,String gcc,String factory){
        StringBuilder output= new StringBuilder();
        try {
            // 编译 C++ 代码
            ProcessBuilder compileBuilder = new ProcessBuilder(
                    gcc,      // g++ 编译器路径
                    infile,      // 源文件路径
                    "-o", exeFile, // 输出可执行文件路径
                    "-static"                    // 静态链接参数
            );

            compileBuilder.directory(new java.io.File(factory));
            Process compileProcess = compileBuilder.start();
            compileProcess.waitFor();
            String compileCommand = gcc+" "+infile+" -o "+exeFile;
            output.append("开始编译\n").append(compileCommand).append("\n");
            // 打印编译错误
            BufferedReader compileErrorReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
            String line;
            if((line = compileErrorReader.readLine()) != null){
                System.out.println("Compilation errors:");
                output.append("Compilation errors:" + "\n");
            }
            while ((line = compileErrorReader.readLine()) != null) {

                System.out.println(line);
                output.append(line).append("\n");
            }

            // 检查编译是否成功
            if (compileProcess.exitValue() == 0) {
                System.out.println("Compilation successful!");
                output.append("Compilation successful!" + "\n");
                try {
                    // 指定要运行的exe文件路径
                    String exePath = exeFile;

                    // 使用 Runtime.getRuntime().exec() 运行exe文件
                    Process process = Runtime.getRuntime().exec(exePath);

                    // 获取进程的输入流并读取输出
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String lines;
                    output.append("______________result begin______________\n");
                    while ((lines = reader.readLine()) != null) {
                        System.out.println(lines);  // 输出 hello.exe 的输出
                        output.append(lines).append("\n");
                    }

                    // 等待进程执行完毕
                    int exitCode = process.waitFor();
                    System.out.println("执行完成，退出代码：" + exitCode);

                    output.append("执行完成，退出代码：" ).append(exitCode).append("\n");
                    output.append("______________result end______________\n");

                } catch (IOException e) {
                    System.out.println("发生IO异常: " + e.getMessage());
                    output.append("发生IO异常: " ).append( e.getMessage()).append("\n");
                } catch (InterruptedException e) {
                    System.out.println("进程被中断: " + e.getMessage());
                    output.append("进程被中断: " ).append( e.getMessage()).append("\n");
                }

            } else {
                System.out.println("Compilation failed!");
                output.append("Compilation failed!" ).append("\n");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            output.append( e.getMessage()).append("\n");
        }
        return output.toString();
    }

    public static String SavaFiles(String path,String message,String type){
// 生成32位随机文件名
        String fileName = UUID.randomUUID().toString().replace("-", "");
        fileName=fileName + "." + (type.equals("c") ? "c" : "cpp");
        try (FileWriter writer = new FileWriter(path+"\\"+fileName)) {
            writer.write(message);
            System.out.println("Code saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
        return fileName;
    }
}
