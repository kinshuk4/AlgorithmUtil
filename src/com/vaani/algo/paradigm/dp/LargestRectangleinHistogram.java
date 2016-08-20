package com.vaani.algo.paradigm.dp;

import java.util.Stack;
/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
*/


public class LargestRectangleinHistogram {
    public int largestRectangleArea(int[] height) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int[] heights = new int[height.length + 1];
        for (int i = 0; i <= height.length; i++) {
            if (i == height.length) heights[i] = 0;
            else {
                heights[i] = height[i];
            }
        }
        for (int i = 0; i < heights.length; ) {
            if (stack.isEmpty() || heights[i] > heights[stack.peek()]) {
                stack.push(i++);
            } else {
                int index = stack.pop();
                result = Math.max(result, heights[index] * (stack.isEmpty() ? i : (i - stack.peek() - 1)));
            }
        }
        return result;
    }
}
