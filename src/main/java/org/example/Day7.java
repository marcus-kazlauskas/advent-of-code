package org.example;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Day7 {
    public static int count() {
        final String path = "./src/main/resources/Day7/input.txt";
        return count(path);
    }

    public static int count(String path) {
        int[] positions = getPositions(path);
        Arrays.sort(positions);
        return countFuel(positions);
    }

    public static int[] getPositions(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        int[] positions = new int[0];
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            String[] textPositions = scanner.nextLine().split(",");
            int length = textPositions.length;
            positions = new int[length];
            for (int i = 0; i < length; i++) {
                positions[i] = Integer.parseInt(textPositions[i]);
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        return positions;
    }

    public static int countFuel(int[] positions) {
        int length = positions.length;
        int half = length / 2;
        int fuelCount = 0;
        for (int i = 0; i <= half; i++) {
            fuelCount -= positions[i];
            fuelCount += positions[length - 1 - i];
        }
        return fuelCount;
    }
}
