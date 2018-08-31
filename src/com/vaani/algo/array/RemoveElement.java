package com.vaani.algo.array;
import static com.vaani.algo.ds.utils.ArrayUtils.swap;

/**
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * <p>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * <p>
 *
 */
public class RemoveElement {
    public static int removeElement2(int[] A, int elem) {
        int index = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != elem) {
                A[index] = A[i];
                index++;
            }
        }
        return index;
    }

    public static int removeElement(int[] A, int elem) {
        if (A.length == 0) return 0;
        int len = -1;
        for (int i = 0; i < A.length; i++) {
            int index = i;
            while (index < A.length && A[index] == elem) index++;
            if (index == A.length) return len + 1;
            if (index != i) swap(A, i, index);
            len = i;
        }
        return len == -1 ? 0 : len + 1;
    }


    public static void main(String[] args) {
        int[] A = {2};
        int len = removeElement(A, 2);
        for (int i = 0; i < len; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
