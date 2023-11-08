package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.lang.System.out;

public class Day2 {
    public static int count() {
        Path path = Paths.get("./src/main/resources/Day2/input.txt");
        File inputFile = new File(path.toUri());
        int horizontalPos = 0;
        int depthPos = 0;
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                String[] command = scanner.nextLine().split(" ");
                int step = Integer.parseInt(command[1]);
                switch (command[0]) {
                    case "forward"  -> horizontalPos += step;
                    case "down"     -> depthPos += step;
                    case "up"       -> {
                        if ((depthPos - step) > 0) {
                            depthPos -= step;
                        } else depthPos = 0;
                    }
                }
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        return horizontalPos * depthPos;
    }

    public static int countV2() {
        String path = "./src/main/resources/Day2/input.txt";
        return countV2(path);
    }

    public static int countV2(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        int horizontalPos = 0;
        int depthPos = 0;
        int aim = 0;
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                String[] command = scanner.nextLine().split(" ");
                int step = Integer.parseInt(command[1]);
                switch (command[0]) {
                    case "forward"  -> {
                        horizontalPos += step;
                        depthPos += aim * step;
                    }
                    case "down"     -> aim += step;
                    case "up"       -> aim -= step;
                }
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        return horizontalPos * depthPos;
    }
}
