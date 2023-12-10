package com.smiccc.aa3;

public class Configuration {
    private final String rootPath;
    private final String tempPath;
    private final int deletionFrequencyDays;
    private final String[] folderNames;

    public Configuration(String rootPath, String tempPath, int deletionFrequencyDays, String[] folderNames) {
        this.rootPath = rootPath;
        this.tempPath = tempPath;
        this.deletionFrequencyDays = deletionFrequencyDays;
        this.folderNames = folderNames;
    }

    public String getRootPath() {
        return rootPath;
    }

    public String getTempPath() {
        return tempPath;
    }

    public int getDeletionFrequencyDays() {
        return deletionFrequencyDays;
    }

    public String[] getFolderNames() {
        return folderNames;
    }
}
