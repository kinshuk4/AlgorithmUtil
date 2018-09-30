package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

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
 */
public class LevelOrderTraversal2 {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrderBottom(BinaryTreeNode root) {
        if (root == null) return result;

        Queue<BinaryTreeNode> queue1 = new LinkedList<BinaryTreeNode>();
        Queue<BinaryTreeNode> queue2 = new LinkedList<BinaryTreeNode>();
        List<Integer> level = new ArrayList<Integer>();
        queue1.add(root);

        while (!queue1.isEmpty()) {
            BinaryTreeNode<Integer> node = queue1.poll();
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

    public List<List<Integer>> levelOrderBottom2(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);
        int numNodesAtCurrLevel = 1;
        int nextLevel = 0;

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < numNodesAtCurrLevel; i++) {
                BinaryTreeNode<Integer> node = queue.poll();
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
            numNodesAtCurrLevel = nextLevel;
            nextLevel = 0;
        }
        return result;
    }
}
