package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.System.out;

public class Day10 {
    public static int count() {
        final String path = "./src/main/resources/Day10/input.txt";
        return count(path);
    }

    public static int count(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        List<Character> bracketsFound = new LinkedList<>();
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                char bracket = checkCorrupted(line);
                if (bracket != ' ') {
                    bracketsFound.add(bracket);
                }
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        return getSumOfBrackets(bracketsFound);
    }

    private static char checkCorrupted(String line) {
        List<Character> brackets = new LinkedList<>();
        char[] bracketArray = line.toCharArray();
        for (char bracket: bracketArray) {
            switch (bracket) {
                case '(', '[', '{', '<' -> {
                    brackets.add(0, bracket);
                }
                case ')', ']', '}', '>' -> {
                    char bracketInStuck = brackets.get(0);
                    switch (bracketInStuck) {
                        case '(' -> {
                            if (bracket != ')') return bracket;
                            else brackets.remove(0);
                        }
                        case '[' -> {
                            if (bracket != ']') return bracket;
                            else brackets.remove(0);
                        }
                        case '{' -> {
                            if (bracket != '}') return bracket;
                            else brackets.remove(0);
                        }
                        case '<' -> {
                            if (bracket != '>') return bracket;
                            else brackets.remove(0);
                        }
                    }
                }
            }
        }
        return ' ';
    }

    private static int getSumOfBrackets(List<Character> brackets) {
        int sum = 0;
        for (char bracket: brackets) {
            switch (bracket) {
                case ')' -> sum += 3;
                case ']' -> sum += 57;
                case '}' -> sum += 1197;
                case '>' -> sum += 25137;
            }
        }
        return sum;
    }

    public static long countV2() {
        final String path = "./src/main/resources/Day10/input.txt";
        return countV2(path);
    }

    public static long countV2(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        List<List<Character>> bracketsFound = new ArrayList<>();
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                List<Character> bracketsInLine;
                bracketsInLine = checkUncorrupted(line);
                if (!bracketsInLine.isEmpty()) {
                    bracketsFound.add(bracketsInLine);
                }
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        out.println(bracketsFound.size());
        return getMedianSumOfBrackets(bracketsFound);
    }

    private static List<Character> checkUncorrupted(String line) {
        List<Character> brackets = new LinkedList<>();
        char[] bracketArray = line.toCharArray();
        for (char bracket : bracketArray) {
            switch (bracket) {
                case '(' -> brackets.add(0, ')');
                case '[' -> brackets.add(0, ']');
                case '{' -> brackets.add(0, '}');
                case '<' -> brackets.add(0, '>');
                case ')', ']', '}', '>' -> {
                    char bracketInStuck = brackets.get(0);
                    if (bracket == bracketInStuck) brackets.remove(0);
                    else return Collections.emptyList();
                }
            }
        }
        return brackets;
    }

    private static long getMedianSumOfBrackets(List<List<Character>> bracketsFound) {
        long[] sum = new long[bracketsFound.size()];
        for (int i = 0; i < bracketsFound.size(); i++) {
            sum[i] = getSumOfBracketsInLine(bracketsFound.get(i));
            printBracketsInLine(bracketsFound.get(i));
            out.print(" ");
            out.println(sum[i]);
        }
        Arrays.sort(sum);
        out.println(bracketsFound.size());
        return sum[bracketsFound.size() / 2];
    }

    private static long getSumOfBracketsInLine(List<Character> bracketsInLine) {
        long sum = 0;
        for (char bracket: bracketsInLine) {
            sum *= 5;
            switch (bracket) {
                case ')' -> sum += 1;
                case ']' -> sum += 2;
                case '}' -> sum += 3;
                case '>' -> sum += 4;
            }
        }
        return sum;
    }

    private static void printBracketsInLine(List<Character> bracketsInLine) {
        for (char bracket: bracketsInLine) {
            out.print(bracket);
        }
    }
}
