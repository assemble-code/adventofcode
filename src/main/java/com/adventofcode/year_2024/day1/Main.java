package com.adventofcode.year_2024.day1;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();


        List<Integer> leftList = new ArrayList<>();
        HashMap<Integer, Integer> rightFreqMap = new HashMap<>();

        //read the input
        BufferedReader bf = new BufferedReader(new FileReader("/home/prabhat/IdeaProjects/AdventOfCode/src/main/java/com/adventofcode/year_2024/day1/input.txt"));
        String line;
        while ((line = bf.readLine()) != null) {
            String[] tokens = line.split("\s\s\s");
            //for calculating distance
            left.add(Integer.parseInt(tokens[0]));
            right.add(Integer.parseInt(tokens[1]));
            //lst of element at left
            leftList.add(Integer.parseInt(tokens[0]));
            //freq of element at right
            rightFreqMap.put(Integer.parseInt(tokens[1]), rightFreqMap.getOrDefault(Integer.parseInt(tokens[1]), 0) + 1);

        }
        //now calculate the distance and
        long distance = 0;
        while (!left.isEmpty()) {

            distance += Math.abs(left.poll() - right.poll());
        }
        System.out.println("Distance: " + distance);


        long similarity = 0;

        for (int num : leftList) {

            similarity += num * (rightFreqMap.getOrDefault(num, 0));
        }
        System.out.println("Similarity: " + similarity);
    }


}