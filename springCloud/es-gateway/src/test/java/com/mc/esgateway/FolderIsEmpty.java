package com.mc.esgateway;

import java.io.File;

public class FolderIsEmpty {
    public static boolean isFolderEmpty(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            return files == null || files.length == 0;
        }
        return false;
    }

    public static void main(String[] args) {
        File folder = new File("F:\\ExamSystem\\SystemCode\\springCloud\\files\\md");
        boolean isEmpty = isFolderEmpty(folder);
        System.out.println("Is the folder empty? " + isEmpty);
    }
}