package com.smiccc.aa3;

//DeleteFileProcessor.java
import java.io.IOException;
import java.nio.file.Path;

public class DeleteFileProcessor implements FileProcessor {
 @Override
 public void processFile(Path file) throws IOException {
     FileHandler.deleteFile(file.toFile());
 }
}