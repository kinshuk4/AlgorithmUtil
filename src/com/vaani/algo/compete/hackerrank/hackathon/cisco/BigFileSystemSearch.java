/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 10/04/2015
 * 
 * Hacker rank
 * Cisco challenge
 * Problem: Big File System Search
 * Status: accepted
 * 
 * You have recently got a job as a system administrator in a big IT company.
 * The first problem which you encountered is to build a data structure which 
 * allows you to do some set-oriented searching in a big file system.
 * You have N files in your system. Each file is represented by a sequence of
 * integers separated by a single space.
 * You have to perform Q queries. For each query, you will be given an array 
 * A = {a1, a2, ..., aK}, and you have to perform any one of the following three
 * queries on the whole file system:
 * 
 * - query_all: Returns the number of files which contain all elements from 
 * array A.
 * - query_any: Returns the number files which contain at least one element 
 * from array A.
 * - query_some: Returns the number of files which contain at least one element 
 * and at most K−1 elements from array A.
 * 
 * Note: A array, X, will be considered to contain all elements of another array, 
 * Y, if and only if the frequency of each element of Y doesn't exceed the 
 * respective frequency in array X. That is, if X={9,5,4} and Y={9,9,5} then
 * X doesn't consists all elements of Y, as frequency of 9 in Y is greater 
 * than X.
 * 
 * Input Format
 * 
 * In the first line there is only one integer, N, denoting the number of files 
 * in the file system.
 * 
 * N lines follow. In the ith of them there is a description of the ith file. 
 * Each such description consists of a number, M, denoting the number of 
 * integers in this file, followed by M integers representing these integers.
 * 
 * The next line consists of a single integer, Q, denoting the number of 
 * queries which you have to perform.
 * 
 * Q lines follow. In the ith of them there is a description of the ith query. 
 * Each query consists of an integer, T, denoting the type of the query followed 
 * by an integer, K, denoting the length of array A. Then follows K integers, in 
 * the same line, representing the elements of A.
 * 
 * Output Format
 * 
 * Print exactly Q lines. In the ith of them print the answer to the ith query.
 * 
 * Sample Input
 * 
3
3 1 2 3
3 2 3 4
3 3 4 5
3
1 2 3 4
2 2 2 5
3 3 2 3 4
 * 
 * Sample Output
 * 
 * 2
 * 3
 * 2
 * 
 */

package com.vaani.algo.compete.hackerrank.hackathon.cisco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class BigFileSystemSearch {

    static Map<Integer, List<Integer>> intToFiles = new HashMap<>();
    
    static Map<Integer, Integer> fileToMatches(int[] A, boolean isAll) {
        Map<Integer, Integer> fileToMatches = new HashMap<>();
        Map<Integer, List<Integer>> cache = new HashMap<>();
        for (int n: A) {
            List<Integer> files = cache.get(n);
            if (files == null) {
                files = intToFiles.get(n);
                if (files == null)
                    if (isAll)
                        return Collections.emptyMap();
                    else
                        continue;
                files = new ArrayList<>(files);
                cache.put(n, files);
            }
            if (files.isEmpty() && isAll)
                return Collections.emptyMap();
            Set<Integer> uniq = new HashSet<>();
            Iterator<Integer> iter = files.iterator();
            while (iter.hasNext()) {
                Integer file = iter.next();
                if (!uniq.contains(file)) {
                    uniq.add(file);
                    iter.remove();
                    fileToMatches.merge(file, 1, (c, i) -> c + i);
                }
            }            
        }
        return fileToMatches;
    }
    
    static int queryAll(int[] A) {
        Map<Integer, Integer> fileToMatches = fileToMatches(A, true);
        return (int) fileToMatches.entrySet().stream().mapToInt((e) -> e.getValue() < A.length? 0: 1).sum();
    }
    
    static int queryAny(int[] A) {
        Map<Integer, Integer> fileToMatches = fileToMatches(A, false);
        return fileToMatches.size();
    }
    
    static int querySome(int[] A) {
        Map<Integer, Integer> fileToMatches = fileToMatches(A, false);
        return (int) fileToMatches.entrySet().stream().mapToInt((e) -> e.getValue() >= A.length? 0: 1).sum();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < N; ++i) {
            int n = scanner.nextInt();
            for (int j = 0; j < n; ++j) {
                int v = scanner.nextInt();
                List files = intToFiles.get(v);
                if (files == null) {
                    files = new ArrayList<>();
                    intToFiles.put(v, files);
                }
                files.add(i);
            }
            scanner.nextLine();
        }
//        System.out.println(intToFiles);
        int Q = scanner.nextInt();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < Q; ++i) {
            int T = scanner.nextInt();
            int K = scanner.nextInt();
            int[] A = new int[K];
            IntStream.range(0, K).forEach((j) -> A[j] = scanner.nextInt());
            switch (T) {
            case 1:
                res.append(queryAll(A)).append('\n');
                break;
            case 2:
                res.append(queryAny(A)).append('\n');
                break;
            case 3:
                res.append(querySome(A)).append('\n');
                break;
            }
        }
        scanner.close();
        System.out.println(res);
    }

}
