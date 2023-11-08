package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class Day1 {
    public static int count() {
        Path path = Paths.get("./src/main/resources/Day1/input1.txt");
        File inputFile = new File(path.toUri());
        int previous;
        int counter = 0;
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            previous = scanner.nextInt();
            while (scanner.hasNext()) {
                int measurement = scanner.nextInt();
                if (measurement > previous) {
                    counter += 1;
                }
                previous = measurement;
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        return counter;
    }

    public static int countV2() {
        Path path = Paths.get("./src/main/resources/Day1/input1.txt");
        File inputFile = new File(path.toUri());
        int previousSum = 0;
        List<Integer> previous = new LinkedList<>();
        int count = 0;
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            for (int i = 0; i < 3; i++) {
                int value = scanner.nextInt();
                previous.add(value);
                previousSum += value;
            }
            while (scanner.hasNext()) {
                int measurement = scanner.nextInt();
                if (measurement + previousSum - previous.get(0) > previousSum) {
                    count += 1;
                }
                previousSum = measurement + previousSum - previous.get(0);
                previous.remove(0);
                previous.add(measurement);
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        return count;
    }
}
