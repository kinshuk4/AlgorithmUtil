package com.vaani.algo.compete.codility;

import java.util.Arrays;
import java.util.Comparator;

public class NumberOfDiscIntersections {
	static final int LIMIT = 10000000;

	public int solution(int[] A) {
		Point1[] points = new Point1[A.length * 2];
		for (int i = 0; i < A.length; i++) {
			points[i * 2] = new Point1((long) i - A[i], Type.LOWER);
			points[i * 2 + 1] = new Point1((long) i + A[i], Type.UPPER);
		}

		Arrays.sort(points, new PointComparator());

		int intersectNum = 0;
		int openedNum = 0;
		for (Point1 point : points) {
			if (point.type.equals(Type.LOWER)) {
				intersectNum += openedNum;
				if (intersectNum > LIMIT) {
					return -1;
				}
				openedNum++;
			} else {
				openedNum--;
			}
		}
		return intersectNum;
	}
}

class PointComparator implements Comparator<Point1> {
	@Override
	public int compare(Point1 p1, Point1 p2) {
		if (p1.y != p2.y) {
			return (int) Math.signum(p1.y - p2.y);
		}
		return p1.type.equals(Type.LOWER) ? -1 : 1;
	}
}

class Point1 {
	long y;
	Type type;

	Point1(long y, Type type) {
		this.y = y;
		this.type = type;
	}
}

enum Type {
	LOWER, UPPER
}