package org.example;

import org.junit.Test;

import java.util.Arrays;

import static org.example.Day25.*;
import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void testDay2CountV2() {
        assert Day2.countV2("./src/main/resources/Day2/input_test.txt") == 900;
    }

    @Test
    public void testDay3Count() {
        assert Day3.count("./src/main/resources/Day3/input_test.txt") == 198;
    }

    @Test
    public void testDay3CountV2() {
        assertEquals(
                230,
                Day3.countV2("./src/main/resources/Day3/input_test.txt")
        );
    }

    @Test
    public void testDay4Count() {
        assertEquals(
                4512,
                Day4.count("./src/main/resources/Day4/input_test.txt")
        );
    }

    @Test
    public void testDay4CountV2() {
        assertEquals(
                1924,
                Day4.countV2("./src/main/resources/Day4/input_test.txt")
        );
    }

    @Test
    public void testDay5Count() {
        assertEquals(
                5,
                Day5.count("./src/main/resources/Day5/input_test.txt", 10)
        );
    }

    @Test
    public void testDay5CountV2() {
        assertEquals(
                12,
                Day5.countV2("./src/main/resources/Day5/input_test.txt", 10)
        );
    }

    public static String DAY6_PATH = "./src/main/resources/Day6/input_test.txt";

    @Test
    public void testDay6Count() {
        assertEquals(
                26,
                Day6.count(DAY6_PATH, 18)
        );
    }

    @Test
    public void testDay6CountSeparately() {
        Integer[] count = getCountArray();
        Integer[] expected = new Integer[count.length];
        expected[0] = 5;
        expected[1] = 4;
        expected[2] = 5;
        expected[3] = 7;
        expected[4] = 5;
        System.out.printf("expected -- %s\n", Arrays.toString(expected));
        System.out.printf("actual -- %s\n", Arrays.toString(count));
        assert Arrays.deepEquals(expected, count);
    }

    public static Integer[] getCountArray() {
        int[] school = Day6.readDaysToBreeding(DAY6_PATH);
        System.out.printf("school -- %s\n", Arrays.toString(school));
        Integer[] count = new Integer[school.length];
        for (int i = 0; i < school.length; i++) {
            count[i] = Day6.countAllFish(18, school[i]);
            System.out.println();
        }
        return count;
    }

    @Test
    public void testDay6CountDays() {
        assertEquals(
                5934,
                Day6.count(DAY6_PATH, Day6.DAYS)
        );
    }

    @Test
    public void testDay6CountV2Days() {
        assertEquals(
                5934L,
                Day6.countV2(DAY6_PATH, Day6.DAYS)
        );
    }

    @Test
    public void testDay6CountV2DaysForever() {
        assertEquals(
                26984457539L,
                Day6.countV2(DAY6_PATH, Day6.DAYS_FOREVER)
        );
    }

    @Test
    public void testDay10Count() {
        assertEquals(
                26397,
                Day10.count("./src/main/resources/Day10/input_test.txt")
        );
    }

    @Test
    public void testDay10CountV2() {
        assertEquals(
                288957,
                Day10.countV2("./src/main/resources/Day10/input_test.txt")
        );
    }

    @Test
    public void testDay25Count() {
        assertEquals(
                58,
                Day25.count("./src/main/resources/Day25/input_test.txt")
        );
    }

    @Test
    public void testDay25PrintSteps() {
        final int N = 10;
        char[][] situation = saveState("./src/main/resources/Day25/input_test.txt");
        boolean step;
        for (int i = 1; i <= N; i++) {
            step = nextStep(situation);
            printState(i, situation);
        }
    }
}
