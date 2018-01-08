package com.rizz;

import java.io.File;

public class DOrganizer {

    private static String downloadsPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads";

    public static void main(String[] args) {
        //Checking if Downloads folder exists
        File downloadsFolder = new File(downloadsPath);
        if (!downloadsFolder.isDirectory()) {
            System.err.println("Downloads directory doesn't exist");
            System.exit(1);
        }

        //Creating directory structure inside downloads
        makeDirectory("Pictures");
    }

    private static void makeDirectory(String dirname) {
        File folder = new File(downloadsPath + "\\" + dirname);

        if (!folder.exists()) {
            folder.mkdir();
        }
//        boolean successful = folder.mkdir();
//
//        if(!successful || )
    }
}
