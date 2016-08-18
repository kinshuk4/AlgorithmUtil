package com.vaani.algo.paradigm.dp;

public class MaxProductSubArray {

    // Returns the product of max product subarray. Assumes that the
    // given array always has a subarray with product more than 1
    public static int maxSubarrayProduct(int arr[]) {
        int n = arr.length;
        // max positive product ending at the current position
        int maxEndingHere = 1;

        // min negative product ending at the current position
        int minEndingHere = 1;

        // Initialize overall max product
        int maxSoFar = 1;

        // Traverse through the array.
        // Following values are maintained after the ith iteration:
        // maxEndingHere is always 1 or some positive product ending with arr[i]
        // minEndingHere is always 1 or some negative product ending with arr[i]
        for (int i = 0; i < n; i++) {
            // If this element is positive, update maxEndingHere. Update
            // minEndingHere only if minEndingHere is negative
            if (arr[i] > 0) {
                maxEndingHere = maxEndingHere * arr[i];
                minEndingHere = Math.min(minEndingHere * arr[i], 1);
            }

            // If this element is 0, then the maximum product cannot
            // end here, make both maxEndingHere and minEndingHere 1
            // Assumption: Output is always greater than or equal to 1.
            else if (arr[i] == 0) {
                maxEndingHere = 1;
                minEndingHere = 1;
            }

            // If element is negative. This is tricky
            // maxEndingHere can either be 1 or positive.
            // minEndingHere can either be 1
            // or negative.
            // next minEndingHere will always be prev. maxEndingHere * arr[i]
            // next maxEndingHere will be 1 if prev minEndingHere is 1,
            // otherwise
            // next maxEndingHere will be prev minEndingHere * arr[i]
            else {
                int temp = maxEndingHere;
                maxEndingHere = Math.max(minEndingHere * arr[i], 1);
                minEndingHere = temp * arr[i];
            }

            // update maxSoFar, if needed
            if (maxSoFar < maxEndingHere)
                maxSoFar = maxEndingHere;
        }

        return maxSoFar;
    }

    // Driver Program to test above function
    public static void main(String[] args) {
        int arr[] = {1, -2, -3, 0, 7, -8, -2};

        System.out.println("Maximum Sub array product is "
                + maxSubarrayProduct(arr));

    }
}
