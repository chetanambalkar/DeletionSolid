package com.smiccc.aa3;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileHandler {
    public static void moveFile(Path source, Path destination) throws IOException {
        Files.move(source, destination.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void deleteFile(File file) {
        if (file == null || !file.exists()) {
            System.out.println("File does not exist or is null.");
            return;
        }

        try {
            Files.delete(file.toPath());
            System.out.println("File deleted successfully: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error deleting file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
