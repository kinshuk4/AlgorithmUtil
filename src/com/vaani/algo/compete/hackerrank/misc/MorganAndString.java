/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 24/05/2016
 *
 * Hacker rank
 * Problem: Morgan and a String
 * Status: accepted
 * 
 * Problem
 * 
 * Jack and Daniel are friends. Both of them like letters, 
 * especially upper-case ones.
 * They are cutting upper-case letters from newspapers, and 
 * each one of them has their collection of letters stored in 
 * separate stacks.
 * One beautiful day, Morgan visited Jack and Daniel. He saw 
 * their collections. Morgan wondered what is the lexicographically
 * minimal string, made of that two collections. He can take a 
 * letter from a collection when it is on the top of the stack.
 * Also, Morgan wants to use all the letters in the boys' collections.
 * 
 * Input Format
 * 
 * The first line contains the number of test cases, .
 * Every next two lines have such format: the first line contains
 * string , and the second line contains string.
 * 
 * Output Format
 * 
 * Output the lexicographically minimal string for each test case
 * in new line.
 * 
 * Input
 * 
3
DAD
DAD
JACK
DANIEL
ABACABA
ABACABA
 * 
 * Output
 * 
DADADD
DAJACKNIEL
AABABACABACABA
 * 
 */
/*
 * This implementation passed all test cases but after reading the
 * editorial I found that it is broken. It will fail for the test
 * case like:
 * 
1
BAABECL
BAABECA
 * 
 * This problem should be solved with suffix arrays as in editorial. 
 */
package com.vaani.algo.compete.hackerrank.misc;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MorganAndString {

    static void update(char[] a, char[] b, int[] d) {
        while (d[0] < a.length && d[1] < b.length) {
            if (a[d[0]] != b[d[1]]) return;
            d[0]++;
            d[1]++;
        }
        d[0] = d[0] == a.length? a.length - 1: d[0];
        d[1] = d[1] == b.length? b.length - 1: d[1];
    }
    
    static void solve(char[] a, char[] b) {
        a = Arrays.copyOf(a, a.length + 1);
        b = Arrays.copyOf(b, b.length + 1);
        a[a.length - 1] = 'Z';
        b[b.length - 1] = 'Z';
        int i = 0;
        int j = 0;
        int[] d = new int[]{-1, -1};
        while (i < a.length - 1 && j < b.length - 1) {
            System.out.print(a[i] < b[j]? a[i]: b[j]);
            if (a[i] < b[j]) 
                i++;
            else if (a[i] > b[j]) 
                j++;
            else {
                if (d[0] <= i || d[1] <= j || d[0] + 1 == a.length || d[1] + 1 == b.length) {
                    d[0] = i;
                    d[1] = j;
                    update(a, b, d);
                }
                if (a[d[0]] < b[d[1]])
                    i++;
                else
                    j++;
            }
        }
        while (i < a.length - 1) System.out.print(a[i++]);
        while (j < b.length - 1) System.out.print(b[j++]);
        System.out.println();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(MorganAndString.class.getResourceAsStream("MorganAndString.in"));
        int t = scanner.nextInt();
        scanner.nextLine();
        IntStream.range(0, t).forEach(i -> 
            solve(scanner.nextLine().toCharArray(), 
                    scanner.nextLine().toCharArray()));
        scanner.close();
    }

}
