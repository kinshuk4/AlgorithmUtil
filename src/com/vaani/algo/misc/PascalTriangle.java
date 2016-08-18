package com.vaani.algo.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * <p>
 * For example, given numRows = 5,
 * <p>
 * Return
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * <p>
 * Created by Xiaomeng on 8/26/2014.
 */
public class PascalTriangle {
    public static void main(String[] args) {
        PascalTriangle test = new PascalTriangle();
        List<List<Integer>> result = test.generate(10);
        for (List<Integer> single : result) {
            System.out.println(single);
        }
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> curr;
        List<Integer> prev = new ArrayList<Integer>();
        for (int i = 0; i < numRows; i++) {
            curr = new ArrayList<Integer>();
            if (i > 0) prev = result.get(i - 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    curr.add(1);
                else
                    curr.add(prev.get(j - 1) + prev.get(j));
            }
            result.add(curr);
        }
        return result;
    }
}
