package org.example;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Day8 {
    public static final int DIGITS_NUMBER = 10;
    public static final int OUTPUT_LENGTH = 4;
    public static final int LINE_LENGTH = 14;

    public static int count() {
        final String path = Main.INPUT_PATH.formatted("Day8");
        return count(path);
    }

    public static int count(String path) {
        LinkedList<String[]> digitsList = new LinkedList<>();
        LinkedList<String[]> outputList = new LinkedList<>();
        readValues(path, digitsList, outputList);
        return count1478(outputList);
    }

    private static void readValues(String path, LinkedList<String[]> digitsList, LinkedList<String[]> outputList) {
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

    private static int count1478(LinkedList<String[]> outputList) {
        int count = 0;
        for (String[] output: outputList) {
            for (String value: output) {
                if (is1478(value)) count++;
            }
        }
        return count;
    }

    private static boolean is1478(String value) {
        int length = value.length();
        return length == 2 || length == 4 || length == 3 || length == 7;
    }

    public static int countV2() {
        final String path = Main.INPUT_PATH.formatted("Day8");
        return countV2(path);
    }

    public static int countV2(String path) {
        LinkedList<List<Set<Character>>> digitsList = new LinkedList<>();
        LinkedList<List<Set<Character>>> outputList = new LinkedList<>();
        readValuesV2(path, digitsList, outputList);
        int summary = 0;
        int length = digitsList.size();
        for (int i = 0; i < length; i++) {
            List<Set<Character>> output = outputList.poll();
            out.println(output);
            summary += realNumber(digitsList.poll(), output);
        }
        return summary;
    }

    private static void readValuesV2(
            String path,
            LinkedList<List<Set<Character>>> digitsList,
            LinkedList<List<Set<Character>>> outputList
    ) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                String[] values = scanner.nextLine().split("( \\| )|( )");
//                out.println(Arrays.toString(values));

                List<Set<Character>> digits = new ArrayList<>(LINE_LENGTH);
                List<Set<Character>> output = new ArrayList<>(LINE_LENGTH);
                for (int i = 0; i < LINE_LENGTH; i++) {
                    Set<Character> segments = values[i].chars()
                            .mapToObj(c -> (char) c).collect(Collectors.toSet());
//                    out.printf("%d) segments = %s, size = %d\n", i, segments, segments.size());
                    if (i < DIGITS_NUMBER) digits.add(i, segments);
                    else output.add(i - DIGITS_NUMBER, segments);
                }
                digitsList.add(digits);
                outputList.add(output);
            }
            out.println("All elements collected");
        } catch (Exception e) {
            out.print("ERROR: ");
            out.println(e.getMessage());
        }
    }

    public static int realNumber(List<Set<Character>> digits, List<Set<Character>> output) {
        Dictionary dictionary = new Dictionary(digits);
        int realNumber = 0;
        for (int i = 0; i < OUTPUT_LENGTH; i++) {
            realNumber += dictionary.realDigit(output.get(i), OUTPUT_LENGTH - 1 - i);
        }
        out.printf("%d\n", realNumber);
        return realNumber;
    }

    private static class Dictionary {
        /**
         * Если бы я сделал построчную обработку, то не пришлось бы писать этот класс!
         */
        Set<Character> one = new HashSet<>();
        Set<Character> four = new HashSet<>();

        Dictionary(List<Set<Character>> digits) {
            for (Set<Character> digit: digits) {
                switch (digit.size()) {
                    case 2 -> one = digit;
                    case 4 -> four = digit;
                }
            }
        }

        int oneAndValueSize(Set<Character> outputValue) {
            Set<Character> oneAndValue = new HashSet<>(outputValue);
            oneAndValue.retainAll(one);
            out.printf("%s & %s = %s\n", one, outputValue, oneAndValue);
            return oneAndValue.size();
        }

        int fourAndValueSize(Set<Character> outputValue) {
            Set<Character> fourAndValue = new HashSet<>(outputValue);
            fourAndValue.retainAll(four);
            out.printf("%s & %s = %s\n", four, outputValue, fourAndValue);
            return fourAndValue.size();
        }

        int realDigit(Set<Character> outputValue) {
            int realDigit = -1;
            switch (outputValue.size()) {
                case 2 -> realDigit = 1;
                case 3 -> realDigit = 7;
                case 4 -> realDigit = 4;
                case 5 -> {
                    if (oneAndValueSize(outputValue) == 1) {
                            if (fourAndValueSize(outputValue) == 2) realDigit = 2;
                            else realDigit = 5;
                    } else realDigit = 3;
                }
                case 6 -> {
                    if (oneAndValueSize(outputValue) == 1) realDigit = 6;
                    else {
                        if (fourAndValueSize(outputValue) == 3) realDigit = 0;
                        else realDigit = 9;
                    }
                }
                case 7 -> realDigit = 8;
            }
            out.printf("size = %d -> realDigit = %d\n", outputValue.size(), realDigit);
            return realDigit;
        }

        int realDigit(Set<Character> outputValue, int n) {
            int realDigit = realDigit(outputValue);
            if (realDigit < 0) out.println("ERROR!");
            for (int i = 0; i < n; i++) {
                realDigit *= 10;
            }
            return realDigit;
        }
    }

//    public static Map<Character, Character> dictionary(String[] digits) {
//        String one = "oo";
//        String seven = "sss";
//        String four = "ffff";
//        LinkedList<String> twoThreeFive = new LinkedList<>();
//        LinkedList<String> zeroSixNine = new LinkedList<>();
//        String eight = "eeeeeee";
//        for (String digit: digits) {
//            switch (digit.length()) {
//                case 2 -> one = digit;
//                case 3 -> seven = digit;
//                case 4 -> four = digit;
//                case 5 -> twoThreeFive.add(digit);
//                case 6 -> zeroSixNine.add(digit);
//                case 7 -> eight = digit;
////                default -> out.println("Digit cannot contain more than 7 symbols!");
//            }
//        }
//        Map<Character, Character> dictionary = new HashMap<>();
//        dictionary.put('a', aSegment(one, seven));
//        return dictionary;
//    }

//    public static char aSegment(String one, String seven) {
//        char aSegment = 'a';
//        char[] oneArray = one.toCharArray();
//        char[] sevenArray = seven.toCharArray();
//        Arrays.sort(oneArray);
//        Arrays.sort(sevenArray);
//        int i = 0;
//        while (i < oneArray.length && oneArray[i] == sevenArray[i]) {
//            i++;
//        }
//        aSegment = sevenArray[i];
//        return aSegment;
//    }
}
