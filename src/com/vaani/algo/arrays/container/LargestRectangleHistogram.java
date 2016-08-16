package com.vaani.algo.arrays.container;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 *
 * Created by Xiaomeng on 9/3/2014.
 */
public class LargestRectangleHistogram {
    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int len = height.length;
        for(int i = 0; i < height.length; i++){
            if(stack.isEmpty() || height[stack.peek()] < height[i]){
                stack.push(i);
            }else{
                int index = stack.pop();
                int sum = height[index] * (stack.isEmpty() ? i : i - stack.peek() - 1);
                result = Math.max(sum, result);
                i--;
            }
        }
        while(!stack.isEmpty()){
            int index = stack.pop();
            int sum = height[index] * (stack.isEmpty() ? len : len - stack.peek() - 1);
            result = Math.max(sum, result);
        }
        return result;
    }

    public static void main(String[] args){
        LargestRectangleHistogram test = new LargestRectangleHistogram();
        int[] height = {2,1,5,6,2,3};
        System.out.println(test.largestRectangleArea(height));
    }
}
