package com.adventofcode.year_2024.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.adventofcode.year_2024.day7.Operations.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //read the input
        BufferedReader bf = new BufferedReader(new FileReader("/home/prabhat/IdeaProjects/AdventOfCode/src/main/java/com/adventofcode/year_2024/day7/input.txt"));
        String line;
        List<Node> data = new ArrayList<>();
        while ((line = bf.readLine()) != null) {
            String[] tokens = line.split(":");
            Node n = new Node();
            n.total = Long.parseLong(tokens[0]);

            String[] numbers = tokens[1].trim().split(" ");

            for (String number : numbers) {
                n.numbers.add(Long.parseLong(number));
            }

            data.add(n);

        }


        processData(data);

    }

    private static void processData(List<Node> data) {


        long sum = 0;

        //check each node can be created
        for (Node node : data) {
            long total = node.total;

            if (canBeObtained(node.numbers, total)) {
                sum += total;
            }


        }

        System.out.println("Answer --> " + sum);
    }

    private static boolean canBeObtained(List<Long> numbers, long total) {


        // check with each operation as can total be reached

        return checkTotalValueCanBeReached(numbers, 0, ADDITION, 0, total)
                || checkTotalValueCanBeReached(numbers, 0, MULTIPLICATION, 1, total)
                /*** comment this section for just part 1 **/
                || checkTotalValueCanBeReached(numbers, 0, CONCATENATION, 0, total);

    }


    //it will generate recursively
    private static boolean checkTotalValueCanBeReached(List<Long> numbers, int numberIndex, Operations operation, long currentValue, long total) {

        if (numberIndex > numbers.size() - 1) {

            //  System.out.println("Current value --> " + currentValue);
            return currentValue == total;
        }

        switch (operation) {
            //do addition
            case ADDITION -> currentValue += numbers.get(numberIndex);

            //do multiplication
            case MULTIPLICATION -> currentValue *= numbers.get(numberIndex);

            // join digit, condition for part 2
            case CONCATENATION -> currentValue = Long.parseLong(currentValue + "" + numbers.get(numberIndex));

        }

        boolean wittPlus = checkTotalValueCanBeReached(numbers, numberIndex + 1, ADDITION, currentValue, total);


        boolean wittMul = checkTotalValueCanBeReached(numbers, numberIndex + 1, MULTIPLICATION, currentValue, total);

        // part 2 code for "||" as concatenation operation
        //comment this section for getting the answer for part 1
        boolean wittConcat = checkTotalValueCanBeReached(numbers, numberIndex + 1, CONCATENATION, currentValue, total);


        return wittPlus || wittMul || wittConcat;
    }


}

class Node {
    public long total;
    public List<Long> numbers = new ArrayList<>();
}


