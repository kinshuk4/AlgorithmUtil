package com.vaani.algo.ds.tree.binary;

import com.vaani.algo.ds.core.TreeNode;

/**
 *  Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty,
 *  flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 *
 *  For example:
 *  Given a binary tree {1,2,3,4,5},
 *
 *          1
 *         / \
 *        2   3
 *       / \
 *      4   5
 *
 * return the root of the binary tree [4,5,2,#,#,3,1].
 *          4
 *         / \
 *        5   2
 *           / \
 *          3   1
 *
 */
public class BinaryTreeUpsideDown {
    public static TreeNode UpsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null) return root;

        TreeNode leftRoot = UpsideDownBinaryTree(root.left);
        TreeNode node = leftRoot;
        while(node.right != null){
            node = node.right;
        }

        node.left = root.right;
        node.right = root;
        root.left = null;
        root.right = null;
        return leftRoot;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        UpsideDownBinaryTree(root).display();
        //root.display();
    }
}
