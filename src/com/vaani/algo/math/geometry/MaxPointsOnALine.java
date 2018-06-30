package com.vaani.algo.math.geometry;

import com.vaani.algo.ds.core.visual.Point;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * <p>
 */
public class MaxPointsOnALine {
    int max = 0;

    public static void main(String[] args) {
        Point[] points = {
                new Point(1, 1),
                new Point(1, 1),
                new Point(1, 1),
        };
        MaxPointsOnALine test = new MaxPointsOnALine();
        System.out.println(test.maxPoints(points));
    }

    public int maxPoints(Point[] points) {
        if (points.length <= 2) return points.length;

        Map<Double, Integer> map = new HashMap<Double, Integer>();
        int vertical = 1;
        int horizontal = 1;
        int dup = 0;

        for (int i = 0; i < points.length; i++) {
            Point base = points[i];
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                Point point = points[j];
                if (base.x == point.x && base.y == point.y) {
                    dup++;
                } else if (base.x == point.x) {
                    vertical++;
                } else if (base.y == point.y) {
                    horizontal++;
                } else {
                    double slope = 10000 * (base.y - point.y) / (base.x - point.x);
                    if (!map.containsKey(slope)) {
                        map.put(slope, 1);
                    }
                    map.put(slope, map.get(slope) + 1);
                }
            }
            max = findMax(map, vertical, horizontal, dup);
            vertical = 1;
            horizontal = 1;
            dup = 0;
            map.clear();
        }
        return max;
    }

    private int findMax(Map<Double, Integer> map, int vertical, int horizonal, int dup) {
        if (map.isEmpty()) {
            max = Math.max(Math.max(vertical, horizonal), dup + 1);
            return max;
        }

        for (int num : map.values()) {
            max = Math.max(max, num + dup);
        }
        max = Math.max(Math.max(vertical, horizonal), max);
        return max;
    }
}
