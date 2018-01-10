package com.rizz;

import java.io.File;

public class DOrganizer {

    private static String downloadsPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads";

    private static String[] pictureExtension = {".jpg", ".jpeg", ".png", ".gif"};
    private static String[] softwareExtension = {".exe"};
    private static String[] compressedFileExtension = {".zip", ".tgz", ".tar.gz", ".tar"};
    private static String[] videoExtension = {".mp4", ".avi", ".flv", ".mkv", ".wmv"};

    public static void main(String[] args) {
        //Checking if Downloads folder exists
        File downloadsFolder = new File(downloadsPath);
        if (!downloadsFolder.isDirectory()) {
            System.err.println("Downloads directory doesn't exist");
            System.exit(1);
        }

        //Creating directory structure inside downloads
        makeDirectory("Pictures");
        makeDirectory("Software");
        makeDirectory("CompressedFiles");
        makeDirectory("Docs");
        makeDirectory("Videos");

        //Organizing downloads directory
        organizeDir(pictureExtension, "Pictures");
        organizeDir(softwareExtension, "Software");
        organizeDir(compressedFileExtension, "CompressedFiles");
        organizeDir(videoExtension, "Videos");

        organizeDocuments("Docs");
    }


    private static void makeDirectory(String dirname) {
        File folder = new File(downloadsPath + "\\" + dirname);
        folder.mkdir();
    }


    private static void organizeDir(String[] fileExtension, String destination) {
        File downloadsFolder = new File(downloadsPath);
        File[] listOfFiles = downloadsFolder.listFiles();

        for (File f : listOfFiles) {
            if (f.isFile()) {
                String filename = f.getName();
                for (String extension : fileExtension) {
                    if (filename.endsWith(extension)) {
                        f.renameTo(new File(downloadsPath + "\\" + destination + "\\" + filename));
                    }
                }
            }

        }

    }


    private static void organizeDocuments(String destination) {
        File downloadsFolder = new File(downloadsPath);
        File[] listOfFiles = downloadsFolder.listFiles();

        for (File f : listOfFiles) {
            if (f.isFile()) {
                String filename = f.getName();

                f.renameTo(new File(downloadsPath + "\\" + destination + "\\" + filename));

            }
        }

    }

}
