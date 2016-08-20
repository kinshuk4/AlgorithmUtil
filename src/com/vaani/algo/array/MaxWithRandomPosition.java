package com.vaani.algo.array;

import java.util.Random;

class MaxWithRandomPosition {

    public int returnMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        int curIdx = -1;
        int maxCount = 0;
        Random rnd = new Random();

        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] > max) {
                max = arr[i];
                maxCount = 1;
                curIdx = i;
            } else if (arr[i] == max) {
                maxCount += 1;
                int ranInt = rnd.nextInt(maxCount);
                if (ranInt < 1) {
                    curIdx = i; // replace index with prob 1/n
                }
            }
        }

        return curIdx;
    }

}
