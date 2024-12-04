package com.adventofcode.year_2024.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {


        //read the input
        BufferedReader bf = new BufferedReader(new FileReader("/home/prabhat/IdeaProjects/AdventOfCode/src/main/java/com/adventofcode/year_2024/day4/input.txt"));
        String line;


        String[] input = new String[140];


        // Part 1
        int index = 0;
        while ((line = bf.readLine()) != null) {
            //get the frequency of each character
            input[index++] = line;


        }

        countOfXmas(input);
        countOfXmases(input);


    }

    private static void countOfXmases(String[] lines) {
        int count = 0;
        //starting with 1 as if left most element or top most element is 'A' we can do anything
        // checking till 2nd last row and 2nd element as the 'A' at the end won't be able to create required structure
        for (int i = 1; i < lines.length - 1; i++) {
            for (int j = 1; j < lines[0].length() - 1; j++) {

                if (lines[i].charAt(j) == 'A') {
                    //if we have 'A' lets check if we can make 'MAS' in 'X' shape
                    count += xmases(lines, i, j);

                }
            }

        }

        System.out.println(count);
    }

    private static int xmases(String[] lines, int i, int j) {
        int count = 0;

        //diagonally down left
        // M            S
        //  A             A
        //    S             M
        if (((lines[i - 1].charAt(j - 1) == 'M' && lines[i + 1].charAt(j + 1) == 'S')
                || (lines[i + 1].charAt(j + 1) == 'M' && lines[i - 1].charAt(j - 1) == 'S')
        ) && (
                (lines[i + 1].charAt(j - 1) == 'M' && lines[i - 1].charAt(j + 1) == 'S')
                        || (lines[i - 1].charAt(j + 1) == 'M' && lines[i + 1].charAt(j - 1) == 'S')
        )) {
            count++;

        }


        return count;
    }


    public static void countOfXmas(String[] line) {
        int count = 0;
        for (int i = 0; i < line.length; i++) {
            for (int j = 0; j < line[0].length(); j++) {

                if (line[i].charAt(j) == 'X') {
                    //if we have 'X' lets check if we can make 'XMAS'
                    count += xmas(line, i, j);

                }
            }

        }

        System.out.println(count);

    }


    public static int xmas(String[] line, int i, int j) {
        int count = 0;

        //check if we can make 'xmas' horizontally

        //horizontally backward
        if (j >= 3) {
            if (line[i].charAt(j) == 'X' && line[i].charAt(j - 1) == 'M' && line[i].charAt(j - 2) == 'A' && line[i].charAt(j - 3) == 'S') {
                count++;
            }
        }
        //at least there are 3 elements after the current position of 'j'
        if (j <= line[i].length() - 3 - 1) {

            if (line[i].charAt(j) == 'X' && line[i].charAt(j + 1) == 'M' && line[i].charAt(j + 2) == 'A' && line[i].charAt(j + 3) == 'S') {
                count++;
            }
        }

        //vertically upward
        if (i >= 3) {

            if (line[i].charAt(j) == 'X' && line[i - 1].charAt(j) == 'M' && line[i - 2].charAt(j) == 'A' && line[i - 3].charAt(j) == 'S') {
                count++;
            }

        }

        //vertically downward
        if (i <= line.length - 3 - 1) {

            if (line[i].charAt(j) == 'X' && line[i + 1].charAt(j) == 'M' && line[i + 2].charAt(j) == 'A' && line[i + 3].charAt(j) == 'S') {
                count++;
            }

        }


        //diagonally upward moving right
        if (i >= 3 && j <= line[i].length() - 3 - 1) {

            if (line[i].charAt(j) == 'X' && line[i - 1].charAt(j + 1) == 'M' && line[i - 2].charAt(j + 2) == 'A' && line[i - 3].charAt(j + 3) == 'S') {
                count++;
            }

        }

        //diagonally upward moving left
        if (i >= 3 && j >= 3) {

            if (line[i].charAt(j) == 'X' && line[i - 1].charAt(j - 1) == 'M' && line[i - 2].charAt(j - 2) == 'A' && line[i - 3].charAt(j - 3) == 'S') {
                count++;
            }

        }


        //diagonally downward moving right
        if (i <= line.length - 3 - 1 && j <= line[i].length() - 3 - 1) {

            if (line[i].charAt(j) == 'X' && line[i + 1].charAt(j + 1) == 'M' && line[i + 2].charAt(j + 2) == 'A' && line[i + 3].charAt(j + 3) == 'S') {
                count++;
            }

        }
        //diagonally downward moving left
        if (i <= line.length - 3 - 1 && j >= 3) {

            if (line[i].charAt(j) == 'X' && line[i + 1].charAt(j - 1) == 'M' && line[i + 2].charAt(j - 2) == 'A' && line[i + 3].charAt(j - 3) == 'S') {
                count++;
            }

        }


        return count;

    }

}