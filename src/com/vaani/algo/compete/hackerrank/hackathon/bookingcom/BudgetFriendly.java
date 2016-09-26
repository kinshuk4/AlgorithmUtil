/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 06/09/2015
 * 
 * Hacker rank
 * Booking.com Hackathon
 * Problem: Budget Friendly
 * Status: accepted
 * 
 * Problem
 * 
 * Lots of users are visiting Booking.com trying to plan a trip to visit multiple cities, usually 
 * those user have a budget on how much they can spend on their trip. We wanna help those users 
 * to find the best hotels which is fitting within their budget.
 * You will get the list of cities a user is interested to visit, also a list of hotels within 
 * each city, each hotel has a price and a score which, and of course we would have the amount 
 * of euros that the user is ready to spend.
 * You are required to find the highest total score of the hotels that the user can book 
 * within his budget.
 * 
 * Input Format
 * 
 * First line has two numbers number of cities C and user's budget B, each city will start with 
 * a number N which is the number of available hotels in this city, followed by N line, each line 
 * has two numbers, an integer representing hotel price (Pij) & a number representing hotel 
 * score (Sij).
 * 
 * Output Format
 * 
 * One number which is the highest total score for the hotels where the user can book, -1 if 
 * the user budget can't achieve booking the whole trip.
 * Print two digits after decimal point, rounded to 2 decimal places. If the answer is -1, 
 * than just print "-1".
 * Note: User has to book exactly one hotel per destination, and he has to book all destinations
 * 
 * Sample Input
 * 
2 50
3
10 7.8
15 6.4
12 8.111
3
25 7.8
19 6.4
50 8.1
 * 
 * Sample Output
 * 
15.91
 * 
 */

package com.vaani.algo.compete.hackerrank.hackathon.bookingcom;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BudgetFriendly {
    
    static BigDecimal[][] S = new BigDecimal[10][100];
    static int[][] P = new int[10][100];
    static BigDecimal[][] M = new BigDecimal[10][10000];
    static int[] H = new int[10];
    static int C;
    static int B;
    static BigDecimal MINUS_ONE = BigDecimal.valueOf(-1);
    
    static BigDecimal maxScoreTrip(int c, int b) {
        if (b < 0) return MINUS_ONE;
        if (c < 0) return BigDecimal.ZERO;
        if (M[c][b] != null) return M[c][b];
        BigDecimal max = MINUS_ONE;
        for (int h = 0; h < H[c]; ++h) {
            BigDecimal score = maxScoreTrip(c - 1, b - P[c][h]);
            if (score == MINUS_ONE) continue;
            score = score.add(S[c][h]);
            if (max.compareTo(score) < 0)
                max = score;
        }
        M[c][b] = max;
        return max;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(BudgetFriendly.class.getResourceAsStream("BudgetFriendly1.in"));
        C = scanner.nextInt();
        B = scanner.nextInt();
        IntStream.range(0, C).forEach(c -> {
            H[c] = scanner.nextInt();
            IntStream.range(0, H[c]).forEach(h -> {
                P[c][h] = scanner.nextInt();
                S[c][h] = scanner.nextBigDecimal();
            });
        });
        BigDecimal r = maxScoreTrip(C - 1, B - 1);
        if (r == MINUS_ONE) System.out.println(-1);
        else System.out.println(r.setScale(2, RoundingMode.HALF_UP));
        scanner.close();
    }
    
}
