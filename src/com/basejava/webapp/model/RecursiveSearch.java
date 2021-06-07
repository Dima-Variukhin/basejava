package com.basejava.webapp.model;

import java.io.File;

public class RecursiveSearch {
    public static void recursiveSearch(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("directory " + file.getName());
                recursiveSearch(file);
            } else {
                System.out.println("      File : " + file.getName());
            }
        }
    }
}
