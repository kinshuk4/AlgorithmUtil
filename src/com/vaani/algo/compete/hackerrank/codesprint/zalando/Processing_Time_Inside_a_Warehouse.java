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
 * Problem: Processing Time Inside a Warehouse
 * Status: accepted
 * 
 * Problem
 * 
 * Before each product is shipped to a customer, it needs to be
 * retrieved and packed by a warehouse employee. After that, 
 * packages are loaded into a truck which, in turn, delivers the 
 * products to our happy customers.
 * Each employee picks one product, packs it, loads it into the 
 * truck, and then repeats the process for another product. The 
 * amount of time (in minutes) it takes for each employee to 
 * retrieve, pack, and load a product into the truck varies from 
 * employee to employee.
 * Given products that need to be packed and shipped and warehouse 
 * employees who pack and load products, what is the minimum number 
 * of minutes that the truck must wait for all the products to be 
 * loaded?
 * 
 * Input Format
 * 
 * The first line contains two space-separated integers denoting 
 * the respective values for (the number of products) and (the number 
 * of employees).
 * The second line contains space-separated integers describing,
 * where each is the number of minutes it takes for employee to 
 * process one order ().
 * 
 * Output Format
 * 
 * Print the minimum number of minutes that the truck will have to 
 * wait for all the products to be packed and loaded.
 * 
 * Input
 * 
7 3
1 2 3
 * 
 * Output
 * 
4
 * 
 */
package com.vaani.algo.compete.hackerrank.codesprint.zalando;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.LongUnaryOperator;

public class Processing_Time_Inside_a_Warehouse {

    static long[] M;
    static long N;
    
    /*
     * Applies lambda for values in range s..e.
     * Lambda return codes:
     *  negative - current value is small
     *  0 or positive - current value is big
     *  Returns latest big value encountered.
     */
    static long binarySearch(long s, long e, LongUnaryOperator oper) {
        if (e - s < 0) return -1;
        long m = (s + e) / 2;
        long r = oper.applyAsLong(m);
        if (r < 0)
            return binarySearch(m + 1, e, oper);
        long res = binarySearch(s, m - 1, oper);
        return res == -1? m: res;
    }
    
    static int packagesLeft(long t) {
        long n = N;
        int i = 0;
        while (i < M.length) {
            if (t < M[i]) return -1;
            n -= t / M[i];
            if (n < 0) return 1;
            if (n == 0) return 0;
            i++;
        }
        return -1;
    }
    
    static long solve() {
        Arrays.sort(M);
        long r = 1;
        while (packagesLeft(r) < 0) {
            r *= 2;
        }
        return binarySearch(r / 2, r, t -> packagesLeft(t));
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.getProperty("local") == null? 
                System.in: Processing_Time_Inside_a_Warehouse.class.getResourceAsStream("Processing_Time_Inside_a_Warehouse.in"));
        N = scanner.nextLong();
        M = new long[scanner.nextInt()];
        Arrays.setAll(M, i -> scanner.nextLong());
        System.out.println(solve());
        scanner.close();
    }

}
