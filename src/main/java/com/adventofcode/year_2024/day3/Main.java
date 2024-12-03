package com.adventofcode.year_2024.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {


        //read the input
        BufferedReader bf = new BufferedReader(new FileReader("/home/prabhat/IdeaProjects/AdventOfCode/src/main/java/com/adventofcode/year_2024/day3/input.txt"));
        String line;
        long sum = 0;
        long sum2 = 0;

        // Part 1

        boolean active = true;
        while ((line = bf.readLine()) != null) {
            String patter = "mul\\(\\d{1,3},\\d{1,3}\\)";
            Pattern p = Pattern.compile(patter);

            Matcher m = p.matcher(line);

            while (m.find()) {
                Pattern number = Pattern.compile("\\d+,\\d+");

                Matcher numberMatcher = number.matcher(m.group());

                while (numberMatcher.find()) {
                    String[] numberString = numberMatcher.group().split(",");
                    sum += Long.parseLong(numberString[0]) * Long.parseLong(numberString[1]);
                }

            }

            //part 2 code
            long[] ans = part2(line, active);
            sum2 += ans[0];
            active = ans[1] == 1; //keep track of last do or don;t so that we can process the next line accordingly
        }


        System.out.println("Part One: " + sum);
        System.out.println("Part Two: " + sum2);

    }


    static long[] part2(String input, boolean active) {


        String patter = "(mul\\(\\d{1,3},\\d{1,3}\\))|(do\\(\\))|(don't\\(\\))";
        Pattern p = Pattern.compile(patter);

        Matcher m = p.matcher(input);
        long sum = 0;
        while (m.find()) {
            if (m.group(1) != null) {
                System.out.println((active ? "processing" : "skipping") + " : " + m.group(1));
                if (active) {
                    Pattern number = Pattern.compile("\\d{1,3},\\d{1,3}");
                    Matcher numberMatcher = number.matcher(m.group(1));
                    while (numberMatcher.find()) {
                        String[] numberString = numberMatcher.group().split(",");
                        sum += Long.parseLong(numberString[0]) * Long.parseLong(numberString[1]);
                    }
                }
            } else {
                System.out.print("chaning active from " + active);
                active = m.group(2) != null;
                System.out.println(", to " + active);

            }
        }


        return new long[]{sum, active ? 1 : 0};  //active value of  1 means do and 0 means don't
    }


}