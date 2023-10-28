package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class Day4 {
    public static int day4Count() {
        final String path = "./src/main/resources/Day4/input.txt";
        return day4Count(path);
    }

    public static int day4Count(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        int[] numbers = {0};
        List<int[][]> boards = new ArrayList<>();
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            numbers = saveNumbers(scanner);
            saveBoards(scanner, boards);
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        return checkBoards(boards, numbers);
    }

    private static int[] saveNumbers(Scanner scanner) {
        String[] numbersInString = scanner.nextLine().split(",");
        int numbersCount = numbersInString.length;
        int[] numbers = new int[numbersCount];
        for (int i = 0; i < numbersCount; i++) {
            numbers[i] = Integer.parseInt(numbersInString[i]);
        }
        return numbers;
    }

    private static void saveBoards(Scanner scanner, List<int[][]> boards) {
        while (scanner.hasNext()) {
            scanner.nextLine();
//            String emptyLine = scanner.nextLine();
//            out.println(emptyLine);
            saveBoard(scanner, boards);
        }
    }

    private static void saveBoard(Scanner scanner, List<int[][]> boards) {
        int[][] board = new int[5][5];
        String[] numbersInString;
        for (int i = 0; i < 5; i++) {
            numbersInString = scanner.nextLine().split("\\s+");
            int[] numbers = new int[5];
            for (int j = 0; j < 5; j++) {
                if (numbersInString[0].equals("")) out.print(numbersInString[j + 1]);
//                else out.print(numbersInString[j]);
//                out.print("=");
                if (numbersInString[0].equals("")) numbers[j] = Integer.parseInt(numbersInString[j + 1]);
                else numbers[j] = Integer.parseInt(numbersInString[j]);
//                out.print(numbers[j]);
//                out.println();
            }
            board[i] = numbers;
        }
        boards.add(board);
    }

    private static int checkBoards(List<int[][]> boards, int[] numbers) {
        for (int j : numbers) {
            out.printf("%d ", j);
        }
        out.println();
        for (int number: numbers) {
            out.printf("number=%d\n", number);
            for (int i = 0; i < boards.size(); i++) {
                out.printf("board%d\n", i + 1);
                int[][] board = boards.get(i);
                printBoard(board, "before");
                markNumber(board, number);
                printBoard(board, "after");
                if (checkRows(board) || checkColumns(board)) return sumOfNumbers(board) * number;
                boards.set(i, board);
            }
        }
        return 0;
    }

    private static void markNumber(int[][] board, int number) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == number) board[i][j] = -1;
            }
        }
    }

    private static int sumOfNumbers(int[][] board) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] != -1) sum += board[i][j];
            }
        }
        return sum;
    }

    private static boolean checkRows(int[][] board) {
        int count;
        for (int[] line: board) {
            count = 0;
            for (int j : line) {
                if (j == -1) count++;
            }
            if (count == 5) return true;
        }
        return false;
    }

    private static boolean checkColumns(int[][] board) {
        int count;
        for (int j = 0; j < 5; j++) {
            count = 0;
            for (int i = 0; i < 5; i++) {
                if (board[i][j] == -1) count++;
            }
            if (count == 5) return true;
        }
        return false;
    }

    private static void printBoard(int[][] board, String message) {
        out.println(message);
        for (int[] line: board) {
            for (int num: line) {
                out.printf("%1$d ", num);
            }
            out.println();
        }
    }

    public static int day4CountV2() {
        final String path = "./src/main/resources/Day4/input.txt";
        return day4CountV2(path);
    }

    public static int day4CountV2(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        int[] numbers = {0};
        List<int[][]> boards = new ArrayList<>();
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            numbers = saveNumbers(scanner);
            saveBoards(scanner, boards);
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        return checkBoardsV2(boards, numbers);
    }

    private static int checkBoardsV2(List<int[][]> boards, int[] numbers) {
        for (int j : numbers) {
            out.printf("%d ", j);
        }
        out.println();
        for (int number: numbers) {
            out.printf("number=%d\n", number);
            for (int i = 0; i < boards.size(); i++) {
                out.printf("board%d\n", i + 1);
                int[][] board = boards.get(i);
                printBoard(board, "before");
                markNumber(board, number);
                printBoard(board, "after");
                if (checkRows(board) || checkColumns(board)) {
                    if (boards.size() == 1) return sumOfNumbers(board) * number;
                    else {
                        boards.remove(i);
                        i--;
                    }
                } else boards.set(i, board);
            }
        }
        return 0;
    }
}
