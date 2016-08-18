package com.vaani.algo.bits;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiaomeng on 10/22/2014.
 */
public class HammingDistance {
    /**
     * Write a function to calculate the hamming distance between two binary numbers
     */
    public static int getHammingDistance(String a, String b) {
        if (a.length() != b.length()) return -1;
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
        }
        return count;
    }

    /**
     * Write a function that takes a list of binary numbers and returns the sum of the hamming distances for each pair
     * The solution will take O(n)
     */
    public static int getAllHammingDisntances(List<String> binarys) {
        int size = binarys.size();
        if (size == 0) return 0;
        int len = binarys.get(0).length();

        int[] bitVector = new int[len];
        for (String binary : binarys) {
            for (int i = 0; i < binary.length(); i++) {
                if (binary.charAt(i) == '1')
                    bitVector[i] += 1;
            }
        }

        int total = 0;
        for (int i = 0; i < bitVector.length; i++) {
            total += bitVector[i] * (size - bitVector[i]);
        }
        return total;
    }

    public static void main(String[] args) {
        String x = "1001";
        String y = "1011";
        String z = "0010";
        List<String> binarys = new ArrayList<String>();
        binarys.add(x);
        binarys.add(y);
        binarys.add(z);
        System.out.println(getAllHammingDisntances(binarys));
    }
}
