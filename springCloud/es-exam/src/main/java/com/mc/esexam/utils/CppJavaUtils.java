package com.mc.esexam.utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.UUID;

public class CppJavaUtils {
    public static String JavaRunCpp(String infile, String exeFile, String gcc, String factory) {
        StringBuilder output = new StringBuilder();
        try {
            ProcessBuilder compileBuilder = new ProcessBuilder(
                    gcc,
                    infile,
                    "-o", exeFile,
                    "-static"
            );

            compileBuilder.directory(new java.io.File(factory));
            appendPath(compileBuilder.environment(), factory);

            Process compileProcess = compileBuilder.start();
            compileProcess.waitFor();

            String compileCommand = gcc + " " + infile + " -o " + exeFile;
            output.append("开始编译\n").append(compileCommand).append("\n");

            BufferedReader compileErrorReader = new BufferedReader(
                    new InputStreamReader(compileProcess.getErrorStream())
            );
            String line;
            boolean hasCompileError = false;
            while ((line = compileErrorReader.readLine()) != null) {
                if (!hasCompileError) {
                    System.out.println("Compilation errors:");
                    output.append("Compilation errors:\n");
                    hasCompileError = true;
                }
                System.out.println(line);
                output.append(line).append("\n");
            }

            if (compileProcess.exitValue() == 0) {
                System.out.println("Compilation successful!");
                output.append("Compilation successful!\n");
                try {
                    Process process = Runtime.getRuntime().exec(exeFile);

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(process.getInputStream())
                    );
                    String lines;
                    output.append("______________result begin______________\n");
                    while ((lines = reader.readLine()) != null) {
                        System.out.println(lines);
                        output.append(lines).append("\n");
                    }

                    int exitCode = process.waitFor();
                    System.out.println("执行完成，退出代码：" + exitCode);
                    output.append("执行完成，退出代码：").append(exitCode).append("\n");
                    output.append("______________result end______________\n");
                } catch (IOException e) {
                    System.out.println("发生IO异常: " + e.getMessage());
                    output.append("发生IO异常: ").append(e.getMessage()).append("\n");
                } catch (InterruptedException e) {
                    System.out.println("进程被中断: " + e.getMessage());
                    output.append("进程被中断: ").append(e.getMessage()).append("\n");
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.println("Compilation failed!");
                output.append("Compilation failed!\n");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            output.append(e.getMessage()).append("\n");
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
        return output.toString();
    }

    private static void appendPath(Map<String, String> environment, String extraPath) {
        String pathKey = "Path";
        for (String key : environment.keySet()) {
            if ("PATH".equalsIgnoreCase(key)) {
                pathKey = key;
                break;
            }
        }

        String current = environment.get(pathKey);
        if (current == null || current.isEmpty()) {
            environment.put(pathKey, extraPath);
            return;
        }

        if (!current.toLowerCase().contains(extraPath.toLowerCase())) {
            environment.put(pathKey, extraPath + ";" + current);
        }
    }

    public static String SavaFiles(String path, String message, String type) {
        String fileName = UUID.randomUUID().toString().replace("-", "");
        fileName = fileName + "." + (type.equals("c") ? "c" : "cpp");
        try (FileWriter writer = new FileWriter(path + "\\" + fileName)) {
            writer.write(message);
            System.out.println("Code saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
        return fileName;
    }
}
