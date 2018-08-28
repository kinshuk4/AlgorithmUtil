package com.vaani.algo.compete.cc150.chap4treegraph;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * You have two very large binary trees: T1, with millions of nodes, and T2,
 * with hundreds of nodes. Create an algorithm to decide if T2 is a subtree of
 * T1. A tree T2 is a subtree of T1 if there exists a node n in T1 such that the
 * subtree of n is identical to T2. That is, if you cut off the tree at node n,
 * the two trees would be identical.
 */
// O(1) space, O(mn) time
public class Question8 {

    public boolean isSub(BinaryTreeNode haystack, BinaryTreeNode needle) {
        if (haystack == null) {
            return false;
        }
        if (needle == null) {
            return true;
        }
        if (haystack.val == needle.val) { // root match
            if (match(haystack, needle)) {
                return true;
            }
        }
        return isSub(haystack.left, needle) || isSub(haystack.right, needle);
    }

    private boolean match(BinaryTreeNode haystack, BinaryTreeNode needle) {
        if (haystack == null && needle == null) {
            return true;
        }
        if (haystack.val != needle.val) {
            return false;
        }
        // compare left and right
        return match(haystack.left, needle.left) && match(haystack.right, needle.right);
    }


}
