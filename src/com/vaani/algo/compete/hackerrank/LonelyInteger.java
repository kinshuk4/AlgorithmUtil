package com.vaani.algo.compete.hackerrank;
/**
launch code assignment
*/
public class LonelyInteger {
	//array containing all numbers occuring twice except 1 which occures only ones
static int lonelyInteger(int[] arr) {
       
		int m = 0;
		for (int j:arr)
			m ^= j;
		return m;
}
}
