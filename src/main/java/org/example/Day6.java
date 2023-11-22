package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.System.out;

public class Day6 {
    public static final int DAYS = 80;
    public static final int DAYS_FOREVER = 256;
    public static final int CYCLE = 7;
    public static final int DAYS_TO_BREEDING = 8;

    public static int count() {
        final String path = "./src/main/resources/Day6/input.txt";
        return count(path, DAYS);
    }

    public static int count(String path, int lifetime) {
        int[] referenceCounts = new int[CYCLE];
        for (int i = 0; i < CYCLE; i++) {
            int referenceCount = countAllFish(lifetime, i);
            referenceCounts[i] = referenceCount;
            out.printf("%d days to breeding - %d babies\n", i, referenceCount);
        }

        int[] school = readDaysToBreeding(path);
        int count = 0;
        out.println(Arrays.toString(school));
        out.println();
        for (int fish: school) {
            count += referenceCounts[fish];
        }
        return count;
    }

    public static int[] readDaysToBreeding(String path) {
        Path inputPath = Paths.get(path);
        File inputFile = new File(inputPath.toUri());
        int[] school = new int[1];
        try (Scanner scanner = new Scanner(inputFile, StandardCharsets.UTF_8)) {
            String[] inputTimers = scanner.nextLine().split(",");
            int length = inputTimers.length;
            school = new int[length];
            for (int i = 0; i < length; i++) {
                school[i] = Integer.parseInt(inputTimers[i]);
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        }
        return school;
    }
    
    public static int countBabies(int parentLifetime, int daysToBreeding) {
        int maxBabyLifetime = parentLifetime - 1 - daysToBreeding;
        if (maxBabyLifetime >= 0) {
            return maxBabyLifetime / CYCLE + 1;
        } else {
            return 0;
        }
    }

    public static int getLifetime(int parentLifetime, int daysToBreeding, int i) {
        int maxBabyLifetime = parentLifetime - 1 - daysToBreeding;
        return maxBabyLifetime - CYCLE * i;
    }

    public static int[] getLifetimes(int parentLifetime, int daysToBreeding, int babiesCount) {
        int[] lifetimes = new int[babiesCount];
        for (int i = 0; i < babiesCount; i++) {
            lifetimes[i] = getLifetime(parentLifetime, daysToBreeding, i);
        }
        return lifetimes;
    }

    public static int countFish(int fishLifetime, int daysToBreeding) {
        int count = countBabies(fishLifetime, daysToBreeding);
//        out.printf("countBabies=%d\n", count);
        if (count > 0) {
            int[] lifetimes = getLifetimes(fishLifetime, daysToBreeding, count);
            for (int lifetime: lifetimes) {
                int growth = countFish(lifetime, DAYS_TO_BREEDING);
//                out.printf("lifetime=%d, growth=%d\n", lifetime, growth);
                count += growth;
            }
        }
        return count;
    }

    public static int countAllFish(int fishLifeTime, int daysToBreeding) {
        return countFish(fishLifeTime, daysToBreeding) + 1;
    }

    public static long countV2() {
        final String path = "./src/main/resources/Day6/input.txt";
        return countV2(path, DAYS_FOREVER);
    }

    public static long countV2(String path, int lifetime) {
        long[] referenceCounts = new long[CYCLE];
        for (int i = 0; i < CYCLE; i++) {
            long referenceCount = countAllFishV2(lifetime, i);
            referenceCounts[i] = referenceCount;
            out.printf("%d days to breeding - %d babies\n", i, referenceCount);
        }

        int[] school = readDaysToBreeding(path);
        long count = 0;
        for (int fish: school) {
            count += referenceCounts[fish];
        }
        return count;
    }

    public static long countFishV2(int fishLifetime, int daysToBreeding) {
        int countBabies = countBabies(fishLifetime, daysToBreeding);
        long countAll = 0;
        if (countBabies > 0) {
            countAll = countBabies;
            int[] lifetimes = getLifetimes(fishLifetime, daysToBreeding, countBabies);
            for (int lifetime: lifetimes) {
                long growth = countFishV2(lifetime, DAYS_TO_BREEDING);
                countAll += growth;
            }
        }
        return countAll;
    }

    public static long countAllFishV2(int fishLifeTime, int daysToBreeding) {
        return countFishV2(fishLifeTime, daysToBreeding) + 1;
    }
}
