package org.example;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day25 {
    public static int count() {
        final String path = "./src/main/resources/Day25/input.txt";
        return count(path);
    }

    public static int count(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        return 0;
    }
}
