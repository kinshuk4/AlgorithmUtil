package com.vaani.algo.array.container;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * <p>
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 * <p>
 */
public class LargestRectangleHistogram {
    public static void main(String[] args) {
        LargestRectangleHistogram test = new LargestRectangleHistogram();
        int[] height = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(height));

        System.out.println(largestRectangleArea(new int[] {1,2,3,4,5}));
    }

    public static int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int len = height.length;
        for (int i = 0; i < height.length; ) {
            if (stack.isEmpty() ||  height[i] >= height[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                result = calculateArea(height, stack, result, i);
            }
        }
        while (!stack.isEmpty()) {
            result = calculateArea(height, stack, result, len);
        }
        return result;
    }

    private static int calculateArea(int[] height, Stack<Integer> stack, int result, int i) {
        int index = stack.pop();
        int h = height[index];
        int w = stack.isEmpty() ? i : i - stack.peek() - 1;
        int sum = h * w;
        result = Math.max(sum, result);
        return result;
    }



}
