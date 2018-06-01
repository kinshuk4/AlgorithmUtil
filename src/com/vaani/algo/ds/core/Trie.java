package com.vaani.algo.ds.core;

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

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            TrieNode child = node.getChild(c);
            if (child == null)
                return false;
            node = child;
        }
        if (node.isWord) {
            return true;
        } else {
            return false;
        }
    }

    public boolean startsWith(String prefix){
        TrieNode node = root;
        for(char c: prefix.toCharArray()){
            TrieNode child = node.getChild(c);
            if(child==null)
                return false;
            node = child;
        }
        return true;
    }
}
