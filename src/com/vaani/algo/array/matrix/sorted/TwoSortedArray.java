package com.vaani.algo.array.matrix.sorted;

public class TwoSortedArray {
    //////////////////////     Kth Minimum element    \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    /**
     * http://leetcode.com/2011/01/find-k-th-smallest-element-in-union-of.html
     * O(lgm + lgn) Solution
     */

    static final int MIN = Integer.MIN_VALUE;
    static final int MAX = Integer.MAX_VALUE;

    public static int kthSmallest(int[] A, int[] B, int k) {
        if (A == null || B == null || k > A.length + B.length)
            throw new IllegalArgumentException();
        return kthSmallest(A, 0, A.length, B, 0, B.length, k);
    }

    private static int kthSmallest(int[] A, int aLow, int aLength, int[] B, int bLow, int bLength, int k) {

        assert (aLow >= 0);
        assert (bLow >= 0);
        assert (aLength >= 0);
        assert (bLength >= 0);
        assert (aLength + bLength >= k);

        int i = (int) ((double) ((k - 1) * aLength / (aLength + bLength)));
        int j = k - 1 - i;

        int Ai_1 = aLow + i == 0 ? MIN : A[aLow + i - 1];
        int Ai = aLow + i == A.length ? MAX : A[aLow + i];

        int Bj_1 = bLow + j == 0 ? MIN : B[bLow + j - 1];
        int Bj = bLow + j == B.length ? MAX : B[bLow + j];

        if (Bj_1 < Ai && Ai < Bj)
            return Ai;
        else if (Ai_1 < Bj && Bj < Ai)
            return Bj;

        assert (Ai < Bj - 1 || Bj < Ai_1);

        if (Ai < Bj_1) // exclude A[aLow .. i] and A[j..bHigh], k was replaced by k - i - 1
            return kthSmallest(A, aLow + i + 1, aLength - i - 1, B, bLow, j, k - i - 1);
        else // exclude A[i, aHigh] and B[bLow .. j], k was replaced by k - j - 1
            return kthSmallest(A, aLow, i, B, bLow + j + 1, bLength - j - 1, k - j - 1);
    }


    //approach 2- http://stackoverflow.com/a/8935157
//	public static int kthSmallest(int[] A, int[] B, int k){
//		if (A == null || B == null || k > A.length + B.length)
//			throw new IllegalArgumentException();
//		int k1 = A.length;
//		int k2 = B.length;
//		return kthSmallest(A, 0, k1, B, 0, k2, k);
//		
//
//	}
//	public static int kthSmallest(int[] arr1,int aLow,int aHigh,int[] arr2,int bLow,int bHigh, int k){
//	    int mida1 = aLow + (aHigh-aLow)/2;
//	    int mida2 = bLow + (bHigh-bLow)/2;
//
//	    	
//	    if ((mida1+mida2)<k){
//	        if (arr1[mida1]>arr2[mida2])
//	            return kthSmallest(arr1,aLow,mida1, arr2, mida2+1,bHigh, k-mida1-1);
//	        else
//	            return kthSmallest(arr1, mida1,aHigh, arr2,bLow,bHigh, k-mida2-1);
//	    }
//	    else{
//	        if (arr1[mida1]>arr2[mida2])
//	            return kthSmallest(arr1,aLow,mida1, arr2, bLow,bHigh,k);
//	        else
//	            return kthSmallest(arr1,aLow,aHigh, arr2, bLow,mida2, k);
//	    }
//	}


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6};
        int[] arr2 = {4};
        int k = 4;

        int ans = kthSmallest(arr, arr2, 4);
        System.out.println(ans);
    }

}
