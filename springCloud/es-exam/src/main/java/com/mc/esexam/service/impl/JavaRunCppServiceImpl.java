package com.mc.esexam.service.impl;


import com.mc.esexam.service.JavaRunCppService;
import com.mc.esexam.utils.CppJavaUtils;
import org.springframework.stereotype.Service;

@Service
public class JavaRunCppServiceImpl implements JavaRunCppService {
    private static final String path=System.getProperty("user.dir") + "/files/code";
    private static String gcc1="D:\\Applications\\msys2\\mingw64\\bin\\g++.exe";
    private static String gcc2="D:\\Applications\\msys2\\mingw64\\bin\\gcc.exe";
    private static String gcc="";
    private static final String factory="D:\\Applications\\msys2\\mingw64\\bin";

    @Override
    public String runCpp(String message, String type){
        String filepath = CppJavaUtils.SavaFiles(path, message, type);
        gcc=type.equals("c") ? gcc2 : gcc1;
        String m = CppJavaUtils.JavaRunCpp(path + "\\" + filepath, path + "\\" + (filepath.split("\\.")[0])+".exe", gcc, factory);
        return m ;
    }
}
