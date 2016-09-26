package com.vaani.algo.compete.hackerrank;

import java.util.Arrays;

/**
 * Created by andersonkmi on 8/1/2016.
 */
public class SumPairs {
    public void printMatchingPairs(int[] numbers, int sum) {
        Arrays.sort(numbers);

        int firstPos = 0;
        int lastPos = numbers.length - 1;

        while(firstPos < lastPos) {
            int value = numbers[firstPos] + numbers[lastPos];

            if(value == sum) {
                System.out.println(numbers[firstPos] + " + " + numbers[lastPos] + " = " + sum);
                firstPos++;
                lastPos--;
            } else {
                if(value < sum) {
                    firstPos++;
                } else {
                    lastPos--;
                }
            }
        }
    }

    public static void main(String[] args) {
        SumPairs sum = new SumPairs();
        int[] numbers = {-2, -1, 0, 3, 5, 7, 9, 13, 14};
        sum.printMatchingPairs(numbers, 12);
    }
}
