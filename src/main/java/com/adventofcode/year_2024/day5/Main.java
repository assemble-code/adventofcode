package com.adventofcode.year_2024.day5;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        //read the input
        BufferedReader bf = new BufferedReader(new FileReader("/home/prabhat/IdeaProjects/AdventOfCode/src/main/java/com/adventofcode/year_2024/day5/input.txt"));
        String line;

        ArrayList<String> ruleList = new ArrayList<>();
        ArrayList<Integer[]> pagesList = new ArrayList<>();
        //reading the rules for ordering manual
        while (!(line = bf.readLine()).isEmpty()) {

            ruleList.add(line);
        }

        //reading actual page number
        while ((line = bf.readLine()) != null) {
            //split the line and convert it to the array of integer
            pagesList.add(Arrays.stream(line.split(",")).map(Integer::parseInt).toList().toArray(new Integer[0]));
        }


        ArrayList<Integer[]> sortedOrder = new ArrayList<>();
        ArrayList<Integer[]> unSortedOrder = new ArrayList<>();


        for (Integer[] pages : pagesList) {

            boolean allCorrectOrder = isCorrectOrder(pages, ruleList);

            if (allCorrectOrder) {
                sortedOrder.add(pages);
            } else {
                unSortedOrder.add(pages);
            }
        }


        long sum = 0;
        for (int i = 0; i < sortedOrder.size(); i++) sum += sortedOrder.get(i)[sortedOrder.get(i).length / 2];

        System.out.println("--> " + sum);

        puzzleTwo(unSortedOrder, ruleList);


    }

    private static boolean isCorrectOrder(Integer[] pages, ArrayList<String> ruleList) {
        boolean allCorrectOrder = true;

        for (int i = 0; i < pages.length; i++) {
            int x = pages[i];

            for (int j = i + 1; j < pages.length; j++) {
                boolean found = false;

                for (String pageOrderRule : ruleList) {
                    if (pageOrderRule.equals(x + "|" + pages[j])) {
                        found = true;
                        break;
                    }

                }

                if (!found) {
                    allCorrectOrder = false;
                    break;
                }
            }


        }
        return allCorrectOrder;
    }

    private static void puzzleTwo(ArrayList<Integer[]> incorrectOrder, ArrayList<String> rules) {
        int answer = 0;
        ArrayList<Integer> fixedReport = new ArrayList<>();
        for (Integer[] incorrect : incorrectOrder) {
            fixedReport.clear();


            for (int i = 0; i < incorrect.length-1; i++) {

                    Integer current = incorrect[i];
                    String next;

                    next = "" + incorrect[i + 1];

                    if (!fixedReport.contains(current)) {
                        fixedReport.add(i, current);
                    }

                    if (rules.contains(next + "|" + current)) {
                        fixedReport.add(i, Integer.parseInt(next));
                    } else {
                        fixedReport.add(i + 1, Integer.parseInt(next));
                    }

            }

            // Keep looping through to resort
            for (int j = 0; j < 24; j++) {
                for (int i = incorrect.length - 1; i >= 0; i--) {
                    Integer current = fixedReport.get(i);
                    try {
                        Integer previous = fixedReport.get(i - 1);
                        if (rules.contains(current + "|" + previous)) {
                            fixedReport.add(i - 1, current);
                            fixedReport.remove(i + 1);
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
            }

            Integer[] fixedArr = fixedReport.toArray(new Integer[0]);
            answer += fixedArr[(fixedArr.length - 1) / 2];
        }

        System.out.println("Puzzle Two: " + answer);
    }
}
