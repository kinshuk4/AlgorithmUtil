package com.vaani.algo.ds.algos.trie;

import com.vaani.algo.ds.core.tree.Trie;
import com.vaani.algo.ds.core.tree.TrieNode;

import java.util.Map;

/**
 * Created by Xiaomeng on 11/12/2014.
 */
public class LongestPrefixMatching {
    public static String getMatchingPrefix(String input, Trie dict) {
        String result = ""; // Initialize resultant string
        int length = input.length();  // Find length of the input string

        // Initialize reference to traverse through Trie
        TrieNode crawl = dict.root;

        // Iterate through all characters of input string 'str' and traverse
        // down the Trie
        int level, prevMatch = 0;
        for (level = 0; level < length; level++) {
            // Find current character of str
            char ch = input.charAt(level);

            // HashMap of current Trie node to traverse down
            Map<Character, TrieNode> child = crawl.children;

            // See if there is a Trie edge for the current character
            if (child.containsKey(ch)) {
                result += ch;          //Update result
                crawl = child.get(ch); //Update crawl to move down in Trie

                // If this is end of a word, then update prevMatch
                if (crawl.isWord)
                    prevMatch = level + 1;
            } else break;
        }

        // If the last processed character did not match end of a word,
        // return the previously matching prefix
        if (!crawl.isWord)
            return result.substring(0, prevMatch);

        else return result;
    }

    public static void main(String[] args) {
        Trie dict = new Trie();
        dict.insert("are");
        dict.insert("area");
        dict.insert("base");
        dict.insert("cat");
        dict.insert("cater");
        dict.insert("basement");

        String input = "caterer";
        System.out.print(input + ":   ");
        System.out.println(getMatchingPrefix(input, dict));

        input = "basement";
        System.out.print(input + ":   ");
        System.out.println(getMatchingPrefix(input, dict));
    }
}
