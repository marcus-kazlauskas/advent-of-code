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
}
