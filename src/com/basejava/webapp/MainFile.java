package com.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        File file = new File("/home/metamorfag/IdeaProjects/basejava/.gitignore");
        try {
            System.out.println(file.getCanonicalFile());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("/home/metamorfag/IdeaProjects/basejava/src/com/basejava/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException();
        }
        recursiveSearch(new File("/home/metamorfag/IdeaProjects/basejava/src"), 0);
    }

    public static void recursiveSearch(File dir, int level) {
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                for (int i = 0; i < level; i++) {
                    System.out.print("\t");
                }
                if (file.isDirectory()) {
                    System.out.println("[directory: " + file.getName() + "]");
                    recursiveSearch(file, level + 1);
                } else {
                    System.out.println("File : " + file.getName());
                }
            }
        }
    }
}

