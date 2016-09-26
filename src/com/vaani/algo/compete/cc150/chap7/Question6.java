package com.vaani.algo.compete.cc150.chap7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a two-dimensional graph with points on it, find a line which passes the
 * most number of points.
 */
public class Question6 {

    /**
     * @param points The points in the graph.
     * @return An array list containing two integers, the slope and the intercept of the line.
     * if line is vertical (x = c) and horizontal (y = c), return the constant only,
     * if two square coincides, return the x, y of the points.
     */
    public Line findLine(List<Point> points) {
        // write implementation here
        Map<Line, Integer> map = new HashMap<Line, Integer>();
        int bestCount = 0;
        Line bestLine = null;
        for (int i = 0; i < points.size(); ++i) {
            for (int j = 0; j < points.size(); ++j) {
                if (i == j) {
                    continue;
                }
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                Line line = new Line(p1, p2);
                Integer count = map.get(line);
                if (count == null) {
                    count = 0;
                    map.put(line, count);
                }
                map.put(line, count + 1);
                if (count > bestCount) {
                    bestCount = count;
                    bestLine = line;
                }
            }
        }

        return bestLine;
    }

    public static class Point {
        public double x;
        public double y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Line {
        private static final double epsilon = 0.000001;
        private double slope;
        private double intercept;
        private boolean infiniteSlope = false;

        public Line(Point p1, Point p2) {
            if (Math.abs(p1.x - p2.x) < epsilon) {
                this.infiniteSlope = true;
            } else {
                this.slope = (p2.y - p1.y) / (p2.x - p1.x);
                this.intercept = p1.y - this.slope * p1.x;
            }
        }

        public boolean equals(Object other) {
            if (other instanceof Line) {
                Line otherLine = (Line) other;
                if (this.infiniteSlope != otherLine.infiniteSlope) {
                    return false;
                }
                if (this.infiniteSlope == true) { // both has infinite slope
                    return true;
                } else {
                    return this.slope == otherLine.slope && this.intercept == otherLine.intercept;
                }
            }

            return false;
        }

        public int hashCode() {
            return (int) (intercept * 17);
        }
    }

}

