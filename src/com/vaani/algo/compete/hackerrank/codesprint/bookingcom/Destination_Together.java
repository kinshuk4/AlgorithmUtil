/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 06/08/2016
 * 
 * Hacker rank
 * Booking.com CodeSprint
 * Problem: Destination Together
 * Status: accepted
 * 
 * Problem
 * 
 * John and Zizi are planning their respective autumn vacations. 
 * John has exactly n cities he plans to visit, Zizi has exactly m
 * cities she plans to visit, and they want to have exactly c
 * destination cities in common. Having been a couple for over 
 * two years without ever vacationing together, they used Booking.com 
 * to find a romantic final destination that appeals to both of 
 * their passions: marvelous L'viv in the Ukraine.
 * 
 * John and Zizi are software engineers who love creating and 
 * solving interesting problems. They want to know the number of 
 * different sequences in which they can visit their respective 
 * vacation destinations such that they visit each city exactly 
 * once and both meet up in L'viv as their final destination. 
 * Given n, m, and c, find and print the number of different ways 
 * they can do this.
 * 
 * Input Format
 * 
 * A single line of three space-separated positive integers denoting 
 * the respective values of n (the number of cities John plans to 
 * visit), m (the number of cities Zizi plans to visit), and c (the 
 * number of cities they both want to visit). 
 * 
 * Output Format
 * 
 * Print a single integer denoting the number of possible ways they can 
 * visit each city once and meet up in L'viv for their first vacation 
 * together.
 * 
 * Sample Input
 * 
3 4 2
 * 
 * Sample Output
 * 
24
 * 
 */

package com.vaani.algo.compete.hackerrank.codesprint.bookingcom;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.LongStream;

public class Destination_Together {
    
    static long fac(int n) {
        if (n <= 1) return 1;
        //System.out.println(n);
        return LongStream.range(1, n + 1)
                .reduce((r, i) -> r * i)
                .getAsLong();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Destination_Together.class.getResourceAsStream("Destination_Together.in"));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int c = scanner.nextInt();
        System.out.println(fac((n - c) + (m - c) + c - 1));
        scanner.close();
    }
    
}
