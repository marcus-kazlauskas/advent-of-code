package org.example;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;

public class Day9 {
    public static final int MAX = 10;

    public static int count() {
        final String path = Main.INPUT_PATH.formatted("Day9");
        return count(path);
    }

    public static int count(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        LinkedList<int[]> heights = new LinkedList<>();
        LinkedList<Integer> lowPoints = new LinkedList<>();
        try (Scanner scanner = new Scanner(inputFile)){
            firstLine(scanner, heights);
            while (scanner.hasNext()) {
                nextLine(scanner, heights);
                checkPoint(heights, lowPoints);
            }
            lastLine(heights);
            checkPoint(heights, lowPoints);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        return sumRiskLevels(lowPoints);
    }

    private static void checkPoint(LinkedList<int[]> threeLines, LinkedList<Integer> lowPoints) {
        int length = threeLines.get(1).length;
        for (int i = 0; i < length; i++) {
            int point = threeLines.get(1)[i];
            int up = threeLines.get(0)[i];
            int right = MAX;
            int down = threeLines.get(2)[i];
            int left = MAX;
            if (i < length - 1) {
                right = threeLines.get(1)[i + 1];
            }
            if (i > 0) {
                left = threeLines.get(1)[i - 1];
            }
            if (point < up && point < right && point < down && point < left)
                lowPoints.add(point);
        }
    }

    private static int[] toIntArray(String line) {
        return line.chars().map(x -> x - '0').toArray();
    }

    private static void firstLine(Scanner scanner, LinkedList<int[]> threeLines) {
        String line = scanner.nextLine();
        int[] newLine = toIntArray(line);
        int[] emptyLine = new int[line.length()];
        Arrays.fill(emptyLine, MAX);
        threeLines.add(emptyLine);
        threeLines.add(emptyLine);
        threeLines.add(newLine);
    }

    private static void nextLine(Scanner scanner, LinkedList<int[]> threeLines) {
        String line = scanner.nextLine();
        int[] newLine = toIntArray(line);
        threeLines.poll();
        threeLines.add(newLine);
    }

    private static void lastLine(LinkedList<int[]> threeLines) {
        int[] emptyLine = new int[threeLines.getFirst().length];
        Arrays.fill(emptyLine, MAX);
        threeLines.poll();
        threeLines.add(emptyLine);
    }

    private static int riskLevel(int height) {
        return 1 + height;
    }

    private static int sumRiskLevels(LinkedList<Integer> lowPoints) {
        int summary = 0;
        for (int height: lowPoints) {
            summary += riskLevel(height);
        }
        return summary;
    }
}
