/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 17/08/2016
 * 
 * Hacker rank
 * Week of Code 22
 * Problem: Cookie Party
 * Status: accepted
 * 
 * Problem
 * 
 * You're having a cookie party tonight! You're expecting n guests and 
 * you've already made m cookies. You want to distribute all the cookies 
 * evenly between your guests in such a way that each guest receives the 
 * same number of whole cookies. If there are not enough cookies to give 
 * everyone the same amount, you must make some number of additional cookies.
 * 
 * Given n and m, find and print the minimum number of additional cookies you 
 * must make so that everybody receives the same number of cookies.
 * 
 * Input Format
 * 
 * A single line of two space-separated integers describing the respective 
 * values of n and m. 
 * 
 * Output Format
 * 
 * Print a single integer denoting the number of additional cookies you 
 * need to make so that everyone at the cookie party has the same number 
 * of whole cookies.
 * 
 * Sample Input
 * 
3 2
 * 
 * Sample Output
 * 
1
 * 
 */

package com.vaani.algo.compete.hackerrank.codesprint.weekofcode._22;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cookie_Party {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Cookie_Party.class.getResourceAsStream("Cookie_Party.in"));
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        if (n >= m)
            System.out.println(n - m);
        else
            System.out.println((m % n == 0)? 0: n - (m % n));
        scanner.close();
    }
    
}
