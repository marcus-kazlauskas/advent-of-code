package org.example;

import static java.lang.System.out;
import static java.lang.System.in;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int taskNumber;
        try (Scanner userInput = new Scanner(in)) {
            taskNumber = userInput.nextInt();
        }
        switch (taskNumber) {
            case 1      -> out.println(day1Count());
            case 2      -> out.println(day1CountV2());
            case 3      -> out.println(day2Count());
            case 4      -> out.println(day2CountV2());
            default     -> out.println("task not found");
        }
    }

    public static int day1Count() {
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

    public static int day1CountV2() {
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

    public static int day2Count() {
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

    public static int day2CountV2() {
        String path = "./src/main/resources/Day2/input.txt";
        return day2CountV2(path);
    }

    public static int day2CountV2(String path) {
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