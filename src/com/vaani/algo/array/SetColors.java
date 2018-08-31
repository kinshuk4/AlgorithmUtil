package com.vaani.algo.array;
import static com.vaani.algo.ds.utils.ArrayUtils.swap;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * <p>
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * <p>
 * Could you come up with an one-pass algorithm using only constant space?
 * <p>
 */
public class SetColors {
    public static void sortColors(int[] A) {
        int i = 0, j = A.length - 1;
        int curr = i;
        while (curr <= j) {
            if (A[curr] == 0) {
                if (curr > i) {
                    swap(A, curr, i++);
                } else {
                    curr++;
                    i++;
                }
            } else if (A[curr] == 2) {
                if (curr < j) {
                    swap(A, curr, j--);
                } else {
                    return;
                }
            } else if (A[curr] == 1) {
                curr++;
            }
        }
    }

}
