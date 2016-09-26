package com.vaani.algo.compete.codility.mindist;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinDistanceFinderTest {
    final MinDistanceFinder finder = new MinDistanceFinder();

    @Test
    public void testMinDistanceQSort() throws Exception {
        assertEquals(1, finder.quickSortAndFindMinDist(new int[]{1, 2, 3, 4, 5, 6}));
        assertEquals(11, finder.countIterations);

        assertEquals(0, finder.quickSortAndFindMinDist(new int[]{0, 2, 4, 4, 10, 13, 88, 20, 12, 44, 77, 99, 0, 43, 22, 55, 33, 22}));
        assertEquals(25, finder.countIterations);

        assertEquals(1, finder.quickSortAndFindMinDist(new int[]{1, 4, 7, 16, 15, 12, 11, 10}));
        assertEquals(13, finder.countIterations);

        assertEquals(5, finder.quickSortAndFindMinDist(new int[]{100, 50, 90, 70, 10, 5, 85, 30, 0, 65, 150}));
        assertEquals(17, finder.countIterations);

        assertEquals(3, finder.quickSortAndFindMinDist(new int[]{1, 4, 8, 17, 190, 60, 50, 30, 20}));
        assertEquals(15, finder.countIterations);
    }

    @Test
    public void testMinDistanceBubble() throws Exception {
        assertEquals(1, finder.minDistanceBubble(new int[]{1, 2, 3, 4, 5, 6}));
        assertEquals(15, finder.countIterations);

        assertEquals(0, finder.minDistanceBubble(new int[]{0, 2, 4, 4, 10, 13, 88, 20, 12, 44, 77, 99, 0, 43, 22, 55, 33, 22}));
        assertEquals(153, finder.countIterations);

        assertEquals(1, finder.minDistanceBubble(new int[]{1, 4, 7, 16, 15, 12, 11, 10}));
        assertEquals(28, finder.countIterations);

        assertEquals(5, finder.minDistanceBubble(new int[]{100, 50, 90, 70, 10, 5, 85, 30, 0, 65, 150}));
        assertEquals(55, finder.countIterations);

        assertEquals(3, finder.minDistanceBubble(new int[]{1, 4, 8, 17, 190, 60, 50, 30, 20}));
        assertEquals(36, finder.countIterations);
    }
}