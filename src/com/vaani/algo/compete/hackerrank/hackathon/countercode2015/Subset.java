/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 30/07/2015
 * 
 * Hacker rank
 * CounterCode 2015
 * Problem: Subset
 * Status: timeout
 * 
 * Problem
 * 
 * You have a list of integers, initially the list is empty.
 * You have to process Q operations of three kinds:
 * 
 * - add s: Add integer s to your list, note that an integer 
 * can exist more than one time in the list
 * 
 * - del s: Delete one copy of integer s from the list, it's 
 * guaranteed that at least one copy of s will exist in the 
 * list
 * 
 * - cnt s: Count how many integers a are there in the list 
 * such that a AND s = a , where AND is bitwise AND operator
 * 
 * Input Format
 * 
 * First line contains an integer Q.
 * Each of the following Q lines contains an operation type 
 * string T and an integer s. 
 * 
 * Output Format
 * 
 * For each cnt s operation, output the answer in a new line. 
 * 
 * Sample Input
 * 
7
add 11
cnt 15
add 4
add 0
cnt 6
del 4
cnt 15
 * 
 * Sample Output
 * 
1
2
2
 * 
 */

package com.vaani.algo.compete.hackerrank.hackathon.countercode2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Subset {

    static Map<Integer, Integer> M = new HashMap<>();
    static int[] B = {
        1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 
        262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 
        268435456, 536870912};
    static int[] C = new int[16];
    
    private static int[] binary(int n) {
        return String.format("%16s", Integer.toBinaryString(n)).replace(' ', '0').
                chars().map((ch) -> ch - '0').toArray();
    }

    private static void add(int n) {
        Integer c = M.get(n);
        if (c == null) {
            c = 0;
            int[] a = binary(n);
            IntStream.range(0, a.length).filter((i) -> a[i] == 0).
                forEach((i) -> C[15 - (a.length - i - 1)]++);
        }
        c++;
        M.put(n, c);
    }
    
    private static void del(int n) {
        Integer c = M.get(n);
        if (c == 1) {
            int[] a = binary(n);
            IntStream.range(0, a.length).filter((i) -> a[i] == 0).
                forEach((i) -> C[15 - (a.length - i - 1)]--);
            M.remove(n);
        } else
            M.put(n, c - 1);
    }
    
    private static int cnt(int[] a, int p, int n) {
        if (p == a.length)
            return M.getOrDefault(n, 0);
        if (p != 0 && a[a.length - p - 1] == 0 && C[15 - p] == 0)
            return 0;
        int r = cnt(a, p + 1, n);
        if (a[a.length - p - 1] == 0)
            return r;
        return r + cnt(a, p + 1, n | B[p]);
    }
    
    private static int cnt(int n) {
        int[] a = Integer.toBinaryString(n).chars().map((ch) -> ch - '0').toArray();
        int k = Arrays.stream(a).map((i) -> i == 1? 1: 0).sum();
        if (M.size() < B[k])
            return M.entrySet().stream().
                    mapToInt((e) -> (e.getKey() & n) == e.getKey()? e.getValue(): 0).sum();
        return cnt(a, 0, 0);
    }

    public static void main(String[] args) throws FileNotFoundException {
//        cnt(11);
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(new File("/home/lynx/tmp/inin"));
        int Q = scanner.nextInt();
        scanner.nextLine();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < Q; ++i) {
            String q = scanner.next();
            int n = scanner.nextInt();
            switch (q) {
            case "add":
                add(n);
                break;
            case "del":
                del(n);
                break;
            case "cnt":
                res.append(cnt(n)).append('\n');
                break;
            }
        }
        scanner.close();
        System.out.println(res);
    }
    
}
