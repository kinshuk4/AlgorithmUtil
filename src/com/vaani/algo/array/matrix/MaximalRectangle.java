package com.vaani.algo.array.matrix;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 *
 * Created by Xiaomeng on 9/6/2014.
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = rows == 0 ? 0 : matrix[0].length;
        int[][] height = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(matrix[i][j] == '0'){
                    height[i][j] = 0;
                }else if(matrix[i][j] == '1'){
                    if(i == 0)
                        height[i][j] = 1;
                    else
                        height[i][j] = height[i - 1][j] + 1;
                }
            }
        }

        int max = 0;
        for(int i = 0; i < rows; i++){
            max = Math.max(max, largestRectangleArea(height[i]));
        }
        return max;
    }

    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for(int i = 0; i < height.length; i++){
            if(stack.isEmpty() || height[stack.peek()] < height[i]){
                stack.push(i);
            }else{
                int index = stack.pop();
                int area = height[index] * (stack.isEmpty() ? i : i - stack.peek() - 1);
                max = Math.max(area, max);
                i--;
            }
        }
        while(!stack.isEmpty()){
            int index = stack.pop();
            int area = height[index] * (stack.isEmpty() ? height.length : height.length - stack.peek() - 1);
            max = Math.max(area, max);
        }
        return max;
    }
}
