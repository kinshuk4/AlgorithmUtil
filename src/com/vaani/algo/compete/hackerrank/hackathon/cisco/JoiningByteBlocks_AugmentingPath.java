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
 * Problem: Joining Byte Blocks
 * Status: accepted / terminated due timeout
 * 
 * 
 * Problem
 * 
 * As you are probably aware, the Internet protocols specify a canonical byte order 
 * convention for data transmitted over the network called network byte order, so 
 * that machines with different byte order conventions can communicate. But what if 
 * such canonical byte order didn't exist? We would probably be trapped in chaos 
 * trying to figure out the byte order for every machine we want to communicate with.
 * But luckily, no matter the byte order (big-endian or little-endian), there will be 
 * byte blocks that will always be read correctly.
 * Imagine you have a list of N byte blocks. In order to minimize the number of 
 * trasmission operations required to send all of them, you want to pair as many as
 * possible blocks. Note that the resulting byte frame should have same representation 
 * in both network orders, i.e., they should be a palindrome when paired. The rules 
 * for such pairings are the following:
 *  - No block can be paired with itself.
 *  - A block can be paired zero or one time.
 *  - You cannot pair more than two blocks.
 * For the ease of representation we will use lowercase latin characters to represent 
 * byte blocks. Suppose we have two blocks [′a′,′a′,′f′] and [′f′], and they are paired 
 * to form the frame [′f′,′a′,′a′,′f′], then it has the same representation in any of 
 * the byte order.
 * Now, given the list of blocks, using the pairings described above, what's the minimum 
 * number of transmissions required to send them all?
 * Note: A block can either be transmitted alone, or paired with another block (if the 
 * pair satisfies above criteria).
 * 
 * Input Format
 * 
 * There will be multiple test cases per input file. Every test case will start with a 
 * number N telling you the size of the list. Then N lines follow, each one with a block,
 * where each byte has been replaced by its current English alphabet lowercase letter. 
 * No test case will have more than 3000 potential pairs.
 * 
 * Output Format
 * 
 * Output a single line per test case in the input with the required answer.
 * 
 * Sample Input
 * 
 * 6
 * aaababa
 * aa
 * ababaaa
 * baaa
 * a
 * b
 * 9
 * aabbaabb 
 * bbaabbaa
 * aa
 * bb
 * a
 * bbaa
 * bba
 * bab
 * ab
 * 
 * Sample Output
 * 
 * 3
 * 5
 * 
 */

package com.vaani.algo.compete.hackerrank.hackathon.cisco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class JoiningByteBlocks_AugmentingPath {
    
    static boolean isPoly(String s) {
        int l = 0;
        int r = s.length() - 1;
        boolean res = true;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                res = false;
                break;
            }
        }
        return res;
    }

    static boolean[] visited;
    static int[] M;
    static List<Integer>[] G;
    
    static boolean augment(int v)
    {
        if (visited[v]) 
            return false;
        visited[v] = true;
        for(int a: G[v])
        {
            if (visited[a]) continue;
            if (M[a] == -1 || augment(M[a]))
            {
                M[a] = v;
                M[v] = a;
                return true;
            }   
        }
        return false;
    }
    
    static int cost(boolean tryToImprove) {
        int max = 0;
        for (int v = 0; v < G.length; ++v) 
        {
            visited = new boolean[G.length];
            if (M[v] == -1)
                max += augment(v)? 1: 0;
        }
        int[] exposed = IntStream.range(0, M.length).filter((i) -> M[i] == -1).toArray();
        max += exposed.length;
        if (!tryToImprove) return max;
        for (int v: exposed) {
            if (G[v].isEmpty()) continue;
            Arrays.fill(M, -1);
            int a = G[v].get(0);
            M[a] = v;
            M[v] = a;
            max = Math.min(cost(false) + 1, max);
        }
        return max;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    static int cost(String[] a) {
        G = new List[a.length];
        Arrays.setAll(G, (i) -> new ArrayList());
        for (int i = 0; i < a.length; ++i) {
            for (int j = i + 1; j < a.length; ++j) {
                if (isPoly(a[i] + a[j]) || isPoly(a[j] + a[i])) {
                    G[i].add(j);
                    G[j].add(i);
                }
            }
        }
        M = new int[G.length];
        Arrays.fill(M, -1);
        return cost(true);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(new File("/tmp/in"));
        while (scanner.hasNextInt()) {
            int N = scanner.nextInt();
//            System.out.println(N);
            scanner.nextLine();
            String[] a = new String[N];
            IntStream.range(0, N).forEach((i) -> a[i] = scanner.nextLine());
            System.out.println(cost(a));
        }
        scanner.close();
    }

}
