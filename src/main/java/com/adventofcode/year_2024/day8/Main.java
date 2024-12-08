package com.adventofcode.year_2024.day8;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //read the input
        BufferedReader bf = new BufferedReader(new FileReader("/home/prabhat/IdeaProjects/AdventOfCode/src/main/java/com/adventofcode/year_2024/day8/input.txt"));
        String line;
        List<char[]> data = new ArrayList<>();
        while ((line = bf.readLine()) != null) {
            char[] tokens = line.toCharArray();
            data.add(tokens);
        }

        HashMap<Character, List<Point>> charPoint = new HashMap<>();

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.getFirst().length; j++) {
                if (Character.isDigit(data.get(i)[j]) || Character.isLetter(data.get(i)[j])) {
                    Point point = new Point(i, j);
                    charPoint.computeIfAbsent(data.get(i)[j], (s) -> new ArrayList<>());
                    charPoint.get(data.get(i)[j]).add(point);
                }
            }

        }

        findAntiNode(charPoint, data.size());
    }


    static void findAntiNode(HashMap<Character, List<Point>> charPoint, int size) {
        int maxX = size;
        int maxY = size;

        int count = 0;
        HashSet<Point> uniquePointsPart1 = new HashSet<>();
        HashSet<Point> uniquePointsPart2 = new HashSet<>();


        for (Map.Entry<Character, List<Point>> points : charPoint.entrySet()) {
            //if single antenna no need to calculate
            if (points.getValue().size() == 1) {
                continue;
            }
            List<Point> pointList = points.getValue();
            for (int i = 0; i < pointList.size() - 1; i++) {
                for (int j = i + 1; j < pointList.size(); j++) {
                    /// part 1 calculation
                    List<Point> listOfAntiNodes = calculateValidAntiNode(pointList.get(i), pointList.get(j), maxX, maxY);
                    uniquePointsPart1.addAll(listOfAntiNodes);


                    /// part 2 calculation
                    List<Point> listOfAntiNodeWithHarmonics = calculateValidAntiNodeLocationWithHarmonics(pointList.get(i), pointList.get(j), maxX, maxY);
                    uniquePointsPart2.addAll(listOfAntiNodeWithHarmonics);

                }

            }
        }
        System.out.println("Part one --> " + uniquePointsPart1.size());
        System.out.println("Part two --> " + uniquePointsPart2.size());
    }


    static List<Point> calculateValidAntiNode(Point p1, Point p2, int maxX, int maxY) {
        List<Point> points = new ArrayList<>();

        int difX = p2.x() - p1.x();
        int difY = p2.y() - p1.y();

        ///by subtracting the diff from p1 we move twice far from p2
        Point antiNode1 = new Point(p1.x() - difX, p1.y() - difY);

        /// by adding the diff we move twice far from p1
        Point antiNode2 = new Point(p2.x() + difX, p2.y() + difY);

        //anti nodes are within grid
        if (validLocation(antiNode1, maxX, maxY)) {
            points.add(antiNode1);
        }

        if (validLocation(antiNode2, maxX, maxY)) {
            points.add(antiNode2);
        }

        return points;
    }


    static List<Point> calculateValidAntiNodeLocationWithHarmonics(Point p1, Point p2, int maxX, int maxY) {
        int difX = p2.x() - p1.x();
        int difY = p2.y() - p1.y();
        List<Point> points = new ArrayList<>();
        Point antiNode1 = p1;
        Point antiNode2 = p2;

        /// location is valid add the point
        while (validLocation(antiNode1, maxX, maxY) || validLocation(antiNode2, maxX, maxY)) {

            if (validLocation(antiNode1, maxX, maxY)) {
                points.add(antiNode1);
            }
            if (validLocation(antiNode2, maxX, maxY)) {
                points.add(antiNode2);
            }

            // now update the coordinates
            // by moving away from the last point using the diff
            antiNode1 = new Point(antiNode1.x() - difX, antiNode1.y() - difY);
            antiNode2 = new Point(antiNode2.x() + difX, antiNode2.y() + difY);
        }

        return points;
    }

    /**
     * Validates that the point is within limit
     *
     * @param p1   Point which needs to be validated
     * @param maxX Upperbound of X
     * @param maxY Upperbound of Y
     * @return {@code true } If the point is within bounds
     */
    static boolean validLocation(Point p1, int maxX, int maxY) {

        return p1.x() >= 0 && p1.x() < maxX && p1.y() >= 0 && p1.y() < maxY;
    }

}
