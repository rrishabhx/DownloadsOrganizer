package com.rizz;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class DOrganizer {

    private static String downloadsDir;
    private static String downloadsPath;
    private static String FS;
    private static HashSet<String> pictureExtensionSet = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "gif", "bmp"));
    private static HashSet<String> softwareExtensionSet = new HashSet<>(Arrays.asList("exe", "jar", "bat", "py", "sh", "pl", "msi", "ini", "run", "out", "deb", "rpm"));
    private static HashSet<String> archiveExtensionSet = new HashSet<>(Arrays.asList("zip", "tgz", "gz", "tar", "bz2", "7z", "rar", "pkg"));
    private static HashSet<String> videoExtensionSet = new HashSet<>(Arrays.asList("mp4", "avi", "flv", "mkv", "wmv", "3gp", "mpeg", "mpg", "h264"));
    private static HashSet<String> audioExtensionSet = new HashSet<>(Arrays.asList("mp3", "wav", "aif", "mpa", "wma", "wpl"));
    private static HashMap<String, HashSet<String>> fileTypeMap = new HashMap<>();
    private static Logger logger = LogManager.getLogger(DOrganizer.class);

    static {
        FS = File.separator;
        String HOME = System.getProperty("user.home");
        downloadsDir = HOME + FS + "Downloads";
        downloadsPath = downloadsDir + FS;

        //Populating fileTypeMap
        fileTypeMap.put("Pictures", pictureExtensionSet);
        fileTypeMap.put("Software", softwareExtensionSet);
        fileTypeMap.put("Archives", archiveExtensionSet);
        fileTypeMap.put("Videos", videoExtensionSet);
        fileTypeMap.put("Audios", audioExtensionSet);
    }

    public static void main(String[] args) {
        logger.info("******************** " + new Date() + " ********************");
        logger.info("Going to organize: [" + downloadsDir + "]");
        //Printing OS
        logger.info("Operating System: " + System.getProperty("os.name"));

        //Checking if Downloads folder exists
        File downloadsFolder = new File(downloadsDir);
        if (!downloadsFolder.isDirectory()) {
            logger.error("Downloads directory doesn't exist. Exiting....");
            System.exit(1);
        }

        //Creating file-specific directory structure inside downloads
        makeDirectory("Pictures");
        makeDirectory("Software");
        makeDirectory("Archives");
        makeDirectory("Docs");
        makeDirectory("Videos");
        makeDirectory("Audios");

        //Organizing downloads directory
        logger.info("Organizing files:~ ");
        runOrganizer();

        logger.info(">>>>>>>>>>>>>>>>>>>> [" + downloadsDir + "] ORGANIZED <<<<<<<<<<<<<<<<<<<<");

    }


    private static void makeDirectory(String dirname) {
        File folder = new File(downloadsPath + dirname);

        if (!folder.mkdir() && !folder.exists()) {
            logger.error("xxx Unable to create " + dirname + " directory. xxx");
        }
    }


    private static void runOrganizer() {
        File downloadsFolder = new File(downloadsDir);
        File[] listOfFiles = downloadsFolder.listFiles();


        for (File f : listOfFiles) {
            boolean isDocumentFile = true;

            //Skip if 'f' is folder/DownloadsOrganizer.jar
            if (f.isDirectory() || f.getName().equals("DownloadsOrganizer.jar")) {
                continue;
            }

            String filename = f.getName();
            String fileExtension = FilenameUtils.getExtension(filename);

            for (String fileTypeFolder : fileTypeMap.keySet()) {
                if (fileTypeMap.get(fileTypeFolder).contains(fileExtension)) {
                    String absoluteFilePath = downloadsPath + fileTypeFolder + FS + filename;
                    moveFile(absoluteFilePath, f);
                    isDocumentFile = false;
                    break;
                }
            }
            if (isDocumentFile) {
                String absoluteDocumentPath = downloadsPath + "Docs" + FS + filename;
                moveFile(absoluteDocumentPath, f);
            }

        }

    }


    private static void moveFile(String absoluteFilePath, File file) {
        if (!file.renameTo(new File(absoluteFilePath))) {

            //Checking if the file is not DownloadsOrganizer executable
            if (!file.getName().equals("DownloadsOrganizer.jar")) {
                logger.info("FAILURE: " + file.getName() + " --> " + absoluteFilePath);
                return;
            }
        }
        logger.info("SUCCESS: " + file.getName() + " --> " + absoluteFilePath);
    }

}
