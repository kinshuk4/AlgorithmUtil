package com.vaani.algo.tree.binary.traversal;

import java.util.ArrayDeque;
import java.util.Queue;

import com.vaani.algo.tree.binary.ds.TreeNode;


public class LevelOrderTraversal1 {
    public static void main(String[] args) {
        Queue queue = new ArrayDeque<>();
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
        levelTraverse(root, queue);
    }

    public static void levelTraverse(TreeNode root, Queue<TreeNode> queue) {
        if (root == null) {
            return;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode remove = queue.remove();
            System.out.print(remove.val + "\t");
            if (remove.left != null) {
                queue.add(remove.left);
            }
            if (remove.right != null) {
                queue.add(remove.right);
            }

        }
    }
    
    
}