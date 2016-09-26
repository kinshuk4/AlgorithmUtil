/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 15/05/2016
 * 
 * Hacker rank
 * Week of code 12
 * Problem: Favorite sequence
 * Status: correct
 * 
 * Johnny, like every mathematician, has his favorite sequence of distinct
 * natural numbers. Let’s call this sequence . Johnny was very bored, so he
 * wrote down copies of the sequence in his big notebook. One day, when Johnny
 * was out, his little sister Mary erased some numbers(possibly zero) from every
 * copy of and then threw the notebook out onto the street. You just found it. 
 * Can you reconstruct the sequence?
 * In the input there are sequences of natural numbers representing the copies 
 * of the sequence after Mary’s prank. In each of them all numbers are distinct. 
 * Your task is to construct the shortest sequence that might have been the original.
 * If there are many such sequences, return the lexicographically smallest one.
 * It is guaranteed that such a sequence exists.
 * 
 * Input Format
 * 
 * In the first line, there is one number denoting the number of copies of.
 * This is followed by and in next line a sequence of length representing one of 
 * sequences after Mary's prank. All numbers are separated by a single space. 
 * 
 * Output Format
 * 
 * In one line, write the space-separated sequence - the shortest sequence that 
 * might have been the original . If there are many such sequences, return the 
 * lexicographically smallest one.
 * 
 * Sample Input
 * 
2
2
1 3
3
2 3 4
 *
 * Sample Output
 * 
1 2 3 4
 *
 */

package com.vaani.algo.compete.hackerrank.weekofcode._12;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class FavouriteSequence {
    
    static final int SIZE = 100000;
    
    @SuppressWarnings("unchecked")
    static List<Integer>[] G = new List[SIZE];
    
    static void topologicalLexicographicSort() {
        int[] degrees = new int[SIZE];
        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G[i].size(); j++) {
                degrees[G[i].get(j)]++;
            }
        }
        Queue<Integer> q = new PriorityQueue<>((v, u) -> v - u);
        for (int v = 0; v < G.length; v++) {
            if (G[v].size() == 0) continue;
            if (degrees[v] == 0) q.add(v);
        }
        while (!q.isEmpty()) {
            Integer v = q.remove();
            for (int u: G[v]) {
                degrees[u]--;
                if (degrees[u] == 0) {
                    q.add(u);
                }
            }
            System.out.print((v + 1) + " ");
        }
        System.out.println();
    }

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null?
                new Scanner(System.in): new Scanner(FavouriteSequence.class.getResourceAsStream("FavouriteSequence.in"));
        int n = scanner.nextInt();
        Arrays.setAll(G, i -> new ArrayList(50));
        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt();
            int v = scanner.nextInt() - 1; 
            for (int j = 0; j < k - 1; j++) {
                int u = scanner.nextInt() - 1;
                G[v].add(u);
                v = u;
            }
        }
        topologicalLexicographicSort();
        scanner.close();
    }

}
