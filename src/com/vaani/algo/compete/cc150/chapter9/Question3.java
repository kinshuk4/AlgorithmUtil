package com.vaani.algo.compete.cc150.chapter9;

/**
 * A magic index in an array A[0...n-1] is defined to be an index such that A[i]
 * = i. Given a sorted array, write a method to find a magic index, if one
 * exists, in array A.
 * <p>
 * FOLLOW UP What if the values are not distinct?
 */
// O(1) space, O(lgn) time
public class Question3 {
    /**
     * Return the index of the array that is the magic index. If there are
     * multiple, return the first one. If there is none, return -1.
     *
     * @param array
     * @return
     */
    public int findMagicIndex(int[] array) {
        // write implement here
        return findMagicIndex(array, 0, array.length - 1);
    }

    private int findMagicIndex(int[] array, int low, int high) {
        if (low > high || low < 0 || high >= array.length) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (array[mid] == mid) {
            return mid;
        }
        int left = Math.min(mid - 1, array[mid]);
        int leftRes = findMagicIndex(array, low, left);
        if (leftRes != -1) {
            return leftRes;
        }
        int right = Math.max(mid + 1, array[mid]);
        return findMagicIndex(array, right, high);
    }

}
