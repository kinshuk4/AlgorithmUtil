package com.vaani.algo.compete.hackerrank;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**

 * Ref: Staircase exercise.
 */
public class Staircase {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int height = in.nextInt();

        // Creates the formatting string
        StringBuilder buffer = new StringBuilder();
        buffer.append("%").append(height).append("s%n");

        // Prints the staircase
        for(int index = 0; index < height; index++) {
            String currentStep = IntStream.range(0, index + 1).mapToObj(x -> "#").collect(Collectors.joining());
            System.out.printf(buffer.toString(), currentStep);
        }
    }
}
