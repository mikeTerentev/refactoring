package ru.akirakozov.sd.refactoring.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class FileUtils {

    public static String readFile(Path path) throws IOException {
        return String.join("\n", Files.readAllLines(path));
    }
}
