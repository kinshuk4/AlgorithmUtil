package com.vaani.algo.trees;

import java.util.Stack;

import com.vaani.algo.ds.utils.TreeNode;

public class DFSonTree {
    static Stack<TreeNode> stack = new Stack<>();

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
        l3.left = new TreeNode(14);
        r2.right = r3;

        TreeNode r4 = new TreeNode(88);
        r1.right = r4;
        dfs(root);
    }

    public static void dfs(TreeNode root) {
        if (null == root) {
            return;
        }
        root.isVisited = true;
        stack.push(root);

        if (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.print(pop.data + "\t");
            if (pop.left != null && !pop.left.isVisited) {
                dfs(pop.left);
            }
            if (pop.right != null && !pop.right.isVisited) {
                dfs(pop.right);
            }
        }
}
}
