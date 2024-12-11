package com.adventofcode.year_2024.day11;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        //read the input
        BufferedReader bf = new BufferedReader(new FileReader("/home/prabhat/IdeaProjects/AdventOfCode/src/main/java/com/adventofcode/year_2024/day11/input.txt"));

        String[] line = bf.readLine().split(" ");

        List<Long> strings = Arrays.stream(line).map(Long::parseLong).collect(Collectors.toList());

        /// part 1
        System.out.println("Part one -->" + getStoneCountAfterBlink(strings, 25));

        /// part 2
        long count = getStoneCountAfterBlink(strings, 75);
        System.out.println("Part two -->" + count);
    }

    static List<Long> getStoneAfterBlink(Long input) {
        List<Long> result = new ArrayList<>();

        //rule  1 if value is 0, make it 1
        if (input == 0) {
            result.add(1L);

        }
        //rule 2 has even digits
        else if (String.valueOf(input).length() % 2 == 0) {
            int splitIndex = input.toString().length() / 2;
            Long first = Long.parseLong(String.valueOf(input).substring(0, splitIndex));
            Long second = Long.parseLong(String.valueOf(input).substring(splitIndex));

            //separate it and add as individual stone
            result.add(first);
            result.add(second);

        } else {

            //rule 3 multiply by 2024

            long num = input * 2024;
            result.add(num);
        }
        return result;
    }

    static long getStoneCountAfterBlink(List<Long> input, int blinkCount) {
        HashMap<Long, long[]> cache = new HashMap<>();
        long sum = 0;

        for (Long stone : input) {
            sum += countAfterBlinking(stone, cache, blinkCount);
        }

        return sum;

    }

    private static long countAfterBlinking(Long s, HashMap<Long, long[]> cache, int blinkCount) {

        //check if cache contains the key, else initialise it
        if (cache.containsKey(s)) {
            //cache is 0 it means we have yet calculated the size for the given stone and blink size;
            if (cache.get(s)[blinkCount - 1] != 0) {
                return cache.get(s)[blinkCount - 1];
            }
        } else {
            cache.put(s, new long[75]);
        }

        //if there are no more blink left, return the size that will be achieved after this
        // and store it in the cache for future use
        if (blinkCount == 1) {
            cache.get(s)[blinkCount - 1] = getStoneAfterBlink(s).size();
            return cache.get(s)[blinkCount - 1];
        }

        //iterate over the number of stones that can be formed from current stone
        // and try repeat the process for the stone and check it can be further divided.
        long sum = 0;
        for (Long stone : getStoneAfterBlink(s)) {

            sum += countAfterBlinking(stone, cache, blinkCount - 1);
        }

        cache.get(s)[blinkCount - 1] = sum;


        return sum;
    }


}
