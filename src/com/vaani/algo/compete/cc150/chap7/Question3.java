package com.vaani.algo.compete.cc150.chap7;

/**
 * Given two lines on a Cartesian plane, determine whether the two lines would
 * intersect.
 */
public class Question3 {

    /**
     * Check whether two lines would intersect
     *
     * @param x1 x coordinate of start point of line 1
     * @param y1 y coordinate of start point of line 1
     * @param x2 x coordinate of end point of line 1
     * @param y2 y coordinate of end point of line 1
     * @param x3 x coordinate of start point of line 2
     * @param y3 y coordinate of start point of line 2
     * @param x4 x coordinate of end point of line 2
     * @param y4 y coordinate of end point of line 2
     * @return
     */
    public boolean isIntersect(double x1, double y1, double x2, double y2,
                               double x3, double y3, double x4, double y4) {
        // write implementation here
        // identify with cross check
        // check whether (x3, y3) and (x4, y4) are on different side of line 1
        double sign1 = (x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3);
        double sign2 = (x1 - x2) * (y1 - y4) - (y1 - y2) * (x1 - x4);
        if (sign1 * sign2 >= 0) {
            return false;
        }

        // chechk whether (x1, y1) and (x2, y2) are on different side of line 2
        double sign3 = (x3 - x4) * (y3 - y1) - (y3 - y4) * (x3 - x1);
        double sign4 = (x3 - x4) * (y3 - y2) - (y3 - y4) * (x3 - x2);

        return sign3 * sign4 < 0;
    }
}
