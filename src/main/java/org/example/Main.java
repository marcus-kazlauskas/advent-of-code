package org.example;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int taskNumber;
        try (Scanner userInput = new Scanner(in)) {
            taskNumber = userInput.nextInt();
        }
        switch (taskNumber) {
            case 1      -> out.println(Day1.count());
            case 2      -> out.println(Day1.countV2());
            case 3      -> out.println(Day2.count());
            case 4      -> out.println(Day2.countV2());
            case 5      -> out.println(Day3.count());
            case 6      -> out.println(Day3.countV2());
            case 7      -> out.println(Day4.count());
            case 8      -> out.println(Day4.countV2());
            case 9      -> out.println(Day5.count());
            case 10     -> out.println(Day5.countV2());
//            case 11     -> out.println(Day6.count());
//            case 12     -> out.println(Day6.countV2());
//            case 13     -> out.println(Day7.count());
//            case 14     -> out.println(Day7.countV2());
//            case 15     -> out.println(Day8.count());
//            case 16     -> out.println(Day8.countV2());
//            case 17     -> out.println(Day9.count());
//            case 18     -> out.println(Day9.countV2());
            case 19     -> out.println(Day10.count());
            case 20     -> out.println(Day10.countV2());
//            case 21     -> out.println(Day11.count());
//            case 22     -> out.println(Day11.countV2());
//            case 23     -> out.println(Day12.count());
//            case 24     -> out.println(Day12.countV2());
//            case 25     -> out.println(Day13.count());
//            case 26     -> out.println(Day13.countV2());
//            case 27     -> out.println(Day14.count());
//            case 28     -> out.println(Day14.countV2());
//            case 29     -> out.println(Day15.count());
//            case 30     -> out.println(Day15.countV2());
//            case 31     -> out.println(Day16.count());
//            case 32     -> out.println(Day16.countV2());
//            case 33     -> out.println(Day17.count());
//            case 34     -> out.println(Day17.countV2());
//            case 35     -> out.println(Day18.count());
//            case 36     -> out.println(Day18.countV2());
//            case 37     -> out.println(Day19.count());
//            case 38     -> out.println(Day19.countV2());
//            case 39     -> out.println(Day20.count());
//            case 40     -> out.println(Day20.countV2());
//            case 41     -> out.println(Day21.count());
//            case 42     -> out.println(Day21.countV2());
//            case 43     -> out.println(Day22.count());
//            case 44     -> out.println(Day22.countV2());
//            case 45     -> out.println(Day23.count());
//            case 46     -> out.println(Day23.countV2());
//            case 47     -> out.println(Day24.count());
//            case 48     -> out.println(Day24.countV2());
            case 49     -> out.println(Day25.count());
            case 50     -> out.println("ho-ho");
            default     -> out.println("task not found");
        }
    }
}