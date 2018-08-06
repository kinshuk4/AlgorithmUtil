package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class LevelOrderTraversal1 {
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
        levelOrderTraversal(root);

        List<Integer> traversal = levelOrderTraversal(root);
    }

    public static <T> List<T> levelOrderTraversal(TreeNode<T> root) {
        List<T> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> remove = queue.remove();
            result.add(remove.val);
            if (remove.left != null) {
                queue.add(remove.left);
            }
            if (remove.right != null) {
                queue.add(remove.right);
            }

        }
        return result;
    }


}