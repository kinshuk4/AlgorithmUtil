package com.vaani.algo.ds.algos.tree.binary;

import java.util.Arrays;

//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Ftrees%2FVerifySequenceOfBST.java
/*
 * * Title: Enter an integer array, the array is not a judgment after preorder result of a binary search tree, if it returns true, false otherwise
  * Assuming that the input of any two numbers of the array are different from each other
 */
public class VerifySequenceOfBST {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 6, 9, 11, 10, 8};
        boolean verify = verify(nums);
        System.out.println(verify);
        nums = new int[]{7, 4, 6, 5};
        verify = verify(nums);
        System.out.println(verify);
    }

    public static boolean verify(int[] sequences) {
        if (null == sequences || sequences.length <= 1) {
            return true;
        }
        int root = sequences[sequences.length - 1];
        int index = 0;
        while (index < sequences.length && sequences[index] < root) {
            index++;
        }
        for (int i = index; i < sequences.length; i++) {
            if (sequences[i] < root) {
                return false;
            }
        }
        boolean left = true;
        if (index > 0) {
            left = verify(Arrays.copyOf(sequences, index));
        }
        boolean right = true;
        if (index < sequences.length - 1) {
            right = verify(Arrays.copyOfRange(sequences, index, sequences.length - 1));
        }
        return left && right;
    }
}