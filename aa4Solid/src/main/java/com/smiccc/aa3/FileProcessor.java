package com.smiccc.aa3;

import java.io.IOException;
import java.nio.file.Path;

public interface FileProcessor {
    void processFile(Path file) throws IOException;
}