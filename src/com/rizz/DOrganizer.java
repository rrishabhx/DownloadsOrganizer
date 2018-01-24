package com.rizz;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class DOrganizer {

    private static String myOS = findMyOS();
    private static String downloadsPath;
    private static String downloadsPathString;
    private static String SLASH;
    private static HashSet<String> pictureExtensionSet = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "gif", "bmp"));
    private static HashSet<String> softwareExtensionSet = new HashSet<>(Arrays.asList("exe", "jar", "bat", "py", "sh", "pl", "msi", "ini", "run", "out", "deb", "rpm"));
    private static HashSet<String> archiveExtensionSet = new HashSet<>(Arrays.asList("zip", "tgz", "gz", "tar", "bz2", "7z", "rar", "pkg"));
    private static HashSet<String> videoExtensionSet = new HashSet<>(Arrays.asList("mp4", "avi", "flv", "mkv", "wmv", "3gp", "mpeg", "mpg", "h264"));
    private static HashSet<String> audioExtensionSet = new HashSet<>(Arrays.asList("mp3", "wav", "aif", "mpa", "wma", "wpl"));

    private static HashMap<String, HashSet<String>> fileTypeMap = new HashMap<>();


    static {
        switch (myOS) {
            case "Linux":
                downloadsPath = System.getProperty("user.home") + "/Downloads";
                downloadsPathString = System.getProperty("user.home") + "/Downloads/";
                SLASH = "/";
                break;
            case "Windows":
                downloadsPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads";
                downloadsPathString = "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads\\";
                SLASH = "\\";
                break;
            default:
                System.out.println("xxx Sorry... This program currently supports Windows and Linux only. xxx");
                System.exit(1);
                break;
        }

        //Populating fileTypeMap
        fileTypeMap.put("Pictures", pictureExtensionSet);
        fileTypeMap.put("Software", softwareExtensionSet);
        fileTypeMap.put("Archives", archiveExtensionSet);
        fileTypeMap.put("Videos", videoExtensionSet);
        fileTypeMap.put("Audios", audioExtensionSet);
    }

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
        makeDirectory("Archives");
        makeDirectory("Docs");
        makeDirectory("Videos");
        makeDirectory("Audios");

        //Organizing downloads directory
        System.out.println("\n>>> Organizing files:~ ");
        runOrganizer();

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


    private static void runOrganizer() {
        File downloadsFolder = new File(downloadsPath);
        File[] listOfFiles = downloadsFolder.listFiles();


        for (File f : listOfFiles) {
            boolean isDocumentFile = true;
            boolean moveStatus = false;

            //Checking if it's a folder
            if (!f.isFile()) {
                continue;
            }

            String filename = f.getName();
            String fileExtension = FilenameUtils.getExtension(filename);

            for (String fileTypeFolder : fileTypeMap.keySet()) {
                if (fileTypeMap.get(fileTypeFolder).contains(fileExtension)) {
                    String absoluteFilePath = downloadsPathString + fileTypeFolder + SLASH + filename;
                    moveStatus = moveFile(absoluteFilePath, f);
                    isDocumentFile = false;
                    break;
                }
            }
            if (isDocumentFile) {
                String absoluteDocumentPath = downloadsPathString + "Docs" + SLASH + filename;
                moveStatus = moveFile(absoluteDocumentPath, f);
            }

            if (moveStatus && !f.getName().equals("DownloadsOrganizer.jar")) {
                System.out.println("\t * " + filename);
            }
        }

    }


    private static boolean moveFile(String absoluteFilePath, File file) {
        if (!file.renameTo(new File(absoluteFilePath))) {
            //Checking if the file is not DownloadsOrganizer executable
            if (!file.getName().equals("DownloadsOrganizer.jar")) {
                System.err.println("xxx Unable to move " + file + " to " + absoluteFilePath + " xxx");
                return false;
            }
        }
        return true;
    }

}
