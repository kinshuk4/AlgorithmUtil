package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Ftrees%2FTraverseBinaryTree.java
public class BinaryTreeTraversal {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode l1 = new BinaryTreeNode(3);
        BinaryTreeNode l2 = new BinaryTreeNode(9);
        BinaryTreeNode l3 = new BinaryTreeNode(11);
        BinaryTreeNode r1 = new BinaryTreeNode(5);
        BinaryTreeNode r2 = new BinaryTreeNode(12);
        BinaryTreeNode r3 = new BinaryTreeNode(13);
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

    public static void preOrder(BinaryTreeNode root) {
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

    public static void inOrder(BinaryTreeNode root) {
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

    public static void postOrder(BinaryTreeNode root) {
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
