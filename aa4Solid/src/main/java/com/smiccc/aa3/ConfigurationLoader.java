package com.smiccc.aa3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {
    public static Configuration loadConfiguration(String filePath) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);

            String rootPath = properties.getProperty("ROOT_PATH");
            String tempPath = properties.getProperty("TEMP_PATH");
            int deletionFrequencyDays = Integer.parseInt(properties.getProperty("DELETION_FREQUENCY_DAYS"));
            String folderNames = properties.getProperty("FOLDER_NAMES");

            return new Configuration(rootPath, tempPath, deletionFrequencyDays, folderNames.split(","));
        }
    }
}
