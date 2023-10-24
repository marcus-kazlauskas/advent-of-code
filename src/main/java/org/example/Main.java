package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int taskNumber;
        try (Scanner userInput = new Scanner(System.in)) {
            taskNumber = userInput.nextInt();
        }
        switch (taskNumber) {
            case 1      -> System.out.println(day1_count());
            default     -> System.out.println("task not found");
        }
    }

    public static int day1_count() {
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
            System.out.println(e.getMessage());
        }
        return counter;
    }
}