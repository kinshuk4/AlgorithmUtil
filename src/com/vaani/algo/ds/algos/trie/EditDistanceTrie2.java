package com.vaani.algo.ds.algos.trie;

import com.vaani.algo.ds.core.tree.Trie;
import com.vaani.algo.ds.core.tree.TrieNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all words from a dictionary that are x edit distance away
 */
public class EditDistanceTrie2 {
    public static void main(String[] args) {
        EditDistanceTrie2 test = new EditDistanceTrie2();
        Trie dict = new Trie();
        dict.insert("arb");
        dict.insert("area");
        dict.insert("brc");
        dict.insert("eafr");
        System.out.println(test.getEditDistance("ar", 2, dict));
    }

    /**
     * Edit Distance Brute Force:
     * For each word, we have to fill in an N x M table. An upper bound for the runtime is O( <number of words> * <max word length> ^2 )
     * <p>
     * Trie getTreeHeight:
     * We create at most one row of the table for each node in the trie.
     * The upper bound for the runtime is O(<max word length> * <number of nodes in the trie>).
     * For most dictionaries, considerably less than O(<number of words> * <max word length>^2)
     */
    public List<String> getEditDistance(String word, int k, Trie theTrie) {
        int len = word.length();
        int[] currRow = new int[len + 1];
        for (int i = 0; i < len + 1; i++) {
            currRow[i] = i;
        }
        List<String> result = new ArrayList<String>();
        StringBuilder single = new StringBuilder();
        TrieNode root = theTrie.root;
        for (Character ch : root.children.keySet()) {
            traverseTrie(root.getChild(ch), ch, word, currRow, k, theTrie, single, result);
            single.deleteCharAt(single.length() - 1);
        }
        return result;
    }

    private void traverseTrie(TrieNode node, char letter, String word, int[] prevRow, int k, Trie theTrie, StringBuilder single, List<String> result) {
        int len = prevRow.length;
        int[] currRow = new int[len];
        currRow[0] = prevRow[0] + 1;

        for (int i = 1; i < len; i++) {
            if (letter == word.charAt(i - 1)) {
                currRow[i] = prevRow[i - 1];
            } else {
                currRow[i] = Math.min(Math.min(currRow[i - 1], prevRow[i]), prevRow[i - 1]) + 1;
            }
        }
        single.append(letter);
        if (node.isWord) {
            if (currRow[len - 1] == k) {
                result.add(single.toString());
            }
        }

        for (Character ch : node.children.keySet()) {
            traverseTrie(node.getChild(ch), ch, word, currRow, k, theTrie, single, result);
            single.deleteCharAt(single.length() - 1);
        }
    }
}
