package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.System.out;

public class Day5 {
    public static int count() {
        final String path = "./src/main/resources/Day5/input.txt";
        return count(path, 1000);
    }

    public static int count(String path, int sizeOfDiagram) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        String[] lineCoordinates;
        int[][] diagram = new int[sizeOfDiagram][sizeOfDiagram];
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                lineCoordinates = scanner.nextLine().split(",|( -> )");
                printCoordinates(lineCoordinates);
                addDots(getDots(lineCoordinates), diagram);
//                printDiagram(diagram);
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        return countDots(2, diagram);
    }

    public static List<int[]> getDots(String[] coordinates) {
        if (coordinates[0].equals(coordinates[2])) { // along y axis
            int x = Integer.parseInt(coordinates[0]);
            int y0 = Integer.parseInt(coordinates[1]);
            int y1 = Integer.parseInt(coordinates[3]);
            int yMin = Integer.min(y0, y1);
            int yMax = Integer.max(y0, y1);
            List<int[]> dots = new LinkedList<>();
            for (int i = yMin; i <= yMax; i++) {
                int[] dot = new int[2];
                dot[0] = x;
                dot[1] = i;
                dots.add(dot);
//                out.printf("(%d,%d)\n", dot[0], dot[1]);
            }
            return dots;
        } else if (coordinates[1].equals(coordinates[3])) { // along x axis
            int x0 = Integer.parseInt(coordinates[0]);
            int x1 = Integer.parseInt(coordinates[2]);
            int y = Integer.parseInt(coordinates[1]);
            int xMin = Integer.min(x0, x1);
            int xMax = Integer.max(x0, x1);
            List<int[]> dots = new LinkedList<>();
            for (int i = xMin; i <= xMax; i++) {
                int[] dot = new int[2];
                dot[0] = i;
                dot[1] = y;
                dots.add(dot);
//                out.printf("(%d,%d)\n", dot[0], dot[1]);
            }
            return dots;
        } else return Collections.emptyList();
    }

    public static void addDots(List<int[]> dots, int[][] diagram) {
        for (int[] dot: dots) {
            diagram[dot[0]][dot[1]]++;
            out.printf("diagram[%d][%d]++\n", dot[0], dot[1]);
        }
        out.println();
    }

    public static int countDots(int value, int[][] diagram) {
        int count = 0;
        for (int[] line: diagram) {
            for (int dot: line) {
                if (dot >= value) count++;
            }
        }
        return count;
    }

    public static void printCoordinates(String[] coordinates) {
        for (String number: coordinates) {
            out.printf("%s ", number);
        }
        out.println();
    }

    public static void printDiagram(int[][] diagram) {
        for (int[] line: diagram) {
            for (int dot: line) {
                out.printf("%d ", dot);
            }
            out.println();
        }
        out.println();
    }

    public static int countV2() {
        final String path = "./src/main/resources/Day5/input.txt";
        return countV2(path, 1000);
    }

    public static int countV2(String path, int sizeOfDiagram) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        String[] lineCoordinates;
        int[][] diagram = new int[sizeOfDiagram][sizeOfDiagram];
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                lineCoordinates = scanner.nextLine().split(",|( -> )");
                printCoordinates(lineCoordinates);
                addDots(getDotsV2(lineCoordinates), diagram);
//                printDiagram(diagram);
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        return countDots(2, diagram);
    }

    public static List<int[]> getDotsV2(String[] coordinates) {
        int[] coordinateNumbers = new int[4];
        for (int i = 0; i < 4; i++) {
            coordinateNumbers[i] = Integer.parseInt(coordinates[i]);
        }
        if (coordinateNumbers[0] == coordinateNumbers[2]) { // along y axis
            int x = coordinateNumbers[0];
            int y0 = coordinateNumbers[1];
            int y1 = coordinateNumbers[3];
            int yMin = Integer.min(y0, y1);
            int yMax = Integer.max(y0, y1);
            List<int[]> dots = new LinkedList<>();
            for (int i = yMin; i <= yMax; i++) {
                int[] dot = new int[2];
                dot[0] = x;
                dot[1] = i;
                dots.add(dot);
//                out.printf("(%d,%d)\n", dot[0], dot[1]);
            }
            return dots;
        } else if (coordinateNumbers[1] == coordinateNumbers[3]) { // along x axis
            int x0 = coordinateNumbers[0];
            int x1 = coordinateNumbers[2];
            int y = coordinateNumbers[1];
            int xMin = Integer.min(x0, x1);
            int xMax = Integer.max(x0, x1);
            List<int[]> dots = new LinkedList<>();
            for (int i = xMin; i <= xMax; i++) {
                int[] dot = new int[2];
                dot[0] = i;
                dot[1] = y;
                dots.add(dot);
//                out.printf("(%d,%d)\n", dot[0], dot[1]);
            }
            return dots;
        } else if (checkDiagonal(coordinateNumbers)) {
            int xMin = Math.min(coordinateNumbers[0], coordinateNumbers[2]);
            int xMax = Math.max(coordinateNumbers[0], coordinateNumbers[2]);
            int yMin = Math.min(coordinateNumbers[1], coordinateNumbers[3]);
            int yMax = Math.max(coordinateNumbers[1], coordinateNumbers[3]);
            int i = xMin;
            int j = yMin;
            List<int[]> dots = new LinkedList<>();
            while (i <= xMax && j <= yMax) {
                int[] dot = new int[2];
                dot[0] = i;
                dot[1] = (checkTangent(coordinateNumbers) == 1) ? j : yMin + yMax - j;
                dots.add(dot);
                i++;
                j++;
            }
            return dots;
        } else return Collections.emptyList();
    }

    private static boolean checkDiagonal(int[] coordinateNumbers) {
        int deltaX = Math.abs(coordinateNumbers[0] - coordinateNumbers[2]);
        int deltaY = Math.abs(coordinateNumbers[1] - coordinateNumbers[3]);
        return deltaX == deltaY;
    }

    private static int checkTangent(int[] coordinateNumbers) {
        // можно было через ноль задать веритикальные и горизонтальные линии
        int signumDeltaX = Integer.signum(coordinateNumbers[0] - coordinateNumbers[2]);
        int signumDeltaY = Integer.signum(coordinateNumbers[1] - coordinateNumbers[3]);
        return signumDeltaX * signumDeltaY;
    }
}
