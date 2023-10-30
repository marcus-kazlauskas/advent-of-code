package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void testDay2CountV2() {
        assert Main.day2CountV2("./src/main/resources/Day2/input_test.txt") == 900;
    }

    @Test
    public void testDay3Count() {
        assert Main.day3Count("./src/main/resources/Day3/input_test.txt") == 198;
    }

    @Test
    public void testDay3CountV2() {
        assertEquals(
                230,
                Main.day3CountV2("./src/main/resources/Day3/input_test.txt")
        );
    }

    @Test
    public void testDay4Count() {
        assertEquals(
                4512,
                Day4.day4Count("./src/main/resources/Day4/input_test.txt")
        );
    }

    @Test
    public void testDay4CountV2() {
        assertEquals(
                1924,
                Day4.day4CountV2("./src/main/resources/Day4/input_test.txt")
        );
    }

    @Test
    public void testDay5Count() {
        assertEquals(
                5,
                Day5.day5Count("./src/main/resources/Day5/input_test.txt", 10)
        );
    }

    @Test
    public void testDay5CountV2() {
        assertEquals(
                12,
                Day5.day5CountV2("./src/main/resources/Day5/input_test.txt", 10)
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
}
