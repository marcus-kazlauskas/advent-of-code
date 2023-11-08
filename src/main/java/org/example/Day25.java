package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;

public class Day25 {
    public static int count() {
        final String path = "./src/main/resources/Day25/input.txt";
        return count(path);
    }

    public static int count(String path) {
        char[][] situation = saveState(path);
        boolean step = true;
        int count = 0;
        while (step) {
            step = nextStep(situation);
            count++;
        }
        return count;
    }

    public static char[][] saveState(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        LinkedList<char[]> listOfSituation = new LinkedList<>();
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                listOfSituation.add(scanner.nextLine().toCharArray());
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        int height = listOfSituation.size();
        int width = listOfSituation.get(0).length;
        char[][] situation = new char[height][width];
        for (int i = 0; i < height; i++) {
            char[] lineArray = listOfSituation.poll();
            situation[i] = lineArray;
        }
        return situation;
    }

    public static boolean nextStep(char[][] situation) {
        return nextStepForward(situation) | nextStepDownward(situation);
    }

    private static boolean nextStepForward(char[][] situation) {
        int height = situation.length;
        int width = situation[0].length;
        boolean step = false;
        boolean[] vacant = new boolean[width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(vacant, true);
            for (int j = 0; j < width; j++) {
                if (j == 0) {
                    step = step | stepForward(i, width - 1, 0, situation, vacant);
                } else {
                    step = step | stepForward(i, j - 1, j, situation, vacant);
                }
            }
        }
        return step;
    }

    private static boolean stepForward(int i, int jFrom, int jTo, char[][] situation, boolean[] vacant) {
        boolean step = false;
        if (situation[i][jFrom] == '>' && situation[i][jTo] == '.'
                && vacant[jFrom] && vacant[jTo]) {
            situation[i][jFrom] = '.';
            situation[i][jTo] = '>';
            vacant[jFrom] = false;
            vacant[jTo] = false;
            step = true;
        }
        return step;
    }

    private static boolean nextStepDownward(char[][] situation) {
        int height = situation.length;
        int width = situation[0].length;
        boolean step = false;
        boolean[] vacant = new boolean[height];
        for (int j = 0; j < width; j++) {
            Arrays.fill(vacant, true);
            for (int i = 0; i < height; i++) {
                if (i == 0) {
                    step = step | stepDownward(height - 1, 0, j, situation, vacant);
                } else {
                    step = step | stepDownward(i - 1, i, j, situation, vacant);
                }
            }
        }
        return step;
    }

    private static boolean stepDownward(int iFrom, int iTo, int j, char[][] situation, boolean[] vacant) {
        boolean step = false;
        if (situation[iFrom][j] == 'v' && situation[iTo][j] == '.'
                && vacant[iFrom] && vacant[iTo]) {
            situation[iFrom][j] = '.';
            situation[iTo][j] = 'v';
            vacant[iFrom] = false;
            vacant[iTo] = false;
            step = true;
        }
        return step;
    }

    public static void printState(int step, char[][] situation) {
        int height = situation.length;
        int width = situation[0].length;
        out.println(step);
        for (char[] chars : situation) {
            for (int j = 0; j < width; j++) {
                out.print(chars[j]);
            }
            out.println();
        }
        out.println();
    }
}
