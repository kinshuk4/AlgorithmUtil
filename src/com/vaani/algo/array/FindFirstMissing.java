package com.vaani.algo.array;

public class FindFirstMissing {
    public int missingPositive(int[] A) {
        int misMatchPos = -1;
        for (int i = 0; i < A.length; ++i) {
            if (A[i] < 0 || A[i] >= A.length) {
                misMatchPos = i;
            }
        }
        if (misMatchPos == -1) {
            return A.length;
        }

        int pos = 0;
        while (pos < A.length) {
            if (A[pos] >= 0 &&
                    A[pos] < A.length &&
                    A[pos] != pos &&
                    pos != misMatchPos) {

                if (A[pos] == misMatchPos) {
                    A[misMatchPos] = A[pos];
                    misMatchPos = pos;
                } else {
                    int idx = A[pos];
                    A[misMatchPos] = A[pos];
                    A[pos] = A[idx];
                    A[idx] = A[misMatchPos];
                }
            } else {
                ++pos;
            }
        }

        int i = 0;
        for (; i < A.length; ++i) {
            if (A[i] != i) {
                break;
            }
        }
        return i;
    }

}
