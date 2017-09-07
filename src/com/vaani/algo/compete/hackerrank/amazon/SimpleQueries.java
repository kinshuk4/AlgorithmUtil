package com.vaani.algo.compete.hackerrank.amazon;

import java.util.Arrays;
import java.util.Scanner;

/*Amy has an array, nums, of n positive integers and another array, maxes, of m positive integers. For each maxesi in maxes, she wants to know the total number of elements in nums which are less than or equal to that maxesi. For example, if nums = [1, 2, 3] and maxes = [2, 4], then there are 2 elements in nums that are ¡Ü maxes0 (which is 2) and 3 elements in nums that are ¡Ü maxes1 (which is 4). We can store these respective answers in another array, answer = [2, 3].
Complete the counts function in the editor below. It has two parameters:
An array, nums, of n positive integers.
An array, maxes, of m positive integers.
The function must return an array of m positive integers in which the integer at each index i (where 0 ¡Ü i < m) denotes the total number of elements numsj (where 0 ¡Ü j < n) satisfying numsj ¡Ü maxesi.

Input Format
Locked stub code in the editor reads the following input from stdin and passes it to the function:
The first line contains an integer, n, denoting the number of elements in nums.
Each line j of the n subsequent lines (where 0 ¡Ü j < n) contains an integer describing numsj.
The next line contains an integer, m, denoting the number of elements in maxes.
Each line i of the m subsequent lines (where 0 ¡Ü i < m) contains an integer describing maxesi.

Constraints
2 ¡Ü n, m ¡Ü 105
1 ¡Ü numsj ¡Ü 109, where 0 ¡Ü j < n.
1 ¡Ü maxesi ¡Ü 109, where 0 ¡Ü i < m.

Output Format
The function must return an array of m integers where the value stored at each index i (where 0 ¡Ü i < m) denotes the total number of elements numsj (where 0 ¡Ü j < n) satisfying numsj ¡Ü maxesi. This is printed to stdout by locked stub code in the editor.

Sample Input 0
4
1
4
2
4
2
3
5

Sample Output 0
2
4

Explanation 0
We are given n = 4, nums = [1, 4, 2, 4], m = 2, and maxes = [3, 5].
For maxes0 = 3, we have two elements in nums (nums0 = 1 and nums2 = 2) that are ¡Ü maxes0.
For maxes1 = 5, we have four elements in nums (nums0 = 1, nums1 = 4, nums2 = 2, and nums3 = 4) that are ¡Ü maxes1.
Thus, the function returns the array [2, 4] as the answer.

Sample Input 1
5
2
10
5
4
8
4
3
1
7
8

Sample Output 1
1
0
3
4

Explanation 1
We are given, n = 5, nums = [2, 10, 5, 4, 8], m = 4, and maxes = [3, 1, 7, 8].
For maxes0 = 3, we have one element in nums (nums0 = 2) that is ¡Ü maxes0.
For maxes1 = 1, there are zero elements in nums that are ¡Ü maxes1.
For maxes2 = 7, we have three elements in nums (nums0 = 2, nums2 = 5, and nums3 = 4) that are ¡Ü maxes2.
For maxes3 = 8, we have four elements in nums (nums0 = 2, nums2 = 5, nums3 = 4, and nums4 = 8) that are ¡Ü maxes3.
Thus, the function returns the array [1, 0, 3, 4] as the answer.*/
public class SimpleQueries {
    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 4};
        int[] maxes = {3, 4};
        int[] ans = counts(nums, maxes);
        for (int i : ans)
            System.out.println(i);
    }

    public static int[] counts(int[] nums, int[] maxes) {
        int len = maxes.length;
        int[] ans = new int[len];
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            ans[i] = binarySearch(nums, maxes[i], 0, nums.length - 1) + 1;
        }
        return ans;
    }

    static int binarySearch(int[] nums, int numToSearch, int start, int end)

    {
        if (start > end) {
            return -1;
        }
        //avoids overlow
        int mid = start + (end - start) / 2;

        if (nums[mid] == numToSearch || (nums[mid] < numToSearch && (mid + 1 >= nums.length || nums[mid + 1] > numToSearch))) {
            return mid;
        } else if (nums[mid] < numToSearch && nums[mid + 1] < numToSearch) {
            return binarySearch(nums, numToSearch, mid + 1, end);
        } else if (mid - 1 >= 0 && nums[mid] > numToSearch) {
            return binarySearch(nums, numToSearch, start, mid - 1);
        } else if (nums[mid + 1] == numToSearch) {
            return mid + 1;
        }

        return -1;
    }
}
