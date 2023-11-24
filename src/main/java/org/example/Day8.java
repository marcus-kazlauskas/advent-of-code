package org.example;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;

public class Day8 {
    public static final int DIGITS_NUMBER = 10;
    public static final int LINE_LENGTH = 14;

    public static int count() {
        final String path = "./src/main/resources/Day8/input.txt";
        return count(path);
    }

    public static int count(String path) {
        LinkedList<String[]> digitsList = new LinkedList<>();
        LinkedList<String[]> outputList = new LinkedList<>();
        readValues(path, digitsList, outputList);
        return count1478(outputList);
    }

    public static void readValues(String path, LinkedList<String[]> digitsList, LinkedList<String[]> outputList) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                String[] values = scanner.nextLine().split("( \\| )|( )");
                if (values.length > LINE_LENGTH)
                    throw new Exception("Line is longer than %d".formatted(LINE_LENGTH));
                String[] digits = Arrays.copyOfRange(values, 0, DIGITS_NUMBER);
                String[] output = Arrays.copyOfRange(values, DIGITS_NUMBER, LINE_LENGTH);
                digitsList.add(digits);
                outputList.add(output);
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    public static int count1478(LinkedList<String[]> outputList) {
        int count = 0;
        for (String[] output: outputList) {
            for (String value: output) {
                if (is1478(value)) count++;
            }
        }
        return count;
    }

    public static boolean is1478(String value) {
        int length = value.length();
        return length == 2 || length == 4 || length == 3 || length == 7;
    }
}
