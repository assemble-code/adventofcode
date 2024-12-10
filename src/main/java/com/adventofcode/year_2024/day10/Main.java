package com.adventofcode.year_2024.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //read the input
        BufferedReader bf = new BufferedReader(new FileReader("/home/prabhat/IdeaProjects/AdventOfCode/src/main/java/com/adventofcode/year_2024/day10/input.txt"));

        String line;
        List<int[]> lines = new ArrayList<>();
        while ((line = bf.readLine()) != null) {

            char[] split = line.toCharArray();
            int[] entry = new int[split.length];

            for (int i = 0; i < split.length; i++) {
                entry[i] = split[i] - '0';
            }

            lines.add(entry);
        }

        for (int[] a : lines) {
            System.out.println(Arrays.toString(a));
        }

        long part1 = Part1(lines);
        System.out.println("Part 1--> " + part1);

        long part2 = Part2(lines);
        System.out.println("Part 2--> " + part2);


    }

    private static long Part1(List<int[]> lines) {
        long sum = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length; j++) {

                //from each 0 elevation check if summit can be reached

                if (lines.get(i)[j] == 0) {
                    //  System.out.println("searching peak for -->" + i + "," + j);

                    sum += dfsPart1(lines, i, j, 0, new HashSet<>());


                }


            }
        }


        return sum;
    }

    private static long Part2(List<int[]> lines) {
        long sum = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length; j++) {

                //from each 0 elevation check if summit can be reached

                if (lines.get(i)[j] == 0) {
                    //  System.out.println("searching peak for -->" + i + "," + j);

                    sum += dfsPart2(lines, i, j, 0);

                }

            }
        }


        return sum;
    }

    private static int dfsPart2(List<int[]> lines, int i, int j, int elevation) {
        //check if within bounds
        if ((i < 0 || j < 0) || (i > lines.size() - 1 || j > lines.get(i).length - 1)) {
            return 0;
        }
        if (lines.get(i)[j] != elevation) {
            return 0;
        }
        if (lines.get(i)[j] == 9) {

            return 1;
        }

        int nextElevation = elevation + 1;

        //from current location check all the four direction to see if the summit can be reached or not
        //but we move in the direction only if the elevation is present


        int a = dfsPart2(lines, i + 1, j, nextElevation);

        int b = dfsPart2(lines, i, j + 1, nextElevation);

        int c = dfsPart2(lines, i - 1, j, nextElevation);

        int d = dfsPart2(lines, i, j - 1, nextElevation);


        return a + b + c + d;
    }


    static int dfsPart1(List<int[]> lines, int i, int j, int elevation, HashSet<String> visited) {
        //check if within bounds
        if ((i < 0 || j < 0) || (i > lines.size() - 1 || j > lines.get(i).length - 1)) {
            return 0;
        }
        if (lines.get(i)[j] != elevation) {
            return 0;
        }
        if (lines.get(i)[j] == 9 && !visited.contains(i + "," + j)) {
            visited.add(i + "," + j);
            return 1;
        }

        int nextElevation = elevation + 1;

        //from current location check all the four direction to see if the summit can be reached or not
        //but we move in the direction only if the elevation is present


        int a = dfsPart1(lines, i + 1, j, nextElevation, visited);

        int b = dfsPart1(lines, i, j + 1, nextElevation, visited);

        int c = dfsPart1(lines, i - 1, j, nextElevation, visited);

        int d = dfsPart1(lines, i, j - 1, nextElevation, visited);


        return a + b + c + d;
    }
}
