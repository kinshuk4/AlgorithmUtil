package com.vaani.algo.compete.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by andersonkmi on 7/23/2016.
 * Ref: Compare the Triplets (Hackerrank)
 */
public class CompareTriplets {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a0 = in.nextInt();
        int a1 = in.nextInt();
        int a2 = in.nextInt();
        int b0 = in.nextInt();
        int b1 = in.nextInt();
        int b2 = in.nextInt();
        // Organize the results
        List<Integer> aliceRates = new ArrayList<>();
        List<Integer> bobRates = new ArrayList<>();

        aliceRates.add(a0);
        aliceRates.add(a1);
        aliceRates.add(a2);

        bobRates.add(b0);
        bobRates.add(b1);
        bobRates.add(b2);

        // Creates the results vector
        Integer[] results = new Integer[2];
        results[0] = 0;
        results[1] = 0;

        // Compare the values for Alice and Bob
        compareRates(aliceRates, bobRates, results);

        // Print out the results
        System.out.println(String.format("%d %d", results[0], results[1]));
    }

    private static void compareRates(List<Integer> aliceRates, List<Integer> bobRates, Integer[] results) {
        for(int index = 0; index < aliceRates.size(); index++) {
            Integer aliceRate = aliceRates.get(index);
            Integer bobRate = bobRates.get(index);

            if(aliceRate > bobRate) {
                results[0] = results[0] + 1;
            } else if(aliceRate < bobRate) {
                results[1] = results[1] + 1;
            }
        }
    }
}
