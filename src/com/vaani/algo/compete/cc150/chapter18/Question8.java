package com.vaani.algo.compete.cc150.chapter18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Given a string s and an array of smaller strings T, design a method to search
 * s for each small string in T.
 */
// O(n) space, O(logn) time
public class Question8 {

    /**
     * Return the start indices of all the strings in T found in s. If not found,
     * the index is -1.
     *
     * @param s
     * @param T
     * @return
     */
    public int[] searchAll(String s, String[] T) {
        // write implementation here
        SuffixTree tree = new SuffixTree(s);
        List<Integer> list = new ArrayList<Integer>();
        for (String t : T) {
            list.add(tree.search(t));
        }
        int[] results = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            results[i] = list.get(i);
        }
        return results;
    }

    public static class SuffixNode {
        public char ch;
        public Map<Character, SuffixNode> children;
        public int index;  // denotes the end index if a word terminated as this node

        public SuffixNode(char ch) {
            this.ch = ch;
            this.index = -1;
            this.children = new HashMap<Character, SuffixNode>();
        }
    }

    public static class SuffixTree {
        public SuffixNode root;

        public SuffixTree(String str) {
            this.root = new SuffixNode('#');
            for (int i = 0; i < str.length(); ++i) {
                insert(root, str.substring(i), i);
            }
        }

        public void insert(SuffixNode curNode, String suffix, int index) {
            if (suffix.length() == 0) {
                return;
            }
            char ch = suffix.charAt(0);
            SuffixNode node = curNode.children.get(ch);
            if (node == null) {
                node = new SuffixNode(ch);
                // each node record the end index
                node.index = index;
                curNode.children.put(ch, node);
            }
            insert(node, suffix.substring(1), index + 1);
        }

        public int search(String s) {
            return search(s, s, root);
        }

        public int search(String original, String s, SuffixNode curNode) {
            if (s.length() == 0) {
                return curNode.index + 1 - original.length();
            }
            char ch = s.charAt(0);
            SuffixNode node = curNode.children.get(ch);
            if (node == null) {
                return -1;
            } else {
                return search(original, s.substring(1), node);
            }
        }

    }

}

