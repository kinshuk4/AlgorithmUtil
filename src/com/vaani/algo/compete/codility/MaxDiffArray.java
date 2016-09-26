package com.vaani.algo.compete.codility;

public class MaxDiffArray {

    public int solution(final int[] A) {
        int max = 0;

        int sumLeft = 0;
        int sumRight = sum(A);
        int maxDiff = Integer.MIN_VALUE;
        int maxIndex = 0;

        // Big O(n)
        for (int i = 0; i < A.length; i++) {
            int diff = sumLeft - sumRight;
            if (diff > maxDiff) {
                maxDiff = diff;
                maxIndex = i;
            }
            sumLeft +=  A[i];
            sumRight -= A[i];
        }
        
        max  = subArrayMax(A, maxIndex) - subArrayMin(A, maxIndex);

        return max;
    }

    private int subArrayMax(final int[] A, final int to) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < to; i++) {
            if (max < A[i]) {
                max = A[i];
            }
        }
        return max;
    }
    
    private int subArrayMin(final int[] A, final int from) {
        int min = Integer.MAX_VALUE;
        final int till = A.length;
        for (int i = from; i < till; i++) {
            if ( A[i] < min) {
                min = A[i];
            }
        }
        return min;
    }

    // Big O(n)
    private int sum(final int[] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }

        return sum;
    }
}
