package com.vaani.algo.misc;

/**
 * Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.
 *
 * Examples:
 *
 * Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
 * Ouptut: Sum found between indexes 2 and 4
 *
 * Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
 * Ouptut: Sum found between indexes 1 and 4
 *
 * Input: arr[] = {1, 4}, sum = 0
 * Output: No subarray found
 *
 * Reference:
 * http://www.geeksforgeeks.org/find-subarray-with-given-sum/
 * http://www.mitbbs.com/article_t1/JobHunting/32838067_0_1.html
 *
 */
public class FindSubarrayWithGivenSum {
    public static boolean subArraySum(int[] arr, int sum){
        int currSum = 0;
        for(int start = 0, end = 0; end < arr.length; end++){
            currSum += arr[end];
            if(currSum == sum){
                System.out.println("Sum found between indexes " + start + " and " + end);
                return true;
            }else if(currSum < sum){
                continue;
            }else{
                while(currSum > sum && start < end - 1){
                    currSum -= arr[start++];
                }
                if(currSum == sum){
                    System.out.println("Sum found between indexes " + start + " and " + end);
                    return true;
                }
            }
        }
        System.out.println("No subarray found");
        return false;
    }

    /**
     * Generic solution!
     *
     * : O(n) solution for unsorted array with negative numbers
     : An example
     : arr=[-1 4 1 0 -2 -3 7],
     : sum = 2
     : step 1: accumulate the sums
     : arr -> acc = [0 -1 3 4 4 2 -1 6]. Insert 0 at the front
     : step 2: traverse the acc with hash table
     : the hash table will store the following values
     : ht = [0+2, -1+2, 3+2, 4+2]
     : It returns when finding an element of acc in the hash table (2 in this
     case)

     In step 2, it will go through each element in acc, check if it is in the
     hash table. If not, insert the "element+target value" to the hash table, and
     go on. Otherwise, it finds the contiguous elements that sum up to the
     target value, returning true.
     */

    public static void main(String[] args){
        int[] arr1 = {1, 4, 20, 3, 10, 5};
        System.out.println(subArraySum(arr1, 33));
        int[] arr2 = {1, 4, 0, 0, 3, 10, 5};
        System.out.println(subArraySum(arr2, 7));
        int[] arr3 = {1, 4};
        System.out.println(subArraySum(arr3, 0));
    }
}
