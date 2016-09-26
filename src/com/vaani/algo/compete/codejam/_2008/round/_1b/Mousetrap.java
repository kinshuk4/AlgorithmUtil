/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Code jam
 * Round 1B 2008
 * Problem C. Mousetrap
 * Small input: correct
 * Large input: time limit
 * 
 * Problem
 * 
 * Mousetrap is a simple card game for one player. It is played with a shuffled 
 * deck of cards numbered 1 through K, face down. You play by revealing the top 
 * card of the deck and then putting it on the bottom of the deck, keeping count
 * of how many cards you have revealed. If you reveal a card whose number matches 
 * the current count, remove it from the deck and reset the count. If the count 
 * ever reaches K+1, you have lost. If the deck runs out of cards, you win.
 * 
 * Suppose you have a deck of 5 cards, in the order 2, 5, 3, 1, 4. You will reveal 
 * the 2 on count 1, the 5 on count 2, then the 3 on count 3. Since the value 
 * matches the count, you remove the 3 from the deck, and reset the count. You 
 * now have 4 cards left in the order 1, 4, 2, 5. You then reveal the 1 on count 1, 
 * and remove it as well (you're doing great so far!). Continuing in this way you 
 * will remove the 2, then the 4, and then finally the 5 for victory.
 * 
 * You would like to set up a deck of cards in such a way that you will win the 
 * game and remove the cards in increasing order. We'll call a deck organized in 
 * this way "perfect." For example, with 4 cards you can organize the deck as 1, 
 * 4, 2, 3, and you will win by removing the cards in the order 1, 2, 3, 4.
 * 
 * Input
 * 
 * The first line of input gives the number of cases, T. Each test case starts 
 * with a line containing K, the number of cards in a deck. The next line starts 
 * with an integer n, which is followed by n integers (d1,d2, ...), indices into 
 * the deck.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: " followed by n 
 * integers (k1,k2, ...), where ki is the value of the card at index di of a 
 * perfect deck of size K. The numbers in the output should be separated by spaces, 
 * and there must be at least one space following the colon in each "Case #x:" line.
 * 
 * Sample
 * 
 * Input
 * 2
 * 5
 * 5 1 2 3 4 5
 * 15
 * 4 3 4 7 10
 * 
 * Output
 * Case #1: 1 3 2 5 4
 * Case #2: 2 8 13 4
 * 
 */

package com.vaani.algo.compete.codejam._2008.round._1b;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Mousetrap {
    
    /*
     * Returns perfect sequence for numbers 1..n
     * Perfect sequence means that numbers can be deleted 
     * in increasing order.
     * For example, 4 numbers organized as 1, 4, 2, 3, are in 
     * perfect order since numbers can be removed in the order 
     * 1, 2, 3, 4:
     * - del 1 counting 1: 4, 2, 3
     * - del 2 counting 2: 3, 4
     * - del 3 counting 3: 4
     * - del 4 counting 4:
     * Another example for 5 numbers is 1, 3, 2, 5, 4.
     */
    static Integer[] perfect(int n) {
        Integer[] a = new Integer[n];
        int[] next = new int[n];
        IntStream.range(0, next.length).forEach((i) -> next[i] = i + 1);
        next[next.length - 1] = 0;
        for (int i = 1, c = 0, p = next.length - 1; i <= n; ++i) {
            int t = c;
            for (int j = 1; j < i; ++j) {
                p = c;
                c = next[c];
                // we hit cycle at c so lets skip
                // all of them
                if (t == c) {
                    if (i / j != 1) {
                        j *= i / j;
                        p = c;
                        c = next[c];
                    }
                }
            }
            a[c] = i;
            next[p] = next[c];
            c = next[c];
        }
        return a;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/tmp/in"));
        int T = scanner.nextInt();
        for (int t = 1 ; t <= T; ++t) {
            int k = scanner.nextInt();
            int n = scanner.nextInt();
            Integer[] a = perfect(k);
            String r = IntStream.range(0, n).map((i) -> a[scanner.nextInt() - 1])
                .collect(() -> new StringBuilder(), 
                    (s, e) -> s.append(e + " "), (s1, s2) -> s1.append(s2)).toString();
            System.out.format("Case #%d: %s\n", t, r);
        }
        scanner.close();
    }

}
