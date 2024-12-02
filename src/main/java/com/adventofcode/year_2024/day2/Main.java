package com.adventofcode.year_2024.day2;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {


        File file = new File("src/main/java/com/adventofcode/year_2024/day2/input.txt");

        BufferedReader bf = new BufferedReader(new FileReader(file.getAbsoluteFile()));

        String line;
        long safeLevel = 0;
        long safeLevel2 = 0;

        while ((line = bf.readLine()) != null) {
            String[] levels = line.split("\s");
            int ret = checkLevelIsSafe(levels);

            safeLevel += ret;

            //if the level is not safe then we will to see if we can make it safe;
            if (ret == 0) {
                safeLevel2 += checkLevelIsSafeAfterRemoval(levels, 0) ? 1 : 0;
            } else {
                safeLevel2 += ret;
            }
        }
        System.out.println("safeLevel: " + safeLevel);
        System.out.println("safeLevel2: " + safeLevel2);

    }


    /**
     * Checks if level is safe by making sure the condition are met
     *
     * @param levels {@link String}[] containing level information
     * @return {@code 1} if the level is safe, else {@code 0}
     */
    static int checkLevelIsSafe(String[] levels) {
        int direction = 0;// default, 1 for increasing , -1 for decreasing
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.valueOf(levels[0]));
        for (int i = 1; i < levels.length; i++) {
            int level = Integer.parseInt(levels[i]);
            int prev = stack.pop();

            //if the direction is not set change it

            int diff = prev - level;
            //set it only once
            if (direction == 0) {
                direction = diff < 0 ? -1 : 1;
            }

            //if direction changes level is unsafe
            if (direction == 1 && diff < 0) {
                return 0;
            } else if (direction == -1 && diff > 0) {
                return 0;
            }

            //check if the diff fits the criteria
            diff = Math.abs(diff);
            if (diff < 1 || diff > 3) {
                return 0;
            }

            stack.push(level);

        }

        return 1;
    }


    /**
     * Checks if level is safe by making sure the condition are met
     *
     * @param levels {@link String}[] containing level information
     * @return {@code 1} if the level is safe, else {@code 0}
     */
    static boolean checkLevelIsSafeAfterRemoval(String[] levels, int skipIndex) {
        if (levels.length < skipIndex) {
            return false;
        }
        int direction = 0;// default, 1 for increasing , -1 for decreasing
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.valueOf(levels[skipIndex == 0 ? 1 : 0]));//if skip at start, then start from the second position
        boolean unsafeLevel = false;

        for (int i = skipIndex == 0 ? 2 : 1; i < levels.length; i++) {
            //skip the index

            if (i == skipIndex) {
                continue;
            }

            int level = Integer.parseInt(levels[i]);
            int prev = stack.pop();

            //if the direction is not set change it

            int diff = prev - level;
            //set it only once
            if (direction == 0) {
                direction = diff < 0 ? -1 : 1;
            }

            //if direction changes level is unsafe
            if (direction == 1 && diff < 0) {
                unsafeLevel = true;
            } else if (direction == -1 && diff > 0) {
                unsafeLevel = true;
            }

            //check if the diff fits the criteria
            diff = Math.abs(diff);
            if ((diff < 1 || diff > 3)) {
                unsafeLevel = true;
            }
            //if the skipping skipIndex doesn't make it safe break early and try next index;
            if(unsafeLevel) {
                break;
            }
            stack.push(level);

        }
        return !unsafeLevel || checkLevelIsSafeAfterRemoval(levels, skipIndex + 1);
    }
}
