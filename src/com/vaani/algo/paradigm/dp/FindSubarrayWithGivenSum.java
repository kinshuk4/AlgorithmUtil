package com.vaani.algo.paradigm.dp;

/**
 * Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.
 * <p>
 * Examples:
 * <p>
 * Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
 * Ouptut: Sum found between indexes 2 and 4
 * <p>
 * Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
 * Ouptut: Sum found between indexes 1 and 4
 * <p>
 * Input: arr[] = {1, 4}, sum = 0
 * Output: No subarray found
 * <p>
 * Reference:
 * http://www.geeksforgeeks.org/find-subarray-with-given-sum/
 * http://www.mitbbs.com/article_t1/JobHunting/32838067_0_1.html
 */
public class FindSubarrayWithGivenSum {
    public static boolean hasSubArraySum(int[] arr, int sum) {
        int currSum = 0;
        for (int start = 0, end = 0; end < arr.length; end++) {
            currSum += arr[end];
            if (currSum == sum) {
                System.out.println("Sum found between indexes " + start + " and " + end);
                return true;
            } else if (currSum < sum) {
                continue;
            } else {
                while (currSum > sum && start < end - 1) {
                    currSum -= arr[start++];
                }
                if (currSum == sum) {
                    System.out.println("Sum found between indexes " + start + " and " + end);
                    return true;
                }
            }
        }
        System.out.println("No subarray found");
        return false;
    }

    /**
     * returns array as pair - start and end of subarray with given sum
     *
     * @param arr
     * @param s
     * @return
     */
    public static int[] findSubArraySum(int[] arr, int s) {
        int currSum = arr[0];
        int startIdx = 0;

        int n = arr.length;

        // Pick a starting point
        for (int i = 1; i <= n; i++) {
            // If curr_sum exceeds the sum, then remove the starting elements
            while (currSum > s && startIdx < i - 1) {
                currSum = currSum - arr[startIdx];
                startIdx++;
            }

            // If curr_sum becomes equal to sum, then return true
            if (currSum == s) {
                int p = i - 1;
                return new int[]{startIdx, p};
            }

            // Add this element to curr_sum
            if (i < n) {
                currSum = currSum + arr[i];
            }


        }

        //No subarray found"
        return new int[]{-1, -1};
    }


    /**
     * Generic getTreeHeight!
     * <p>
     * : O(n) getTreeHeight for unsorted array with negative numbers
     * : An example
     * : arr=[-1 4 1 0 -2 -3 7],
     * : sum = 2
     * : step 1: accumulate the sums
     * : arr -> acc = [0 -1 3 4 4 2 -1 6]. Insert 0 at the front
     * : step 2: traverse the acc with hash table
     * : the hash table will store the following values
     * : ht = [0+2, -1+2, 3+2, 4+2]
     * : It returns when finding an element of acc in the hash table (2 in this
     * case)
     * <p>
     * In step 2, it will go through each element in acc, check if it is in the
     * hash table. If not, insert the "element+target value" to the hash table, and
     * go on. Otherwise, it finds the contiguous elements that sum up to the
     * target value, returning true.
     */

    public static void main(String[] args) {
        int[] arr1 = {1, 4, 20, 3, 10, 5};
        System.out.println(hasSubArraySum(arr1, 33));
        int[] arr2 = {1, 4, 0, 0, 3, 10, 5};
        System.out.println(hasSubArraySum(arr2, 7));
        int[] arr3 = {1, 4};
        System.out.println(hasSubArraySum(arr3, 0));
    }
}
