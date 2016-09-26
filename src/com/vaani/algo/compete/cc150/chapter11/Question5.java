package com.vaani.algo.compete.cc150.chapter11;

/**
 * Given a sorted array of strings which is interspersed with empty strings,
 * write a method to find the location of a given string.
 * <p>
 * EXAMPLE Input: find "ball" in {"at", "", "", "", "ball", "", "", "car", "",
 * "", "dad", "", ""} Output: 4
 */
// O(1) space, O(n) time
public class Question5 {

    public int findString(String[] strs, String target) {
        // write implementation here
        return find(strs, target, 0, strs.length - 1);
    }

    private int find(String[] strs, String target, int left, int right) {
        if (left > right || left < 0 || right >= strs.length) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (strs[mid].equals(target)) {
            return mid;
        }
        if (strs[mid].isEmpty()) {
            int prev = mid - 1;
            int next = mid + 1;
            while (true) {
                if (prev < left && next > right) { // cannot find non-empty in range
                    return -1;
                } else if (prev >= left && !strs[prev].isEmpty()) {
                    mid = prev;
                    break;
                } else if (next <= right && !strs[next].isEmpty()) {
                    mid = next;
                    break;
                }
                --prev;
                ++next;
            }
        }

        if (strs[mid].equals(target)) {
            return mid;
        } else if (target.compareTo(strs[mid]) < 0) {
            return find(strs, target, left, mid - 1);
        } else {
            return find(strs, target, mid + 1, right);
        }
    }

}




