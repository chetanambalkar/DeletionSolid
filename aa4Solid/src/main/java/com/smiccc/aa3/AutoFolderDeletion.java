package com.smiccc.aa3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AutoFolderDeletion {

    private static final Logger logger = LoggerFactory.getLogger(AutoFolderDeletion.class);

    private static Configuration configuration;
    private static FileProcessor fileProcessor;

    public static void main(String[] args) {
        initialize();
        while (true) {
            try {
                autoMoveAndDeleteFiles();
            } catch (IOException e) {
                logger.error("Unexpected error during file moving and deletion", e);
            }
            // Sleep for the specified frequency
            try {
                Thread.sleep(TimeUnit.DAYS.toMillis(configuration.getDeletionFrequencyDays()));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
                logger.warn("Thread interrupted", e);
            }
        }
    }

    private static void initialize() {
        try {
            configuration = ConfigurationLoader.loadConfiguration("config.properties");
            fileProcessor = new MoveFileProcessor(Paths.get(configuration.getTempPath()));
        } catch (IOException | NumberFormatException e) {
            logger.error("Error initializing the application", e);
            System.exit(1);
        }
    }

    private static void autoMoveAndDeleteFiles() throws IOException {
        // Iterate through folder names
        for (String folderName : configuration.getFolderNames()) {
            Path folderPath = Paths.get(configuration.getRootPath(), folderName);

            // Iterate through files in the folder
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
                for (Path filePath : directoryStream) {
                    // Check last modified date
                    Date lastModifiedDate = new Date(Files.getLastModifiedTime(filePath).toMillis());
                    if (isOlderThanThreshold(lastModifiedDate)) {
                        // Process the file using the provided FileProcessor
                        fileProcessor.processFile(filePath);
                    }
                }
            }
        }
    }

    private static boolean isOlderThanThreshold(Date lastModifiedDate) {
        // Calculate the deletion threshold
        Date deletionThreshold = calculateDeletionThreshold();

        // Compare last modified date with the threshold
        return lastModifiedDate.before(deletionThreshold);
    }

    private static Date calculateDeletionThreshold() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();

        // Subtract the deletion frequency days
        calendar.add(Calendar.DAY_OF_MONTH, -configuration.getDeletionFrequencyDays());

        // Return the calculated date
        return calendar.getTime();
    }
}