package com.vaani.algo.compete.hackerrank;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Part of Launchcode Coding Challenge
 * Program executes in O(nlogn)
 * It sorts the array and arrange the array according to requirement - 
 * largest smallest 2ndLargest 2ndSmallest and so on
 * long story was written on Hackerrank could not remember but it passed all test cases in less than 0.2 seconds
 */
public class ZigZagArray {
	public static void main(String[] args) {

		// int[] a = { 5, 2, 7, 8, -2, 25};
		int[] a = { -27676, 211621, 904304, 161270, -292822, 732004, 860511, -825806, -721722, 536428, -927571,
				-287004 };

		zigzag(a);

	}

	static void zigzag(int[] intArr) {

		// create input arraylist
		ArrayList<Integer> inputArr = new ArrayList<Integer>();

		for (int i = 0; i < intArr.length; i++) {
			inputArr.add(intArr[i]);
		}

		// sort the array - uses merge sort - O(nlogn)
		Collections.sort(inputArr);

		int size = inputArr.size();
		boolean isEven = true;
		int loop_count = 0;

		// find the loop count
		if (size % 2 == 0) {
			loop_count = size / 2;
		} else {
			loop_count = (size / 2) + 1;
			isEven = false;
		}

		ArrayList<Integer> outputArr = new ArrayList<Integer>();

		// add according to zigzag pattern
		for (int i = 0; i < loop_count; i++) {

			if (isEven) {
				outputArr.add(inputArr.get((size - 1) - i));
				outputArr.add(inputArr.get(i));
			} else {

				// considering edge case for last element for odd one
				if (i == loop_count - 1) {
					outputArr.add(inputArr.get((size - 1) - i));
				} else {
					outputArr.add(inputArr.get((size - 1) - i));
					outputArr.add(inputArr.get(i));
				}
			}
		}

		System.out.println(outputArr);

	}
}
