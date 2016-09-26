package com.vaani.algo.compete.codility.liftqueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SolutionTest {

    Solution solution;

    @Before
    public void setUp() {
        solution = new Solution();
    }

    @Test
    public void testSolutionPlain() {
        int[] A = {40, 40, 100, 80, 20};
        int[] B = {3, 3, 2, 2, 3};
        Assert.assertEquals(6, solution.solution(A, B, 3, 5, 200));
    }

    @Test
    public void testSolutionHeavyPax() {
        int[] A = {1000000000, 1000000000, 1000000000, 1000000000, 1000000000};
        int[] B = {1, 1, 1, 1, 1};
        Assert.assertEquals(10, solution.solution(A, B, 3, 10, 1000000000));
    }

}
