/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 04/06/2016
 *
 * Hacker rank
 * Zalando CodeSprint
 * Problem: Make Our Customers Happy 
 * Status: accepted
 * 
 * Problem
 * 
 * As part of a new advertising campaign, Zalando is selling 
 * three limited-edition items that we'll call A, B, and C.
 * To make them available to as many customers as possible, 
 * there is a per-person purchase limit set so that each 
 * customer can only buy each of the three items once.
 * Zalando wants to make as many of our customers as happy as
 * possible. In order to do so, we want to find the maximum 
 * number of orders we can fulfill if our warehouses have NA units 
 * of item A, NB units of item B, and NC of item C in stock.
 * Given the stock levels for each item and a list of M customer 
 * orders, find the maximum number of customer orders we can fulfill. 
 * Each of the M orders is a subset of the items available in stock.
 * 
 * Input Format
 * 
 * The first line contains three space-separated integers describing 
 * the respective values for NA, NB, and NC.
 * The second line contains a single integer denoting the number of orders, M.
 * Each of the subsequent lines describes a customer order. Each order consists 
 * of comma-separated items, and each item appears at most once per order.
 * 
 * Output Format
 * 
 * Print a single integer denoting the maximum number of orders that 
 * Zalando can fulfill.
 * 
 * Input
 * 
2 1 1
3
A
A,B
C,A
 * 
 * Output
 * 
2
 * 
 */
package com.vaani.algo.compete.hackerrank.codesprint.zalando;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Make_Our_Customers_Happy {

    static int[] C = new int[8];
    static int NA, NB, NC;
    
    static int count(int n) {
        if (n == 1) {
            int c = 0;
            int t = Math.min(NA, C[1]);
            NA -= t;
            c += t;
            t = Math.min(NB, C[2]);
            NB -= t;
            c += t;
            t = Math.min(NC, C[4]);
            NC -= t;
            c += t;
            return c + count(2);
        }
        if (n == 3) {
            int t = C[7];
            t = Math.min(t, NA);
            t = Math.min(t, NB);
            t = Math.min(t, NC);
            return t;
        }
        int max = 0;
        for (int ab = 0; ab < 2; ab++) {
            for (int ac = 0; ac < 2; ac++) {
                for (int bc = 0; bc < 2; bc++) {
                    int na = NA;
                    int nb = NB;
                    int nc = NC;
                    int c = 0;
                    if (ab == 1) {
                        int t = C[3];
                        t = Math.min(t, NA);
                        t = Math.min(t, NB);
                        NA -= t;
                        NB -= t;
                        c += t;
                    }
                    if (ac == 1) {
                        int t = C[5];
                        t = Math.min(t, NA);
                        t = Math.min(t, NC);
                        NA -= t;
                        NC -= t;
                        c += t;
                    }
                    if (bc == 1) {
                        int t = C[6];
                        t = Math.min(t, NB);
                        t = Math.min(t, NC);
                        NB -= t;
                        NC -= t;
                        c += t;
                    }
                    max = Math.max(c + count(3), max);
                    NA = na;
                    NB = nb;
                    NC = nc;
                }
            }
        }
        return max;
    }
    
    static int solve() {
        return count(1);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.getProperty("local") == null? 
                System.in: Make_Our_Customers_Happy.class.getResourceAsStream("Make_Our_Customers_Happy.in"));
        NA = scanner.nextInt();
        NB = scanner.nextInt();
        NC = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String[] a = scanner.nextLine().split(",");
            int m = 0;
            for (String s: a) {
                switch (s) {
                case "A": m |= 1; break;
                case "B": m |= 2; break;
                case "C": m |= 4; break;
                }
            }
            C[m]++;
        }
        System.out.println(solve());
        scanner.close();
    }

}
