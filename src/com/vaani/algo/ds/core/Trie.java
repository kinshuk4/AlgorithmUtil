package com.vaani.algo.ds.core;

/**
 * Created by Xiaomeng on 11/12/2014.
 */
public class Trie {
    public TrieNode root;
    public int minLevDist;

    public Trie() {
        this.root = new TrieNode(' ');
    }

    public void insert(String word) {
        int length = word.length();
        TrieNode current = this.root;

        if (length == 0) {
            current.isWord = true;
        }

        for (int index = 0; index < length; index++) {

            char letter = word.charAt(index);
            TrieNode child = current.getChild(letter);

            if (child != null) {
                current = child;
            } else {
                current.children.put(letter, new TrieNode(letter));
                current = current.getChild(letter);
            }
            if (index == length - 1) {
                current.isWord = true;
            }
        }
    }
}
