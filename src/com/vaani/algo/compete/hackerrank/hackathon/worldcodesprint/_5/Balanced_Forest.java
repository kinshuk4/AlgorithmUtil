/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 05/08/2016
 * 
 * Hacker rank
 * World CodeSprint #5
 * Problem: Balanced Forest
 * Status: accepted
 * 
 * Greg received an n-node tree as a graduation gift, where 
 * each node i contains ci coins. He wants to insert exactly 
 * one new node, w, into the tree using the following process:
 * 
 *  - Select a node, v, where 1 <= v <= n.
 *  - Create a new edge connecting node v to a new node, w.
 *  - Add cw coins to node w (this can be any non-negative integer).
 * 
 * Now that Greg's tree has n + 1 nodes, he wants to cut two of 
 * its edges to create a forest of 3 trees where each tree contains 
 * an equal number of coins. If such a configuration is possible, 
 * he calls it a balanced forest.
 * For each tree Greg receives as a gift, determine the minimum 
 * value of cw such that the tree can be split into a balanced forest 
 * (meaning that each of the forest's three trees have the same 
 * number of coins); if no cw exists that enables Greg to create a 
 * balanced forest, print -1 instead.
 * 
 * Input Format
 * 
 * The first line contains a single integer, q, denoting the number 
 * of trees gifted to Greg. The subsequent lines describe each query 
 * in the following format:
 * 
 *  - The first line contains an integer, n, denoting the number of 
 * nodes in the tree.
 *  - The second line contains n space-separated integers describing 
 * the respective values of c1, c2, ..., cn, where each ci denotes 
 * the number of coins at node i.
 *  - Each line j of the n - 1 subsequent lines contains two 
 * space-separated integers xj, and yj (where 1 <= xj, yj <= n), 
 * describing edge j connecting nodes xj and yj.
 * 
 * Output Format
 * 
 * For each query, print the minimum value of cw on a new line; if no 
 * such value exists, print -1 instead. 
 * 
 * 
 * Sample Input
 * 
2
5
1 2 2 1 1
1 2
1 3
3 5
1 4
3
1 3 5
1 3
1 2
 *
 * Sample Output
 * 
2
-1
 *
 *
 */

package com.vaani.algo.compete.hackerrank.hackathon.worldcodesprint._5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class Balanced_Forest {

    static Node[] Nodes;
    static Node Root;
    
    static class Node {
        long i, lo, hi, c;
        long total;
        List<Node> adj = new ArrayList<>();
        @Override
        public String toString() {
            return String.format("inx %d total %d", i, total);
        }
    }
    
    /*
     * Use binary search to find index where elements v
     * begins or index of the first element bigger than v. 
     * Search range is [s, e].
     * If all elements are less than value in cmp -1 is returned.
     * cmp(T t) should return:
     * - < 0 if t is < than searched value
     * - > 0 if t is > than searched value
     */
    static <T> int binSearch(T[] a, int s, int e, Function<T, Integer> cmp)
    {
        if (e < s)
            return -1;
        if (e == s) {
            if (cmp.apply(a[e]) >= 0)
                return s;
            else
                return -1;
        }
        int m = (e + s) / 2;
        if (cmp.apply(a[m]) < 0)
            return binSearch(a, m + 1, e, cmp);
        else {
            int p = binSearch(a, s, m - 1, cmp);
            return p == -1? m: p;
        }
    }
    
    /*
     * Preprocess tree nodes to support isParent query
     * for them in constant time.
     */
    static long C;
    static void postOrder(Node n, Node prev) {
        if (n == null)
            return;
        n.lo = C;
        Predicate<Node> filter = a -> a != prev;
        n.adj.stream()
            .filter(filter)
            .forEach(a -> postOrder(a, n));
        C++;
        n.i = C;
        n.hi = C;
        n.total += n.adj.stream()
                .filter(filter)
                .mapToLong(v -> v.total)
                .sum();
    }
    
    static boolean isParent(int p, int c) {
        return (Nodes[p].lo <= Nodes[c].i && Nodes[c].i < Nodes[p].hi);
    }
    
    static Function<Node, Integer> makeCmp(long l) {
        return n -> Long.compareUnsigned(n.total, l);
    }
    
    static long solve() {
        C = 0;
        postOrder(Root, null);
        Arrays.sort(Nodes, (n1, n2) -> 
            Long.compareUnsigned(n1.total, n2.total));
        long lo = Root.total / 3;
        if (Root.total % 3 != 0) 
            lo++;
        long hi = Root.total / 2;
        int s = binSearch(Nodes, 0, Nodes.length - 1, makeCmp(lo));
        if (s == -1) 
            return -1;
        int e = binSearch(Nodes, 0, Nodes.length - 1, makeCmp(hi));
        if (e == -1)
            e = Nodes.length - 1;
        long min = Long.MAX_VALUE;
        // check for all possible non full subtrees
        for (int i = 0; i < s; i++) {
            long coins = Nodes[i].total;
            if ((Root.total - coins) % 2 != 0)
                continue;
            long size = (Root.total - coins) / 2;
            if (size < coins)
                continue;
            long qsize = size + coins;
            int j = binSearch(Nodes, 0, Nodes.length - 1, makeCmp(qsize));
            if (j != -1 && Nodes[j].total == qsize && isParent(j, i)) {
                min = Math.min(min, size - coins);
            }
            j = binSearch(Nodes, 0, Nodes.length - 1, makeCmp(size));
            if (j != -1 && Nodes[j].total == size && !isParent(j, i)) {
                min = Math.min(min, size - coins);
            }
        }
        // check for all possible full subtrees
        for (int i = s; i <= e; i++) {
            long size = Nodes[i].total;
            long qsize1 = size * 2;
            int j = binSearch(Nodes, 0, Nodes.length - 1, makeCmp(qsize1));
            if (j != -1 && Nodes[j].total == qsize1 && isParent(j, i)) {
                min = Math.min(min, size - (Root.total - qsize1));
            }
            long qsize2 = size + (Root.total - size * 2);
            j = binSearch(Nodes, 0, Nodes.length - 1, makeCmp(qsize2));
            if (j != -1 && Nodes[j].total == qsize2 && isParent(j, i)) {
                min = Math.min(min, size * 2 - qsize2);
                continue;
            }
            long qsize3 = size;
            j = binSearch(Nodes, i + 1, Nodes.length - 1, makeCmp(qsize3));
            if (j != -1 && Nodes[j].total == qsize3 && !isParent(j, i)) {
                min = Math.min(min, size - (Root.total - size * 2));
            }
        }
        return min == Long.MAX_VALUE? -1: min;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Balanced_Forest.class.getResourceAsStream("Balanced_Forest.in"));
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            Nodes = new Node[scanner.nextInt()];
            Arrays.setAll(Nodes, v -> new Node());
            for (int j = 0; j < Nodes.length; j++) {
                Nodes[j].c = scanner.nextLong();
                Nodes[j].total = Nodes[j].c;
            }
            for (int j = 0; j < Nodes.length - 1; j++) {
                int v = scanner.nextInt() - 1;
                int u = scanner.nextInt() - 1;
                Nodes[v].adj.add(Nodes[u]);
                Nodes[u].adj.add(Nodes[v]);
            }
            Root = Nodes[0];
            System.out.println(solve());
        }
        scanner.close();
    }

}
