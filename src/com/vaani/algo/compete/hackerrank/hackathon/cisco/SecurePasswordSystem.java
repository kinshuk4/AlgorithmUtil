/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 10/04/2015
 * 
 * Hacker rank
 * Cisco challenge
 * Problem: Secure Password System
 * Status: accepted
 * 
 * Problem
 * 
 * Password security is a very important topic today. We define a password 
 * system here as a set of requirements that a valid password in this system 
 * must fulfill. In each such system every valid password consists only of 
 * digits [0−9]. In addition, for each such system there are two requirements 
 * which a valid password must fulfill:
 *  - Minimum length of a valid password.
 *  - Maximum length of a valid password.
 * We say that a password system is secure if and only if there are more than 
 * one million (106) different possible passwords in this system. Your task, 
 * as a security expert, is to decide for a given password system if it is 
 * secure.
 * Note: Repetition of digits are allowed. That is 111 is a valid password of 
 * length 3. Leading 0's are allowed. That is 001 and 000 are valid password of 
 * length 3. You have to consider the sum of all passwords whose length lies in 
 * range of minimum and maximum value. That is if minimum length is 3 and maximum
 * length is 5, then you have to find total count of passwords whose length are 
 * either 3, 4 or 5.
 * 
 * Input Format
 * 
 * In the first line there is a single integer, T, denoting the number of test 
 * cases. T lines follow. The ith line denotes a single test case and describes a 
 * given password system. It consists of two integers, m and M, denoting the minimum 
 * password length and the maximum password length in this system.
 * 
 * Constraints
 * 
 *     1 <= T <= 100
 *     1 <= m <= M <= 10
 * 
 * Output Format
 * 
 * Print exactly T lines. In the ith of them print "YES" (without quotes) if the 
 * ith password system is secure, otherwise print "NO" (without quotes).
 * 
 * Sample Input
 * 
 * 2
 * 5 5
 * 7 8
 * 
 * Sample Output
 * 
 * NO
 * YES
 * 
 */

package com.vaani.algo.compete.hackerrank.hackathon.cisco;

import java.util.Scanner;

public class SecurePasswordSystem {

    private static boolean isSecure(int min, int max) {
        long c = 0;
        while (max >= min) {
            c += Math.pow(10, max);
            if (c > 1_000_000)
                return true;
            max--;
        }
        return false;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        scanner.nextLine();
        String[] res = new String[T];
        for (int i = 0; i < T; ++i) {
            res[i] = String.valueOf(isSecure(scanner.nextInt(), scanner.nextInt())? "YES": "NO");
        }
        for (int i = 0; i < T; ++i) {
            System.out.println(res[i]);
        }
        scanner.close();
    }

}
