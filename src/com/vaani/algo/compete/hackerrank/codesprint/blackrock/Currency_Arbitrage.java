/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 12/06/2016
 *
 * Hacker rank
 * BlackRock CodeSprint
 * Problem: Currency Arbitrage
 * Status: accepted
 * 
 * Problem
 * 
 * You must use your USD to buy EUR, then use your EUR to buy GBP, and 
 * finally use your GBP to buy USD, resulting in some sort of profit or loss 
 * (i.e., USD->EUR->GBP->USD).
 * Given 100000 USD for each trade, calculate the arbitrage profit truncated 
 * to whole dollars (USD); otherwise, print 0 if there is no arbitrage opportunity.
 * 
 * Input Format
 * 
 * The first line contains a single integer, N, denoting the number of quotes.
 * Each of the subsequent lines describes a quote in the form of three 
 * space-separated floating-point numbers:
 * - The first quote is a real number denoting the price quote for (USD/EUR).
 * - The second quote is a real number denoting the price quote for (EUR/GBP).
 * - The third quote is a real number denoting the price quote for (GBP/USD).

 * 
 * Output Format
 * 
 * For each trade, print a single line denoting the arbitrage profit for that 
 * trade; if no arbitrage opportunity exists, print 0. You should have a total 
 * of N lines of output.
 * 
 * Input
 * 
2
1.1837 1.3829 0.6102
1.1234 1.2134 1.2311
 * 
 * Output
 * 
114
0
 * 
 */
package com.vaani.algo.compete.hackerrank.codesprint.blackrock;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Currency_Arbitrage {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.getProperty("local") == null? 
                System.in: Currency_Arbitrage.class.getResourceAsStream("Currency_Arbitrage.in"));
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            double ue = scanner.nextDouble();
            double eg = scanner.nextDouble();
            double gu = scanner.nextDouble();
            int depo = 100_000;
            double p = depo / ue / eg / gu;
            System.out.println(p < depo? 0: (int)p - depo);
        }
        scanner.close();
    }

}
