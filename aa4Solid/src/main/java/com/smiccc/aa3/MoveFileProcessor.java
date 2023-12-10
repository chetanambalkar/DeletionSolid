package com.smiccc.aa3;

import java.io.IOException;
import java.nio.file.Path;

public class MoveFileProcessor implements FileProcessor {
    private final Path destination;

    public MoveFileProcessor(Path destination) {
        this.destination = destination;
    }

    @Override
    public void processFile(Path file) throws IOException {
        FileHandler.moveFile(file, destination);
    }
}

