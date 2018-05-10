/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 27/07/2016
 * 
 * Zalando Codility
 * Problem: Task 2
 * Status: failed
 * 
 * Note: I did not expect such kind of a problem on
 * Codility :) Therefore I used greedy algorithm to 
 * solve it. Once I saw that it fails on my tests I
 * understood that it is not so simple :) I did not
 * had too much time left so I submitted and get only 40%. 
 * According to the task as input we will get only 30 days
 * so may be there is greedy solution...
 * Anyway eventually I solved it here using dynamic 
 * programming, for any number of days :)
 * Next time I should be carefull with Codility tasks :)
 * 
 * Problem
 * 
 * Given array of days which you need to travel. Find
 * min number of money to spend knowing that ticket
 * prices are:
 * - monthly 25
 * - weekly 7
 * - daily 2
 * 
 * Sample Input
 * 
1, 2, 3, 5, 6, 7, 10
 * 
 * Sample Output
 * 
9
 * 
 */

// Asked in zalando

package com.vaani.algo.compete.codility.buyticket;

import java.util.Arrays;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static org.junit.Assert.assertEquals;

public class Task2_Buy_tickets {

    static final int PRICE_DAILY = 2;
    static final int PRICE_WEEKLY = 7;
    static final int PRICE_MONTHLY = 25;
    
    static final int MONTHLY_LEN = 30;
    static final int WEEKLY_LEN = 7;
    
    private static void update(int[] A, int[] d, int len, int price, int i) {
        int s = A[i] - len + 1;
        if (s > 0) {
            int p = Arrays.binarySearch(A, s);
            if (p < 0) p = abs(p) - 1;
            int cost = price + (p > 0? d[p - 1]: 0);
            d[i] = Math.min(d[i], cost);
        } else 
            d[i] = min(d[i], price);
    }
    
    public int solution(final int[] A) {
        final int[] d = new int[A.length];
        d[0] = PRICE_DAILY;
        for (int i = 1; i < d.length; i++) {
            d[i] = d[i - 1] + PRICE_DAILY;
            update(A, d, MONTHLY_LEN, PRICE_MONTHLY, i);
            update(A, d, WEEKLY_LEN, PRICE_WEEKLY, i);
        }
        return d[A.length - 1];
    }
    
    public static int solve(int[] A) {
        return new Task2_Buy_tickets().solution(A);
    }
    
    public static void main(String[] args) {
        assertEquals(6, solve(new int[]{1, 2, 3}));
        assertEquals(7, solve(new int[]{1, 2, 3, 4, 5}));
        assertEquals(7, solve(new int[]{1, 2, 3, 4, 5, 6, 7}));
        assertEquals(9, solve(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
        assertEquals(7, solve(new int[]{1, 2, 3, 7}));
        assertEquals(8, solve(new int[]{1, 2, 3, 8}));
        assertEquals(9, solve(new int[]{1, 2, 3, 5, 6, 7, 10}));
        assertEquals(11, solve(new int[]{1, 2, 7, 8, 9, 10}));
        assertEquals(23, solve(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22}));
        assertEquals(25, solve(new int[]{1, 2, 3, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27}));
    }
    
}
