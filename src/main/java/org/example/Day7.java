package org.example;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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

    public static int countV2() {
        final String path = "./src/main/resources/Day7/input.txt";
        return countV2(path);
    }

    public static int countV2(String path) {
        int[] positions = getPositions(path);
        Arrays.sort(positions);
        out.println(average(positions));
        int median = median(positions);
        if (median < positions[positions.length - 1]) return countFuelForward(positions);
        else return countFuelBackward(positions);
    }

    public static int median(int[] sorted) {
        return sorted[sorted.length / 2];
    }

    public static float average(int[] positions) {
        int sum = 0;
        for (int y: positions) {
            sum += y;
        }
        return (float) sum / positions.length;
    }

    public static int countFuelForward(int[] positions) {
        int countFuel = countFuelV2(positions, positions[0]);
        int countFuelNext = countFuel;
        int i = 1;
        while (countFuelNext <= countFuel) {
            countFuel = countFuelNext;
            out.printf("y == %d, %d\n", i - 1, countFuel);
            countFuelNext = countFuelV2(positions, i);
            i++;
        }
        return countFuel;
    }

    public static int countFuelBackward(int[] positions) {
        int length = positions.length;
        int countFuel = countFuelV2(positions, positions[length - 1]);
        int countFuelNext = countFuel;
        int i = length - 2;
        while (countFuelNext <= countFuel) {
            countFuel = countFuelNext;
            out.printf("y == %d, %d\n", i - 1, countFuel);
            countFuelNext = countFuelV2(positions, i);
            i--;
        }
        return countFuel;
    }

    public static int countFuelV2(int[] positions, int point) {
        int countFuel = 0;
        for (int y: positions) {
            countFuel += consumption(Math.abs(y - point));
        }
        return countFuel;
    }

    public static int consumption(int delta) {
        int consumption;
        if (delta % 2 == 0) {
            consumption = delta / 2 * (delta + 1);
        } else {
            consumption = (delta + 1) / 2 * delta;
        }
        return consumption;
    }
}
