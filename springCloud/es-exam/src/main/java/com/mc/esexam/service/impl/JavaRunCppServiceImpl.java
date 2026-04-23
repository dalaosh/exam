package com.mc.esexam.service.impl;

import com.mc.esexam.service.JavaRunCppService;
import com.mc.esexam.utils.CppJavaUtils;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class JavaRunCppServiceImpl implements JavaRunCppService {
    private static final String path = resolveCodePath();
    private static String gcc1 = "D:\\application\\MinGW\\bin\\g++.exe";
    private static String gcc2 = "D:\\application\\MinGW\\bin\\gcc.exe";
    private static String gcc = "";
    private static final String factory = "D:\\application\\MinGW\\bin";

    @Override
    public String runCpp(String message, String type) {
        String filepath = CppJavaUtils.SavaFiles(path, message, type);
        gcc = type.equals("c") ? gcc2 : gcc1;
        String m = CppJavaUtils.JavaRunCpp(path + "\\" + filepath, path + "\\" + (filepath.split("\\.")[0]) + ".exe", gcc, factory);
        return m;
    }

    private static String resolveCodePath() {
        String userDir = System.getProperty("user.dir");
        File direct = new File(userDir, "files\\code");
        if (direct.exists()) {
            return direct.getAbsolutePath();
        }

        File nested = new File(userDir, "springCloud\\files\\code");
        if (nested.exists()) {
            return nested.getAbsolutePath();
        }

        direct.mkdirs();
        return direct.getAbsolutePath();
    }
}
