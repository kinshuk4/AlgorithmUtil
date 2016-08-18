package com.vaani.algo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
 * <p>
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 */
public class MissingRanges {
    /**
     * Cleaner solution
     */
    public static List<String> findMissingRanges2(int[] A, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        int len = A.length;

        int prev = lower - 1;
        for (int i = 0; i <= len; i++) {
            int curr = (i == len) ? upper + 1 : A[i];
            if (curr - prev >= 2) {
                result.add(getRange(prev + 1, curr - 1));
            }
            prev = curr;
        }
        return result;
    }

    public static String getRange(int from, int to) {
        return from == to ? String.valueOf(from) : from + "->" + to;
    }

    /**
     * My solution
     */
    public static List<String> findMissingRanges(int[] A, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        int len = A.length;
        if (len == 0) {
            StringBuilder single = new StringBuilder();
            if (lower == upper) {
                single.append(lower);
            } else {
                single.append(lower);
                single.append("->");
                single.append(upper);
            }
            result.add(single.toString());
            return result;
        }

        for (int i = 0; i < len; i++) {
            StringBuilder single = new StringBuilder();
            if (i == 0) {
                if (A[i] > lower) {
                    if (A[i] - lower == 1) {
                        single.append(lower);
                    } else {
                        single.append(lower);
                        single.append("->");
                        single.append(A[i] - 1);
                    }
                } else {
                    continue;
                }
            } else {
                int start = A[i - 1] + 1;
                int end = A[i] - 1;
                if (start < end) {
                    single.append(start);
                    single.append("->");
                    single.append(end);
                } else if (start == end) {
                    single.append(start);
                } else {
                    continue;
                }
            }
            result.add(single.toString());
        }
        if (A[len - 1] < upper) {
            StringBuilder single = new StringBuilder();
            if (upper - A[len - 1] == 1) {
                single.append(upper);
            } else {
                single.append(A[len - 1] + 1);
                single.append("->");
                single.append(upper);
            }
            result.add(single.toString());
        }
        return result;
    }

    public static void main(String[] args) {
        int[] num = {0, 1, 3, 50, 75};
        System.out.println(findMissingRanges(num, 0, 99));

    }
}
