package com.rizz;

import java.io.File;

public class DOrganizer {

    private static String myOS = findMyOS();
    private static String downloadsPath;

    static {
        switch (myOS) {
            case "Linux":
                downloadsPath = System.getProperty("user.home") + "/Downloads";
                break;
            case "Windows":
                downloadsPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads";
                break;
            default:
                System.out.println("xxx Sorry... This program currently supports Windows and Linux only. xxx");
                System.exit(1);
                break;
        }
    }

    private static String[] pictureExtension = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};
    private static String[] softwareExtension = {".exe", ".jar", ".bat", ".py", ".sh", ".pl", ".msi", ".ini", ".run", ".out", ".deb", ".rpm"};
    private static String[] compressedFileExtension = {".zip", ".tgz", ".tar.gz", ".tar", ".bz2", ".7z", ".rar", ".pkg"};
    private static String[] videoExtension = {".mp4", ".avi", ".flv", ".mkv", ".wmv", ".3gp", ".mpeg", ".mpg", ".h264"};
    private static String[] audioExtension = {".mp3", ".wav", ".aif", ".mpa", ".wma", ".wpl"};

    public static void main(String[] args) {
        System.out.println(">>> Going to organize: \"" + downloadsPath + "\"");
        //Printing OS
        System.out.println(">>> Operating System: " + myOS);
        //Checking if Downloads folder exists
        File downloadsFolder = new File(downloadsPath);
        if (!downloadsFolder.isDirectory()) {
            System.err.println("xxx Downloads directory doesn't exist xxx");
            System.exit(1);
        }

        //Creating directory structure inside downloads
        makeDirectory("Pictures");
        makeDirectory("Software");
        makeDirectory("CompressedFiles");
        makeDirectory("Docs");
        makeDirectory("Videos");
        makeDirectory("Audio");

        //Organizing downloads directory
        System.out.println("\n>>> Organizing folders:~ ");
        organizeDir(pictureExtension, "Pictures");
        organizeDir(softwareExtension, "Software");
        organizeDir(compressedFileExtension, "CompressedFiles");
        organizeDir(videoExtension, "Videos");
        organizeDir(audioExtension, "Audio");

        organizeDocuments();

        System.out.println("\n>>> \"" + downloadsPath + "\" ORGANIZED <<<\n");

    }


    private static String findMyOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux")) {
            return "Linux";
        } else if (os.contains("window")) {
            return "Windows";
        }
        return "What?";
    }


    private static void makeDirectory(String dirname) {
        File folder = null;
        if (myOS.equals("Windows")) {
            folder = new File(downloadsPath + "\\" + dirname);
        } else if (myOS.equals("Linux")) {
            folder = new File(downloadsPath + "/" + dirname);
        }
        if (!folder.mkdir() && !folder.exists()) {
            System.err.println("xxx Unable to create " + dirname + " directory. xxx");
        }
    }


    private static void organizeDir(String[] fileExtension, String destination) {
        File downloadsFolder = new File(downloadsPath);
        File[] listOfFiles = downloadsFolder.listFiles();
        String destinationPath = null;
        boolean fileMoveStatus = true;

        if (myOS.equals("Windows")) {
            destinationPath = downloadsPath + "\\" + destination + "\\";
        } else if (myOS.equals("Linux")) {
            destinationPath = downloadsPath + "/" + destination + "/";
        }

        for (File f : listOfFiles) {
            if (!f.isFile()) {
                continue;
            }

            String filename = f.getName();
            for (String extension : fileExtension) {
                if (filename.endsWith(extension)) {
                    if (!f.renameTo(new File(destinationPath + filename))) {
                        System.out.println("xxx Unable to move " + filename + " to " + destination + " xxx");
                        fileMoveStatus = false;
                    }
                }
            }

        }


        System.out.println(">>> " + destination + ": " + (fileMoveStatus ? "SUCCESS" : "FAILURE"));

    }


    private static void organizeDocuments() {
        File downloadsFolder = new File(downloadsPath);
        File[] listOfFiles = downloadsFolder.listFiles();
        String destinationPath = null;
        boolean fileMoveStatus = true;

        if (myOS.equals("Windows")) {
            destinationPath = downloadsPath + "\\" + "Docs" + "\\";
        } else if (myOS.equals("Linux")) {
            destinationPath = downloadsPath + "/" + "Docs" + "/";
        }


        for (File f : listOfFiles) {
            if (!f.isFile()) {
                continue;
            }
            String filename = f.getName();

            if (!f.renameTo(new File(destinationPath + filename))) {
                System.out.println("xxx Unable to move " + filename + " to " + "Docs. xxx");
                fileMoveStatus = false;
            }

        }

        System.out.println(">>> " + "Docs" + ": " + (fileMoveStatus ? "SUCCESS" : "FAILURE"));


    }

}
