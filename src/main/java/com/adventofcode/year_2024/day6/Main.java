package com.adventofcode.year_2024.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        int gurad[] = new int[2];
        //finding guard starting position
        for (int i = 0; i < layout.size(); i++) {
            for (int j = 0; j < layout.get(i).length; j++) {
                if (layout.get(i)[j] == '^') {
                    gurad[0] = i;
                    gurad[1] = j;
                    break;
                }
            }
        }

        int direction[][] = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // up , right, bottom, left;

        //starting position for the guard and it will start moving to up by default;
        int row = gurad[0];
        int col = gurad[1];

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
            System.out.println("row =" + row + ", col=" + col);
            System.out.println("nextRow = " + nextRow + ", nextCol = " + nextCol);

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

        int count = 0;

        for (char[] chars : layout) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    count++;
                }
                System.out.print(aChar);

            }
            System.out.println();
        }

        System.out.println("part 1 -->" + count);

    }


}
