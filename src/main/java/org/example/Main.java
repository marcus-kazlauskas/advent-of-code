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
            case 5      -> out.println(day3Count());
            case 6      -> out.println(day3CountV2());
            case 7      -> out.println(Day4.day4Count());
            case 8      -> out.println(Day4.day4CountV2());
            case 9      -> out.println(Day5.day5Count());
            case 10     -> out.println(Day5.day5CountV2());
//            case 11     -> out.println(Day6.count());
//            case 12     -> out.println(Day6.countV2());
//            case 13     -> out.println(Day7.count());
//            case 14     -> out.println(Day7.countV2());
//            case 15     -> out.println(Day8.count());
//            case 16     -> out.println(Day8.countV2());
//            case 17     -> out.println(Day9.count());
//            case 18     -> out.println(Day9.countV2());
            case 19     -> out.println(Day10.count());
            case 20     -> out.println(Day10.countV2());
//            case 21     -> out.println(Day11.count());
//            case 22     -> out.println(Day11.countV2());
//            case 23     -> out.println(Day12.count());
//            case 24     -> out.println(Day12.countV2());
//            case 25     -> out.println(Day13.count());
//            case 26     -> out.println(Day13.countV2());
//            case 27     -> out.println(Day14.count());
//            case 28     -> out.println(Day14.countV2());
//            case 29     -> out.println(Day15.count());
//            case 30     -> out.println(Day15.countV2());
//            case 31     -> out.println(Day16.count());
//            case 32     -> out.println(Day16.countV2());
//            case 33     -> out.println(Day17.count());
//            case 34     -> out.println(Day17.countV2());
//            case 35     -> out.println(Day18.count());
//            case 36     -> out.println(Day18.countV2());
//            case 37     -> out.println(Day19.count());
//            case 38     -> out.println(Day19.countV2());
//            case 39     -> out.println(Day20.count());
//            case 40     -> out.println(Day20.countV2());
//            case 41     -> out.println(Day21.count());
//            case 42     -> out.println(Day21.countV2());
//            case 43     -> out.println(Day22.count());
//            case 44     -> out.println(Day22.countV2());
//            case 45     -> out.println(Day23.count());
//            case 46     -> out.println(Day23.countV2());
//            case 47     -> out.println(Day24.count());
//            case 48     -> out.println(Day24.countV2());
            case 49     -> out.println(Day25.count());
//            case 50     -> out.println(Day25.countV2());
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

    public static int day3Count() {
        final String path = "./src/main/resources/Day3/input.txt";
        return day3Count(path);
    }

    public static int day3Count(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        int length = 1;
        int[] counter0 = new int[length];
        int[] counter1 = new int[length];
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            char[] bits = scanner.nextLine().toCharArray();
            length = bits.length;
            counter0 = new int[length];
            counter1 = new int[length];
            checkBits(bits, length, counter0, counter1);
            while (scanner.hasNext()) {
                bits = scanner.nextLine().toCharArray();
                checkBits(bits, length, counter0, counter1);
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        char[] gamma = new char[length];
        char[] epsilon = new char[length];
        for (int i = 0; i < length; i++) {
            gamma[i] = (counter0[i] > counter1[i]) ? '0' : '1';
            epsilon[i] = (counter0[i] < counter1[i]) ? '0' : '1';
        }
        return Integer.valueOf(new String(gamma), 2) *
                Integer.valueOf(new String(epsilon), 2);
    }

    public static void checkBits(char[] bits, int length, int[] counter0, int[] counter1) {
        for (int i = 0; i < length; i++) {
            if (bits[i] == '0') counter0[i]++;
            if (bits[i] == '1') counter1[i]++;
        }
    }

    public static int day3CountV2() {
        final String path = "./src/main/resources/Day3/input.txt";
        return day3CountV2(path);
    }

    public static int day3CountV2(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        int length = 1;
        List<char[]> oxygen = new LinkedList<>();
        List<char[]> co2 = new LinkedList<>();
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            char[] bits = scanner.nextLine().toCharArray();
            oxygen.add(bits);
            co2.add(bits);
            length = bits.length;
            while (scanner.hasNext()) {
                bits = scanner.nextLine().toCharArray();
                oxygen.add(bits);
                co2.add(bits);
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        }

        int i = 0;
        int j;
        char oxygenCriteria;
        while (oxygen.size() > 1 && i < length) {
            j = 0;
            oxygenCriteria = bitCriteria(oxygen, i, "oxygen");
            while (j < oxygen.size()) {
                char[] bits = oxygen.get(j);
                if (bits[i] != oxygenCriteria) oxygen.remove(j);
                else j++;
            }
            i++;
        }

        i = 0;
        char co2Criteria;
        while (co2.size() > 1 && i < length) {
            j = 0;
            co2Criteria = bitCriteria(co2, i, "co2");
            while (j < co2.size()) {
                char[] bits = co2.get(j);
                if (bits[i] != co2Criteria) co2.remove(j);
                else j++;
            }
            i++;
        }

        return Integer.valueOf(new String(oxygen.get(0)), 2) *
                Integer.valueOf(new String(co2.get(0)), 2);
    }

    public static char bitCriteria(List<char[]> allBits, int i, String nameRating) {
        int counter = 0;
        for (char[] bits: allBits) {
            if (bits[i] == '0') counter++;
        }
        switch (nameRating) {
            case "oxygen" -> {
                return (counter > (allBits.size() / 2)) ? '0' : '1';
            }
            case "co2" -> {
                return (counter > (allBits.size() / 2)) ? '1' : '0';
            }
            default -> {
                return '0';
            }
        }
    }
}