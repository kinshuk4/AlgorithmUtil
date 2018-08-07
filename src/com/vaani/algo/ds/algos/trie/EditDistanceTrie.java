package com.vaani.algo.ds.algos.trie;


import com.vaani.algo.ds.core.tree.Trie;
import com.vaani.algo.ds.core.tree.TrieNode;

import java.util.ArrayList;

/**
 * Created by Xiaomeng on 11/12/2014.
 */
public class EditDistanceTrie {
    /**
     * Computes the minimum Levenshtein Distance between the given word (represented as an array of Characters) and the
     * words stored in theTrie. This algorithm is modeled after Steve Hanov's blog article "Fast and Easy Levenshtein
     * distance using a Trie" and Murilo Vasconcelo's revised version in C++.
     * <p>
     * http://stevehanov.ca/blog/index.php?id=114
     * http://murilo.wordpress.com/2011/02/01/fast-and-easy-levenshtein-distance-using-a-trie-in-c/
     *
     * @param word - the characters of an input word as an array representation
     * @return int - the minimum Levenshtein Distance
     */
    public static int computeMinimumLevenshteinDistance(ArrayList<Character> word, Trie theTrie) {

        theTrie.minLevDist = Integer.MAX_VALUE;

        int iWordLength = word.size();
        int[] currentRow = new int[iWordLength + 1];

        for (int i = 0; i <= iWordLength; i++) {
            currentRow[i] = i;
        }

//        for (int i = 0; i < iWordLength; i++) {
//            traverseTrie(theTrie.root, word.get(i), word, currentRow, theTrie);
//        }

        for (Character c : theTrie.root.children.keySet()) {
            traverseTrie(theTrie.root.children.get(c), c, word, currentRow, theTrie);
        }
        return theTrie.minLevDist;
    }

    /**
     * Recursive helper function. Traverses theTrie in search of the minimum Levenshtein Distance.
     *
     * @param node        - the current TrieNode
     * @param letter      - the current character of the current word we're working with
     * @param word        - an array representation of the current word
     * @param previousRow - a row in the Levenshtein Distance matrix
     */
    private static void traverseTrie(TrieNode node, char letter, ArrayList<Character> word, int[] previousRow, Trie theTrie) {

        int size = previousRow.length;
        int[] currentRow = new int[size];
        currentRow[0] = previousRow[0] + 1;

        int minimumElement = currentRow[0];
        int insertCost, deleteCost, replaceCost;

        for (int i = 1; i < size; i++) {

            insertCost = currentRow[i - 1] + 1;
            deleteCost = previousRow[i] + 1;

            if (word.get(i - 1) == letter) {
                replaceCost = previousRow[i - 1];
            } else {
                replaceCost = previousRow[i - 1] + 1;
            }

            currentRow[i] = Math.min(Math.min(insertCost, deleteCost), replaceCost);

            if (currentRow[i] < minimumElement) {
                minimumElement = currentRow[i];
            }
        }

        if (currentRow[size - 1] < theTrie.minLevDist && node.isWord) {
            theTrie.minLevDist = currentRow[size - 1];
        }

        if (minimumElement < theTrie.minLevDist) {

            for (Character c : node.children.keySet()) {
                traverseTrie(node.children.get(c), c, word, currentRow, theTrie);
            }
        }
    }

    public static void main(String[] args) {
        Trie dict = new Trie();
        dict.insert("arb");
        dict.insert("area");

        ArrayList<Character> word = new ArrayList<Character>();
        word.add('a');
        word.add('r');
        word.add('c');
        word.add('a');
        System.out.println(computeMinimumLevenshteinDistance(word, dict));
    }
}
