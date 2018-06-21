package com.vaani.algo.ds.tree.binary;

import com.vaani.algo.ds.core.tree.TreeNode;

import java.util.*;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * <p>
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * return its bottom-up level order traversal as:
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 * <p>
 * Created by Xiaomeng on 8/7/2014.
 */
public class LevelOrderTraversal2 {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return result;

        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        List<Integer> level = new ArrayList<Integer>();
        queue1.add(root);

        while (!queue1.isEmpty()) {
            TreeNode<Integer> node = queue1.poll();
            level.add(node.val);

            if (node.left != null) queue2.add(node.left);
            if (node.right != null) queue2.add(node.right);

            if (queue1.isEmpty()) {
                result.add(level);
                level = new ArrayList<Integer>();
                queue1.addAll(queue2);
                queue2.clear();
            }
        }
        Collections.reverse(result);
        return result;
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int currLevel = 1;
        int nextLevel = 0;

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < currLevel; i++) {
                TreeNode<Integer> node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                    nextLevel++;
                }

                if (node.right != null) {
                    queue.add(node.right);
                    nextLevel++;
                }
            }
            result.add(0, level);
            currLevel = nextLevel;
            nextLevel = 0;
        }
        return result;
    }
}
