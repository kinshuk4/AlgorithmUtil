package com.vaani.algo.compete.cc150.chap1arrstring;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is
 * 4 bytes, write a method to rotate the image by 90 degrees (clockwise). Can
 * you do this in place?
 */
// O(1) space O(n^2) time
public class Question6 {

    public void rotate(int[][] mat) {

        if (mat == null) {
            return;
        }

        for (int layer = 0; layer < mat.length / 2; ++layer) {
            int start = layer;
            int end = mat.length - layer - 1;
            for (; start < end; ++start) {
                // store top
                int tmp = mat[layer][start];
                // replace top with left
                mat[layer][start] = mat[mat.length - start - 1][layer];
                // replace left with bottom
                mat[mat.length - start - 1][layer] = mat[mat.length - layer - 1][mat.length - start - 1];
                // replace bottom with right
                mat[mat.length - layer - 1][mat.length - start - 1] = mat[start][mat.length - layer - 1];
                // replace right with top
                mat[start][mat.length - layer - 1] = tmp;
            }
        }
    }

}

