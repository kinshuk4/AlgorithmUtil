package com.vaani.algo.ds.algos.tree.bst;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.Stack;

/**
 * Write a function that takes 2 arguments: a binary tree and an integer n, it should return the n-th element in the inorder traversal of the binary tree
 */
public class NthElementInorderTraversal {
    int order = 0;

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode<Integer>(5);
        root.left = new BinaryTreeNode<Integer>(4);
        root.left.left = new BinaryTreeNode<Integer>(2);
        root.left.right = new BinaryTreeNode<Integer>(1);
        root.right = new BinaryTreeNode<Integer>(3);
        root.right.right = new BinaryTreeNode<Integer>(6);


        System.out.println(getNthIterative(root, 2));
    }

    public int getNth(BinaryTreeNode<Integer> node, int n) {
        if (node == null) return -1;
        int left = getNth(node.left, n);
        if (left != -1) return left;
        order++;
        if (order == n) return node.val;
        int right = getNth(node.right, n);
        return right;
    }

    public static int getNthIterative(BinaryTreeNode<Integer> root, int n) {
        if (root == null) {
            return -1;
        }
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();

        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                BinaryTreeNode<Integer> node = stack.pop();
                n--;
                if (n == 0) {
                    return node.val;
                }
                root = node.right;
            }
        }
        return -1;
    }
}
