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
                System.out.println("Sorry... This program currently supports Windows and Linux only.");
                System.exit(1);
                break;
        }
    }

    private static String[] pictureExtension = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};
    private static String[] softwareExtension = {".exe", ".jar", ".bat", ".py", ".sh", ".pl", ".msi", ".ini"};
    private static String[] compressedFileExtension = {".zip", ".tgz", ".tar.gz", ".tar", ".bz2"};
    private static String[] videoExtension = {".mp4", ".avi", ".flv", ".mkv", ".wmv", ".3gp", ".mpeg", ".mpg", ".h264"};
    private static String[] audioExtension = {".mp3", ".wav", ".aif", ".mpa", ".wma", ".wpl"};

    public static void main(String[] args) {
        //Printing OS
        System.out.println("YOU ARE USING: " + myOS);
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
        makeDirectory("Music");

        //Organizing downloads directory
        organizeDir(pictureExtension, "Pictures");
        organizeDir(softwareExtension, "Software");
        organizeDir(compressedFileExtension, "CompressedFiles");
        organizeDir(videoExtension, "Videos");
        organizeDir(audioExtension,"Music");

        organizeDocuments();

        System.out.println("\n\n--------------------------------------------------");
        System.out.println("|************* DOWNLOADS ORGANIZED **************|");
        System.out.println("--------------------------------------------------\n");

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
            System.err.println("Unable to create " + dirname + " directory.");
        }
    }


    private static void organizeDir(String[] fileExtension, String destination) {
        File downloadsFolder = new File(downloadsPath);
        File[] listOfFiles = downloadsFolder.listFiles();
        String destinationPath = null;

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
                        System.err.println("Unable to move " + filename + " to " + destination);
                    }
                }
            }


        }

    }


    private static void organizeDocuments() {
        File downloadsFolder = new File(downloadsPath);
        File[] listOfFiles = downloadsFolder.listFiles();
        String destinationPath = null;

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
                System.err.println("Unable to move " + filename + " to " + "Docs");
            }

        }

    }

}
