/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 10/08/2015
 * 
 * Hacker rank
 * Problem: Jim and the Skyscrapers
 * Status: accepted
 * 
 * Jim has invented a new flying object called HZ42. HZ42 is like a broom and can 
 * only fly horizontally, independent of the environment. 
 * Let us describe the problem in one dimensional space. We have in total N 
 * skyscrapers aligned from left to right. The i-th skyscraper has a height of hi. 
 * A flying route can be described as (i,j) with i≠j, which means, Jim starts his 
 * HZ42 at the top of the skyscraper i and lands on the skyscraper j. Since HZ42 
 * can only fly horizontally, Jim will remain at the height hi only. Thus the path 
 * (i,j) can be valid, only if each of the skyscrapers i,i+1,...,j−1,j is not strictly 
 * greater than hi and if the height of the skyscraper he starts from and arrives on 
 * have the same height.
 * Help Jim in counting the number of valid paths represented by ordered pairs (i,j). 
 * 
 * Input Format
 * 
 * The first line contains N, the number of skyscrapers. The next line contains N 
 * space separated integers representing the heights of the skyscrapers. 
 * 
 * Output Format
 * 
 * Print an integer that denotes the number of valid routes.
 * 
 * Sample Input
 * 
6
3 2 1 2 3 3
 * 
3
1 1000 1
 *
 * Sample Output
 * 
8
 * 
1
 *
 */
package com.vaani.algo.compete.hackerrank.misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JimAndTheSkyscrapers {

    static class Node {
        int v;
        Node l;
        Node r;
        int c;
        Node(int x) {
            v = x;
        }
    }
    
    static Node sortedArrayToBST(int[] num, int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        Node root = new Node(num[mid]);
        root.l = sortedArrayToBST(num, start, mid - 1);
        root.r = sortedArrayToBST(num, mid + 1, end);
        return root;
    }
    
    static class Item {
        int id, h, c;
        Item(int i) {
            id = i;
        }
    }
    
    private static int updateTree(Node r, int v)
    {
        if (r == null)
            return 0;
        if (r.v >= v) {
            r.c++;
            return updateTree(r.l, v);
        } else {
            return r.c + updateTree(r.r, v);
        }
    }
    
    static long countByPrefixWeights(Stream<Item> s) {
        return s.sorted((i1, i2) -> i1.c - i2.c).
            collect(Collectors.groupingBy((i) -> i.c)).entrySet().stream().
            mapToLong((e) -> {
                List<Item> l = e.getValue();
//              l.forEach((i) -> System.out.print(i.id + " "));
//              System.out.println();
                return (l.size() - 1) * (long)l.size() / 2;
        }).sum();
    }
    
    static long count(int[] a)
    {
        Node r = sortedArrayToBST(Arrays.stream(a).map((v) -> -v).sorted().toArray(), 0, a.length - 1);
        Item[] I = new Item[a.length];
        Arrays.setAll(I, Item::new);
        IntStream.range(0, I.length).forEach((i) -> I[i].h = a[i]);
        IntStream.range(0, a.length).forEach((i) -> I[i].c = updateTree(r, -a[i]));
//      Arrays.stream(I).forEach((i) -> System.out.println(i.c));
        return Arrays.stream(I).
            sorted((i1, i2) -> i1.h - i2.h).
//          forEach((i) -> System.out.println(i.id));
            collect(Collectors.groupingBy((i) -> i.h)).entrySet().stream().
            mapToLong((e) -> countByPrefixWeights(e.getValue().stream())).
            sum() * 2;
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(new File("C:\\in"));
        int N = scanner.nextInt();
        int[] A = new int[N];
        Arrays.setAll(A, (i) -> scanner.nextInt());
        System.out.println(count(A));
        scanner.close();
    }

}

