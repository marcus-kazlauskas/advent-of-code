package org.example;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.System.out;

public class Day9 {
    public static final int MAX = 10;
    public static final int BORDER = 9;
    public static final int EMPTY_ID = -1;
    public static final int MULTIPLY_COUNT = 3;

    public static int count() {
        final String path = Main.INPUT_PATH.formatted("Day9");
        return count(path);
    }

    public static int count(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        LinkedList<int[]> heights = new LinkedList<>();
        LinkedList<Integer> lowPoints = new LinkedList<>();
        try (Scanner scanner = new Scanner(inputFile)) {
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

    private static int firstLine(Scanner scanner, LinkedList<int[]> threeLines) {
        String line = scanner.nextLine();
        int[] newLine = toIntArray(line);
        int[] emptyLine = new int[line.length()];
        Arrays.fill(emptyLine, MAX);
        threeLines.add(emptyLine);
        threeLines.add(emptyLine);
        threeLines.add(newLine);
        return line.length();
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

    public static int countV2() {
        final String path = Main.INPUT_PATH.formatted("Day9");
        return countV2(path);
    }

    public static int countV2(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        LinkedList<int[]> heights = new LinkedList<>();
        Map<Integer, Integer> basinsSizes = new HashMap<>();
        int multipliedSizes = 0;
        try (Scanner scanner = new Scanner(inputFile)) {
            int length = firstLine(scanner, heights);
            int i = 0;
            //  currentBasins[i] = (id бассейна для каждой точки в строке)
            int[] currentBasins = new int[length];
            Arrays.fill(currentBasins, EMPTY_ID);
            while (scanner.hasNext()) {
                nextLine(scanner, heights);
                out.println(i);
                i++;
                checkLine(heights, currentBasins, basinsSizes);
            }
            lastLine(heights);
            out.println(i);
            checkLine(heights, currentBasins, basinsSizes);
            multipliedSizes = multiplySizes(basinsSizes);
        } catch (Exception e) {
            out.println("ERROR!");
            out.println(e.getMessage());
        }
        return multipliedSizes;
    }

    private static void checkLine(
            LinkedList<int[]> threeLines,
            int[] currentBasins,
            Map<Integer, Integer> basinsSizes
    ) {
        int length = currentBasins.length;
        for (int i = 0; i < length; i++) {
            //  (leftUp)    (up)
            //  (left)      (point)
            int point = threeLines.get(1)[i];
            int up = threeLines.get(0)[i];
            int left = MAX;
            int leftUp = MAX;
            if (i > 0) {
                left = threeLines.get(1)[i - 1];
                leftUp = threeLines.get(0)[i - 1];
            }
            if (isBorder(point)) {
                emptyBasin(i, currentBasins);
            } else {
                if (isBorder(up)) {
                    if (isBorder(left)) {
                        newBasin(i, currentBasins, basinsSizes);
                    } else {
                        incrementLeftBasin(i, currentBasins, basinsSizes);
                    }
                } else {
                    if (isBorder(left)) {
                        incrementUpBasin(i, currentBasins, basinsSizes);
                    } else {
                        if (isBorder(leftUp)) {
                            addBasin(i, currentBasins, basinsSizes);
                        } else {
                            incrementLeftBasin(i, currentBasins, basinsSizes);
                            //  incrementBasinUp(i, currentBasins, basinsSizes) тоже подойдёт
                        }
                    }
                }
            }
        }
    }

    private static boolean isBorder(int point) {
        return point == MAX || point == BORDER;
    }

    private static void emptyBasin(int i, int[] currentBasins) {
        currentBasins[i] = EMPTY_ID;
        out.printf("line[%d]: empty\n", i);
    }

    private static void newBasin(int i, int[] currentBasins, Map<Integer, Integer> basinsSizes) {
        int newBasinId = createBasinId();
        while (basinsSizes.get(newBasinId) != null) {
            out.println("ID is not unique, new created");
            newBasinId = createBasinId();
        }
        currentBasins[i] = newBasinId;
        basinsSizes.put(newBasinId, 1);
        out.printf("line[%d]: newBasin, id=%d, count=%d\n", i, newBasinId, 1);
    }

    private static void incrementLeftBasin(int i, int[] currentBasins, Map<Integer, Integer> basinsSizes) {
        int basinId = currentBasins[i - 1];
        currentBasins[i] = basinId;
        int count = basinsSizes.get(basinId);
        basinsSizes.replace(basinId, count + 1);
        out.printf("line[%d]: incrementBasinLeft, id=%d, count=%d\n", i, basinId, count + 1);
    }

    private static void incrementUpBasin(int i, int[] currentBasins, Map<Integer, Integer> basinsSizes) {
        int basinId = currentBasins[i];
        currentBasins[i] = basinId;
        int count = basinsSizes.get(basinId);
        basinsSizes.replace(basinId, count + 1);
        out.printf("line[%d]: incrementBasinUp, id=%d, count=%d\n", i, basinId, count + 1);
    }

    private static void addBasin(int i, int[] currentBasins, Map<Integer, Integer> basinsSizes) {
        int leftBasinId = currentBasins[i - 1];
        int pointBasinId = currentBasins[i];
        if (leftBasinId == pointBasinId) {
            incrementLeftBasin(i, currentBasins, basinsSizes);
            //  incrementBasinUp(i, currentBasins, basinsSizes) тоже подойдёт
        } else {
            int leftBasin = basinsSizes.get(leftBasinId);
            int pointBasin = basinsSizes.get(pointBasinId);
            for (int j = 0; j < currentBasins.length; j++) {
                if (currentBasins[j] == leftBasinId) currentBasins[j] = pointBasinId;
            }
            basinsSizes.remove(leftBasinId);
            basinsSizes.replace(pointBasinId, leftBasin + pointBasin + 1);
            out.printf("line[%d]: addBasin, id=%d, count=%d\n", i, pointBasinId, leftBasin + pointBasin + 1);
        }
    }

    private static int createBasinId() {
        return Math.round((float) Math.random() * 1000000);
    }

    private static int multiplySizes(Map<Integer, Integer> basinsSizes) {
        int multi = 1;
        List<Integer> sizesList = basinsSizes.values().stream()
                .sorted(Collections.reverseOrder())
                .toList();
        try {
            for (int i = 0; i < MULTIPLY_COUNT; i++) {
                multi *= sizesList.get(i);
            }
        } catch (IndexOutOfBoundsException e) {
            out.printf("Number of basins is less than %d", MULTIPLY_COUNT);
        }
        return multi;
    }
}
