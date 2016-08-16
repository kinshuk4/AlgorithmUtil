package com.vaani.algo.arrays.container;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container.
 *
 * Created by Xiaomeng on 9/2/2014.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0, right = height.length -1;
        int max = 0;
        while(left < right){
            int area = Math.min(height[left], height[right]) * (right - left);
            max = area > max ? area : max;
            if(height[left] < height[right])
                left++;
            else
                right--;
        }
        return max;
    }

    public static void main(String[] args){
        ContainerWithMostWater test = new ContainerWithMostWater();
        int[] height = {1,2};
        System.out.println(test.maxArea(height));
    }
}
