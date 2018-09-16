package com.vaani.algo.compete.codesignal.interviewpractice.hashtable;

import com.vaani.algo.ds.core.graph.disjoint.DisjointSet;

import java.util.*;
//https://medium.com/@andrey.khayrutdinov/find-the-largest-string-permutation-given-allowed-swaps-c5150cd5ad88
/**
 * Given a string str and array of pairs that indicates which indices in the string can be swapped, return the lexicographically largest string that results from doing the allowed swaps. You can swap indices any number of times.
 *
 * Example
 *
 * For str = "abdc" and pairs = [[1, 4], [3, 4]], the output should be
 * swapLexOrder(str, pairs) = "dbca".
 *
 * By swapping the given indices, you get the strings: "cbda", "cbad", "dbac", "dbca". The lexicographically largest string in this list is "dbca".
 */
public class SwapLexOrder {
    static String swapLexOrder(String str, int[][] pairs) {
        List<int[]> swaps = new ArrayList<>(pairs.length);
        for(int[] pair: pairs){
            swaps.add(pair);
        }
        return next(str, swaps);
    }



    static String next(String s, List<int[]> swaps) {
        final int N = s.length();

        // 1a. Extract separate graphs.
        DisjointSet set = new DisjointSet(N);
        for (int[] swap : swaps) {
            set.union(swap[0]-1, swap[1]-1);
        }

        // 1b. Map the string’s characters to the appropriate adjacencyList.
        Map<Integer, List<Character>> graphs = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int head = set.find(i);
            List<Character> characters = graphs.computeIfAbsent(head, (dummy) -> new ArrayList<>());
            characters.add(s.charAt(i));
        }

        // 2. Within each graph, sort the characters in **ascending** order.
        for (List<Character> characters : graphs.values()) {
            characters.sort(null);
        }

        // 3. Populate the output by taking the characters from the graph.
        StringBuilder sb = new StringBuilder(N);
        for (int i = 0; i < N; i++) {
            // Since the lists are sorted in ascending order, take the last element.
            // This is similar to pop_back() function of std::vector.
            List<Character> characters = graphs.get(set.find(i));
            char currentMax = characters.remove(characters.size() - 1);
            sb.append(currentMax);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] swaps = {{1, 4}, {3, 4}};
        System.out.println(swapLexOrder("abdc", swaps));
    }
}
