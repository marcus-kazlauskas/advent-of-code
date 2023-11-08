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

public class Day3 {
    public static int count() {
        final String path = "./src/main/resources/Day3/input.txt";
        return count(path);
    }

    public static int count(String path) {
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

    public static int countV2() {
        final String path = "./src/main/resources/Day3/input.txt";
        return countV2(path);
    }

    public static int countV2(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        List<char[]> oxygen = new LinkedList<>();
        List<char[]> co2 = new LinkedList<>();
        int length = saveNumbers(oxygen, co2, inputFile);
        findRating(oxygen, length, "oxygen");
        findRating(co2, length, "co2");
        return Integer.valueOf(new String(oxygen.get(0)), 2) *
                Integer.valueOf(new String(co2.get(0)), 2);
    }

    public static int saveNumbers(List<char[]> oxygen, List<char[]> co2, File inputFile) {
        int length = 1;
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
        return length;
    }

    public static void findRating(List<char[]> numbers, int length, String nameRating) {
        int i = 0;
        int j;
        char oxygenCriteria;
        while (numbers.size() > 1 && i < length) {
            j = 0;
            oxygenCriteria = bitCriteria(numbers, i, nameRating);
            while (j < numbers.size()) {
                char[] bits = numbers.get(j);
                if (bits[i] != oxygenCriteria) numbers.remove(j);
                else j++;
            }
            i++;
        }
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
