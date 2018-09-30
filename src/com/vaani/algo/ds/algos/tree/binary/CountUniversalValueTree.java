package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

public class CountUniversalValueTree {
    static boolean isUnivalTree(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return true;
        }

        boolean left = isUnivalTree(root.left);
        boolean right = isUnivalTree(root.right);

        // If any of the subtrees is not singly, then this
        // cannot be singly.
        if (!left || !right) {
            return false;
        }

        // If left subtree is singly and non-empty, but data
        // doesn't match
        if (root.left != null && !root.val.equals(root.left.val)) {
            return false;
        }

        // Same for right subtree
        if (root.right != null && !root.val.equals(root.right.val)) {
            return false;
        }

        return true;
    }

    int countUnivalTreeBad(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        int totalCount = countUnivalTreeBad(root.left) + countUnivalTreeBad(root.right);
        if (isUnivalTree(root)) {
            totalCount += 1;
        }
        return totalCount;
    }

    class Counter {
        int count = 0;
    }


    // This function increments count by number of single
    // valued subtrees under root. It returns true if subtree
    // under root is Singly, else false.
    boolean countUnivalTreeHelper(BinaryTreeNode<Integer> root, Counter c) {
        // Return false to indicate NULL
        if (root == null)
            return true;

        // Recursively count in left and right subtrees also
        boolean left = countUnivalTreeHelper(root.left, c);
        boolean right = countUnivalTreeHelper(root.right, c);

        // If any of the subtrees is not singly, then this
        // cannot be singly.
        if (!left || !right) {
            return false;
        }

        // If left subtree is singly and non-empty, but data
        // doesn't match
        if (root.left != null && !root.val.equals(root.left.val)) {
            return false;
        }


        // Same for right subtree
        if (root.right != null && !root.val.equals(root.right.val)) {
            return false;
        }

        // If none of the above conditions is true, then
        // tree rooted under root is single valued, increment
        // count and return true.
        c.count++;
        return true;
    }

    int countUnivalTree(BinaryTreeNode<Integer> root) {
        Counter ct = new Counter();
        // Recursive function to count
        countUnivalTreeHelper(root, ct);
        return ct.count;
    }
}
