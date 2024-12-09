package com.adventofcode.year_2024.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        //read the input
        BufferedReader bf = new BufferedReader(new FileReader("/home/prabhat/IdeaProjects/AdventOfCode/src/main/java/com/adventofcode/year_2024/day9/input.txt"));
        //input is single line
        String line = bf.readLine();

        Long[] blockWithSpaces = generateBlockWithSpaces(line);
        ////  System.out.println("block representation -> " + blockWithSpaces);

        Long[] rotatedToFillSpaces = rotateAsPerPart1(blockWithSpaces);

        long partOneSum = checkSum(rotatedToFillSpaces);
        System.out.println("part one sum -> " + partOneSum);


        Long[] blocks = generateBlockWithSpaces(line);

        Long[] moveAsPerPart2 = rotateAsPerPart2(blocks);
        long partTwoSum = checkSum(moveAsPerPart2);

        System.out.println("part two sum -> " + partTwoSum);
    }

    private static Long[] rotateAsPerPart2(Long[] blocks) {
        int currFile = -1; /// space
        int currFileLen = 0;

        //iterationg block in reverse
        for (int i = blocks.length - 1; i > 0; i--) {

            if (blocks[i] != -1) {
                currFileLen += 1;
                currFile = i;
                if (!Objects.equals(blocks[i], blocks[i - 1])) {

                    if (currFileLen != 0) {
                        moveFile(blocks, currFileLen, currFile);
                        currFileLen = 0;

                    }

                }


            } else {
                if (currFileLen != 0) {

                    moveFile(blocks, currFileLen, currFile);
                    currFileLen = 0;

                }

            }


        }


        return blocks;
    }

    private static void moveFile(Long[] blocks, int length, int originIndex) {
        int freeSpace = 0;
        int freeSpaceIdx = -1;

        for (int i = 1; i <= originIndex; i++) {

            if ((!blocks[i - 1].equals(-1L)) && (blocks[i].equals(-1L))) {

                if (freeSpace >= length) {
                    writeFile(blocks, blocks[originIndex], length, freeSpaceIdx);
                    clearFile(blocks, length, originIndex);
                }
                freeSpaceIdx = i;

                freeSpace = 1;
                continue;
            }
            if (blocks[i - 1].equals(-1L) && !blocks[i].equals(-1L)) {

                if (freeSpace >= length) {
                    writeFile(blocks, blocks[originIndex], length, freeSpaceIdx);
                    clearFile(blocks, length, originIndex);
                }
                freeSpaceIdx = -1;
                freeSpace = 0;

            }

            if (blocks[i].equals(-1L)) {
                freeSpace++;
            }

        }


    }

    private static void clearFile(Long[] blocks, int currFileLen, int idx) {

        for (int i = 0; i < currFileLen; i++) {

            blocks[idx + i] = -1L;
        }

    }

    private static void writeFile(Long[] blocks, Long block, int currFileLen, int freeSpaceIdx) {
        for (int i = 0; i < currFileLen; i++) {

            blocks[freeSpaceIdx + i] = block;
        }

    }

    private static long checkSum(Long[] rotatedToFillSpaces) {
        long sum = 0;

        for (int blockIdx = 0; blockIdx < rotatedToFillSpaces.length; blockIdx++) {
            if (rotatedToFillSpaces[blockIdx] == -1) {
                continue;
            }
            sum += blockIdx * rotatedToFillSpaces[blockIdx];
        }

        return sum;
    }

    private static Long[] rotateAsPerPart1(Long[] blockWithSpaces) {

        int start = 0;
        int end = blockWithSpaces.length - 1;

        while (start < end) {
            if (blockWithSpaces[start] == -1 && blockWithSpaces[end] != -1) {
                //swap location
                long t = blockWithSpaces[end];
                blockWithSpaces[end] = blockWithSpaces[start];
                blockWithSpaces[start] = t;
                end--;
                start++;
                continue;
            }

            if (blockWithSpaces[end] != -1) {
                start++;
            }

            if (blockWithSpaces[end] == -1) {
                end--;
            }

        }
        return blockWithSpaces;
    }

    private static Long[] generateBlockWithSpaces(String line) {
        List<Long> arr = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {

            int i1 = Integer.parseInt("" + line.charAt(i));
            for (int j = 0; j < i1; j++) {
                if (i % 2 == 0) {
                    arr.add((long) (i / 2));
                } else {
                    arr.add((long) -1); /// -1 denotes space
                }
            }
        }
        return arr.toArray(new Long[0]);
    }
}
