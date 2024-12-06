package com.adventofcode.year_2024.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        //read the input
        BufferedReader bf = new BufferedReader(new FileReader("/home/prabhat/IdeaProjects/AdventOfCode/src/main/java/com/adventofcode/year_2024/day6/input.txt"));
        String line;

        ArrayList<char[]> layout = new ArrayList<>();
        while ((line = bf.readLine()) != null) {
            char[] entry = line.toCharArray();
            ;


            layout.add(entry);
        }
        int[] guard = new int[2];
        //finding guard starting position
        for (int i = 0; i < layout.size(); i++) {
            for (int j = 0; j < layout.get(i).length; j++) {
                if (layout.get(i)[j] == '^') {
                    guard[0] = i;
                    guard[1] = j;
                    break;
                }
            }
        }
        ArrayList<char[]> layoutPartOne = new ArrayList<>(layout);

        partOne(layoutPartOne, guard);

        int count = 0;

        for (char[] chars : layoutPartOne) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    count++;
                }
                System.out.print(aChar);

            }
            System.out.println();
        }

        System.out.println("part 1 --> " + count);


        //changing each location and checking if we can make guard go in loop
        int loop = 0;
        for (int i = 0; i < layout.size(); i++) {
            for (int j = 0; j < layout.get(i).length; j++) {

                //if gurads starting position or obstacle is already present ignore
                if ((i == guard[0] && j == guard[1]) || layout.get(i)[j] == '#') {
                    continue;
                }
                //at each location change the normal character to obstacle and see if the loop is formed

                layout.get(i)[j] = '#';

                if (partTwo(layout, guard)) {
                    loop++;
                }
                layout.get(i)[j] = '.'; //reverting back and check


            }
        }

        System.out.println("part 2 --> " + loop);

    }


    static void partOne(List<char[]> layout, int[] guard) {

        int[][] direction = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // up , right, bottom, left;

        //starting position for the guard, and it will start moving to up by default;
        int row = guard[0];
        int col = guard[1];

        int dir = 0;//pointing to up;


        //now check at which point guard moves out of bound

        while (true) {
            //if we are at the position then mark it as visited by assign it the value of 'X'
            layout.get(row)[col] = 'X';

            //check if the guard would face any obstacle moving the direction guard is facing
            int xDir = direction[dir][0];
            int yDir = direction[dir][1];

            int nextRow = row + xDir;
            int nextCol = col + yDir;

            //checking if next move will be in out of bound
            if ((nextRow < 0 || nextCol < 0) || (nextRow > layout.size() - 1 || nextCol > layout.get(0).length - 1)) {
                break;
            }

            if (layout.get(nextRow)[nextCol] == '#') {

                dir += 1;
                dir %= direction.length; // keeping it in bounds;
                continue;
            }

            //if we can move in the direction then assign the new value;
            row = nextRow;
            col = nextCol;

        }

    }

    static boolean partTwo(List<char[]> layout, int[] guard) {
        // assume  there is no loop found;
        boolean loopDetected = false;
        int[][] direction = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // up , right, bottom, left;

        //starting position for the guard, and it will start moving to up by default;
        int row = guard[0];
        int col = guard[1];

        int dir = 0;//pointing to up;

        HashSet<String> visited = new HashSet<>();
        //now check at which point guard moves out of bound

        while (true) {


            //if we are at the position then mark it as visited by assign it the value of 'X'
            layout.get(row)[col] = 'X';

            //check if the guard would face any obstacle moving the direction guard is facing
            int xDir = direction[dir][0];
            int yDir = direction[dir][1];

            int nextRow = row + xDir;
            int nextCol = col + yDir;
            //checking if next move will be in out of bound
            if ((nextRow < 0 || nextCol < 0) || (nextRow > layout.size() - 1 || nextCol > layout.get(0).length - 1)) {
                break;
            }

            if (layout.get(nextRow)[nextCol] == '#') {

                dir += 1;
                dir %= direction.length; // keeping it in bounds;

                //if we encounter the obstacle and change direction check if we have been at same location and move in same direction
                if (visited.contains(row + "," + col + "," + dir)) {
                    loopDetected = true;
                    break;
                }
                visited.add(row + "," + col + "," + dir);
                continue;
            }

            //if we can move in the direction then assign the new value;
            row = nextRow;
            col = nextCol;

        }
        return loopDetected;
    }
}
