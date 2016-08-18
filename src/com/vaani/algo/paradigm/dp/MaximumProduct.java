package com.vaani.algo.paradigm.dp;
/*https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fcommons%2FMaximumProduct.java*/

import org.junit.Test;

public class MaximumProduct {


    public static long getMaximumProduct(long[] arrs) {
        long max = 0;
        //以i作为开始，以j作为结束
        for (int i = 0; i < arrs.length - 1; i++) {
            for (int j = i + 1; j < arrs.length; j++) {
                long temp = 1;
                for (int k = i; k <= j; k++) {
                    temp *= arrs[k];
                }
                if (temp > max) {
                    max = temp;
                }

            }
        }
        return max;
    }

    @Test
    public void test() {
        long[] arr = new long[]{2, 4, -3};
        long maximumProduct = getMaximumProduct(arr);
        System.out.println(maximumProduct);
        arr = new long[]{2, 5, -1, 2, -1};
        maximumProduct = getMaximumProduct(arr);
        System.out.println(maximumProduct);
    }
}
