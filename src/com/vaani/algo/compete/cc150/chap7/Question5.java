package com.vaani.algo.compete.cc150.chap7;


/**
 * Given two squares on a two-dimensional plane, find a line that would cut
 * these two squares in half. Assume that the top and the bottom sides of the
 * square run parallel to the x-axis.
 */
public class Question5 {
    /**
     * @param x1 The x coordinate of the upper left corner of the first square.
     * @param y1 The y coordinate of the upper left corner of the first square.
     * @param x2 The x coordinate of the lower right corner of the first square.
     * @param y2 The y coordinate of the lower right corner of the first square.
     * @param x3 The x coordinate of the upper left corner of the second square.
     * @param y3 The y coordinate of the upper left corner of the second square.
     * @param x4 The x coordinate of the lower right corner of the second square.
     * @param y4 The y coordinate of the lower right corner of the second square.
     * @return An array list containing two integers, the slope and the intercept of the line.
     * if line is vertical (x = c) and horizontal (y = c), return the constant only,
     * if two square coincides, return the x, y of the points.
     */
    public double[] cutInHalf(double x1, double y1, double x2, double y2, double x3,
                              double y3, double x4, double y4) {
        double[] arr;
        double m1x = (x1 + x2) / 2;
        double m1y = (y1 + y2) / 2;
        double m2x = (x3 + x4) / 2;
        double m2y = (y3 + y4) / 2;

        if (m1x == m2x && m1y == m2y) {  // any line cross the middle point
            arr = new double[2];
            arr[0] = m1x;
            arr[1] = m1y;
        } else if (m1y == m2y) {  // horizontal line, y = c
            arr = new double[1];
            arr[0] = m1y;
        } else if (m1x == m2x) {  // vertical line, x = c
            arr = new double[1];
            arr[0] = m1x;
        } else {
            arr = new double[2];
            arr[0] = (m1y - m2y) / (m1x - m2x);
            arr[1] = m1y - arr[0] * m1x;
        }
        return arr;
    }

}


