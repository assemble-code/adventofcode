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


        /// calculate part one
        part1(charPoint, data.size());
    }


    static void part1(HashMap<Character, List<Point>> charPoint, int size) {
        int maxX = size ;
        int maxY = size ;

        int count = 0;
        HashSet<Point> uniquePoints = new HashSet<>();
        for (Map.Entry<Character, List<Point>> points : charPoint.entrySet()) {
            //if single antenna no need to calculate
            if (points.getValue().size() == 1) {
                continue;
            }
            List<Point> pointList = points.getValue();
            for (int i = 0; i < pointList.size()-1; i++) {
                for (int j = i + 1; j < pointList.size(); j++) {

                    List<Point> listOfAntiNodes = calculateValidAntiNode(pointList.get(i), pointList.get(j), maxX, maxY);

                    uniquePoints.addAll(listOfAntiNodes);

                }

            }
        }
        System.out.println("Part one --> "+uniquePoints.size());
    }


    static List<Point> calculateValidAntiNode(Point p1, Point p2, int maxX, int maxY) {
        List<Point> points = new ArrayList<>();

        int difX = p2.x() - p1.x();
        int difY = p2.y() - p1.y();

        Point antiNode1 = new Point(p1.x() - difX, p1.y() - difY);
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


    static boolean validLocation(Point p1, int maxX, int maxY) {

        return p1.x() >= 0 && p1.x() < maxX && p1.y() >= 0 && p1.y() < maxY;
    }

   static int getDistance(Point a, Point b) {

        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
