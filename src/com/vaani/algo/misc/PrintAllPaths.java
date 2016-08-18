package com.vaani.algo.misc;

import com.vaani.algo.ds.core.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, print all paths from root to leaf
 */
public class PrintAllPaths {
    /**
     * Recursive
     * Time: O(n)
     * Space: O(n)
     */
    public static void printAllPaths(TreeNode root) {
        int[] path = new int[1000];
        printAllPaths(root, path, 0);
    }

    public static void printAllPaths(TreeNode root, int[] path, int pathLen) {
        if (root == null) return;

        path[pathLen] = root.val;
        pathLen++;

        if (root.left == null && root.right == null) {
            printPaths(path, pathLen);
        } else {
            printAllPaths(root.left, path, pathLen);
            printAllPaths(root.right, path, pathLen);
        }
    }

    public static void printPaths(int[] path, int pathLen) {
        for (int i = 0; i < pathLen; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println();
    }

    /**
     * Iterative: Level-order traversal
     */
    public static void printAllPathToLeafNonRecursive(TreeNode root) {
        if (root == null) return;

        Queue<Object> q = new LinkedList<Object>();
        q.add(root);
        q.add(root.val + "");

        while (!q.isEmpty()) {
            TreeNode head = (TreeNode) q.poll();
            String headPath = (String) q.poll();

            if (head.left == null && head.right == null) {
                System.out.println(headPath);
                continue;
            }

            if (head.left != null) {
                String leftStr = headPath + "->" + head.left.val;
                q.add(head.left);
                q.add(leftStr);
            }

            if (head.right != null) {
                String rightStr = headPath + "->" + head.right.val;
                q.add(head.right);
                q.add(rightStr);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(2);
        printAllPaths(root);
        System.out.println();
        printAllPathToLeafNonRecursive(root);
    }
}
