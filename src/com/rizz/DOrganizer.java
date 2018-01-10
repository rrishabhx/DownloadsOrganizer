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

        organizeDocuments();

        System.out.println("\n\n--------------------------------------------------");
        System.out.println("|************* DOWNLOADS ORGANIZED **************|");
        System.out.println("--------------------------------------------------\n");

    }


    private static void makeDirectory(String dirname) {
        File folder = new File(downloadsPath + "\\" + dirname);
        if (!folder.mkdir() && !folder.exists()) {
            System.err.println("Unable to create " + dirname + " directory.");
        }
    }


    private static void organizeDir(String[] fileExtension, String destination) {
        File downloadsFolder = new File(downloadsPath);
        File[] listOfFiles = downloadsFolder.listFiles();

        for (File f : listOfFiles) {
            if (f.isFile()) {
                String filename = f.getName();
                for (String extension : fileExtension) {
                    if (filename.endsWith(extension)) {
                        if (!f.renameTo(new File(downloadsPath + "\\" + destination + "\\" + filename))) {
                            System.err.println("Unable to move " + filename + " to " + destination);
                        }
                    }
                }
            }

        }

    }


    private static void organizeDocuments() {
        File downloadsFolder = new File(downloadsPath);
        File[] listOfFiles = downloadsFolder.listFiles();

        for (File f : listOfFiles) {
            if (f.isFile()) {
                String filename = f.getName();

                if (!f.renameTo(new File(downloadsPath + "\\" + "Docs" + "\\" + filename))) {
                    System.err.println("Unable to move " + filename + " to " + "Docs");
                }

            }
        }

    }

}
