package com.vaani.algo.ds.tree.bst;

import com.vaani.algo.ds.core.TreeNode;

import java.util.Stack;

/**
 * Write a function that takes 2 arguments: a binary tree and an integer n, it should return the n-th element in the inorder traversal of the binary tree
 */
public class NthElementInorderTraversal {
    int order = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);

        NthElementInorderTraversal test = new NthElementInorderTraversal();

        System.out.println(test.getNthIterative(root, 2));
    }

    public int getNth(TreeNode<Integer> node, int n) {
        if (node == null) return -1;
        int left = getNth(node.left, n);
        if (left != -1) return left;
        order++;
        if (order == n) return node.val;
        int right = getNth(node.right, n);
        return right;
    }

    public int getNthIterative(TreeNode<Integer> root, int n) {
        if (root == null) return -1;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode<Integer> node = stack.pop();
                n--;
                if (n == 0) return node.val;
                root = node.right;
            }
        }
        return -1;
    }
}
