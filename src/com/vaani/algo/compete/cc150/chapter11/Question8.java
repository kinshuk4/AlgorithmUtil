package com.vaani.algo.compete.cc150.chapter11;

/**
 * Imagine you are reading in a stream of integers. Periodically, you wish to be
 * able to look up the rank of a number x (the number of values less than or
 * equal to x). Implement the data structures and algorithms to support these
 * operations. That is, implement the method track(int x), which is called when
 * each number is generated, and the method getRankOfNumber(int x), which
 * returns the number of values less than or equal to x (not including x
 * itself).
 */
// O(n) space, O(n) time
public class Question8 {

    // you can also put your data structures here

    private BST bst = new BST();

    public void track(int x) {
        // write implementation here
        bst.insert(x);
    }

    public int getRankOfNumber(int x) {
        // write implementation here
        return bst.rank(x);
    }

    public static class BST {
        private Node root;

        private int size(Node node) {
            return node != null ? node.N : 0;
        }

        public void insert(int val) {
            root = insert(root, val);
        }

        private Node insert(Node node, int val) {
            if (node == null) {
                return new Node(val, 1);
            }
            if (node.val >= val) {
                node.left = insert(node.left, val);
            } else {
                node.right = insert(node.right, val);
            }
            node.N = 1 + size(node.left) + size(node.right);
            return node;
        }

        public int rank(int val) {
            return rank(root, val);
        }

        private int rank(Node node, int val) {
            if (node == null) {
                return 0;
            }
            if (val < node.val) {
                return rank(node.left, val);
            } else if (val > node.val) {
                return 1 + size(node.left) + rank(node.right, val);
            } else {
                return size(node.left);
            }
        }
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public int N;  // number of elems in subtree

        public Node(int val, int N) {
            this.val = val;
            this.N = N;
        }
    }

}

