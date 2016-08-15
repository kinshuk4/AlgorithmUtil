package com.vaani.algo.trees.binary.traversal;

import com.vaani.algo.trees.binary.ds.TreeNode;

//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Ftrees%2FTraverseBinaryTree.java
public class BinaryTreeTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l1 = new TreeNode(3);
        TreeNode l2 = new TreeNode(9);
        TreeNode l3 = new TreeNode(11);
        TreeNode r1 = new TreeNode(5);
        TreeNode r2 = new TreeNode(12);
        TreeNode r3 = new TreeNode(13);
        root.left = l1;
        root.right = r1;
        l1.left = l2;
        l2.left = l3;
        l3.right = r2;
        r2.right = r3;
        preOrder(root);
        inOrder(root);
        postOrder(root);
    }

    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + "\t");
        if (root.left != null) {
            preOrder(root.left);
        }
        if (root.right != null) {
            preOrder(root.right);
        }
    }

    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inOrder(root.left);
        }
        System.out.print(root.val + "\t");
        if (root.right != null) {
            inOrder(root.right);
        }
    }

    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            postOrder(root.left);
        }
        if (root.right != null) {
            postOrder(root.right);
        }
        System.out.print(root.val + "\t");
}
}
