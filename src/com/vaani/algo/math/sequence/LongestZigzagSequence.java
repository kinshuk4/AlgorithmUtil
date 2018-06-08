package com.vaani.algo.math.sequence;

import java.util.Arrays;

/**
 * A sequence of integers is called a zigzag sequence if each of its elements is either strictly less than all its neighbors or strictly greater than all its neighbors. For example, the sequence 4 2 3 1 5 3 is a zigzag, but 7 3 5 5 2 and 3 8 6 4 5 aren't. Sequence of length 1 is also a zigzag.
 * <p>
 * For a given array of integers return the length of its longest contiguous sub-array that is a zigzag sequence.
 * <p>
 * Example
 * <p>
 * For a = [9, 8, 8, 5, 3, 5, 3, 2, 8, 6], the output should be
 * zigzag(a) = 4.
 * <p>
 * The longest zigzag sub-arrays are [5, 3, 5, 3] and [3, 2, 8, 6] and they both have length 4.
 * <p>
 * For a = [4, 4], the output should be
 * zigzag(a) = 1.
 * <p>
 * The longest zigzag sub-array is [4] - it has only one element, which is strictly greater than all its neighbors (there are none of them).
 */
public class LongestZigzagSequence {
    static int zigzag(int[] a) {
        boolean increase = (a[0] < a[1]);
        boolean equal = a[0] == a[1];
        int currMax = 1;
        int max = 1;

        for (int i = 1; i < a.length - 1; i++) {
            boolean currIncrease = a[i] < a[i + 1];
            boolean currDecrease = a[i] > a[i + 1];
            boolean currEqual = a[i] == a[i + 1];

            if (currEqual) {
                if (currMax > max) {
                    max = currMax;
                }
                currMax = 1;
                equal = true;
            } else if (equal && currDecrease) {
                currMax++;
                increase = !currDecrease;
                equal = false;
            } else if (equal && currIncrease) {
                currMax++;
                increase = currIncrease;
                equal = false;
            } else if (currIncrease && !increase) {
                currMax++;
                increase = currIncrease;
                equal = false;
            } else if (currDecrease && increase) {
                currMax++;
                increase = !currDecrease;
                equal = false;
            } else {
                if (currMax > max) {
                    max = currMax;
                }
                currMax = 2;
            }
        }

        return max;
    }
//    static int zigzag(int[] a) {
//        int[] b = new int[a.length];
//        boolean increase = a[0] < a[1];
//        int currMax = 1;
//        int max = 1;
//        boolean currReset = a[0]==a[1];
//        for (int i = 1; i < a.length - 1; i++) {
//            boolean currIncrease = a[i] < a[i + 1];
//            boolean currDecrease = a[i] > a[i + 1];
//            boolean currEqual = a[i] == a[i + 1];
//            if (currEqual) {
//                if (currMax > max) {
//                    max = currMax;
//                }
//                currMax = 1;
//                currReset = true;
//            } else if ((currReset && currDecrease) || (currIncrease && !increase)) {
//                currMax++;
//                increase = !currDecrease;
//                currReset = false;
//            } else if ((currReset && currIncrease) || (currDecrease && increase)) {
//                currMax++;
//                increase = currIncrease;
//                currReset = false;
//            } else {
//                if (currMax > max) {
//                    max = currMax;
//                }
//
//                currReset = true;
//                currMax = 1;
//            }
//        }
//
//        return max;
//    }


//    static boolean more(int[] a, int i){
//        return (a[i] > a[i-1]) && a[i] > a[i+1]
//    }


    public static void main(String[] args) {
        int[] arr = {9, 8, 8, 5, 3, 5, 3, 2, 8, 6};

        System.out.println(zigzag(arr));
    }
}
