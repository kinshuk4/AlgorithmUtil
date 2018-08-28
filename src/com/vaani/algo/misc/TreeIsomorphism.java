package com.vaani.algo.misc;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Check if two binary trees are Isomorphic
 * <p>
 * Write a function to detect if two trees are isomorphic.
 * Two trees are called isomorphic if one of them can be obtained from other by a series of flips, i.e. by swapping left and right children of a number of nodes.
 * Any number of nodes at any level can have their children swapped. Two empty trees are isomorphic.
 */
public class TreeIsomorphism {
    /**
     * Reference: http://www.geeksforgeeks.org/tree-isomorphism-problem/
     * Time complexity??
     */
    public static boolean isIsomorphic(BinaryTreeNode p, BinaryTreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p == null || q == null || p.val != q.val)
            return false;
        else {
            return (isIsomorphic(p.left, q.left) && isIsomorphic(p.right, q.right))
                    || (isIsomorphic(p.left, q.right) && isIsomorphic(p.right, q.left));
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root1 = new BinaryTreeNode(1);
        root1.left = new BinaryTreeNode(2);
        root1.left.left = new BinaryTreeNode(4);
        root1.left.right = new BinaryTreeNode(5);
        root1.left.right.left = new BinaryTreeNode(7);
        root1.left.right.right = new BinaryTreeNode(8);
        root1.right = new BinaryTreeNode(3);
        root1.right.left = new BinaryTreeNode(6);

        BinaryTreeNode root2 = new BinaryTreeNode(1);
        root2.left = new BinaryTreeNode(3);
        root2.left.right = new BinaryTreeNode(6);
        root2.right = new BinaryTreeNode(2);
        root2.right.left = new BinaryTreeNode(4);
        root2.right.right = new BinaryTreeNode(5);
        root2.right.right.left = new BinaryTreeNode(8);
        root2.right.right.right = new BinaryTreeNode(7);

        System.out.println(isIsomorphic(root1, root2));
    }
}
