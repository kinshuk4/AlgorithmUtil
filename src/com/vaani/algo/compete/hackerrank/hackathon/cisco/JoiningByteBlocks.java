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
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class JoiningByteBlocks {

    static boolean isPoly(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--))
                return false;
        }
        return true;
    }

    static Map<String, Integer> M = new HashMap<>();
    
    static int cost(String[] a, BitSet s, int n) {
        Integer r = M.get(s.toString());
        if (r != null)
            return r;
//      System.out.println("a");
        if (n >= a.length)
            return 0;
        if (!s.get(n))
            return cost(a, s, n + 1);
        if (s.isEmpty())
            return 0;
        int min = Integer.MAX_VALUE;
        s.clear(n);
        if (isPoly(a[n])) {
            int c = cost(a, s, n + 1);
            if (c != Integer.MAX_VALUE)
                min = 1 + c;
        }
        int iter = 0;
        while ((iter = s.nextSetBit(iter)) != -1) {
            int i = iter++;
            if (!(isPoly(a[n] + a[i]) || isPoly(a[i] + a[n])))
                continue;
            s.clear(i);
            int c = cost(a, s, n + 1);
            if (c != Integer.MAX_VALUE)
                min = Math.min(min, 1 + c);
            s.set(i);
        }
        s.set(n);
        M.put(s.toString(), min);
        return min;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("/tmp/in"));
        StringBuilder res = new StringBuilder();
        while (scanner.hasNext()) {
            M.clear();
            int N = scanner.nextInt();
            scanner.nextLine();
            String[] a = new String[N];
            IntStream.range(0, N).forEach((i) -> a[i] = scanner.nextLine());
            BitSet s = new BitSet(N);
            s.set(0, N);
            res.append(cost(a, s, 0)).append('\n');
        }
        System.out.println(res);
        scanner.close();
    }
    
}
