package com.vaani.algo.compete.hackerrank.randomchallenges;

public class Pyramid {

    private int[] pyramidData;

    private int pyramidHeight;

    public static void main(String... args) {
        Pyramid pyramid = new Pyramid(new int[]{1, 10, 3});
        System.out.println(pyramid.getMaxPathValue());
    }

    public Pyramid(int[] pyramidData) {
        this.pyramidData = pyramidData;
        pyramidHeight = getPyramidHeight(pyramidData.length);
        if (pyramidHeight <= 0) {
            throw new IllegalArgumentException("invalid pyramid size");
        }
    }

    public int getMaxPathValue() {
        int dataIndex = pyramidData.length - 1 - pyramidHeight;
        for (int level = pyramidHeight - 1; level >= 1; level--) {
            for (int levelIndex = level; levelIndex > 0; levelIndex--, dataIndex--) {
                int bottomLeft = pyramidData[dataIndex + level];
                int bottomRight = pyramidData[dataIndex + level + 1];
                pyramidData[dataIndex] = pyramidData[dataIndex] + (bottomLeft > bottomRight ? bottomLeft : bottomRight);
            }
        }
        return pyramidData[0];
    }

    private int getPyramidHeight(int size) {
        double d = Math.sqrt(1 + 8 * size);
        if (d % 1 != 0) {
            return -1;
        }
        return ((int) d - 1) / 2;
    }


}
