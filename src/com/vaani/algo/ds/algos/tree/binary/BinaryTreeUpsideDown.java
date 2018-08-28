package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

/**
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty,
 * flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 * <p>
 * For example:
 * Given a binary tree {1,2,3,4,5},
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * return the root of the binary tree [4,5,2,#,#,3,1].
 * 4
 * / \
 * 5   2
 * / \
 * 3   1
 */
public class BinaryTreeUpsideDown {
    public static BinaryTreeNode UpsideDownBinaryTree(BinaryTreeNode root) {
        if (root == null || root.left == null) return root;

        BinaryTreeNode leftRoot = UpsideDownBinaryTree(root.left);
        BinaryTreeNode node = leftRoot;
        while (node.right != null) {
            node = node.right;
        }

        node.left = root.right;
        node.right = root;
        root.left = null;
        root.right = null;
        return leftRoot;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        UpsideDownBinaryTree(root).display();
        //root.display();
    }
}
