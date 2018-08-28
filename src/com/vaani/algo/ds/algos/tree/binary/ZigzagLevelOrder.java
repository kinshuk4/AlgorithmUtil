package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.*;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
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
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * Created by Xiaomeng on 8/7/2014.
 */
public class ZigzagLevelOrder {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    public List<List<Integer>> zigzagLevelOrder(BinaryTreeNode root) {
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
        for (int i = 1; i < result.size(); i += 2) {
            Collections.reverse(result.get(i));
        }
        return result;
    }

    /**
     * Two IStack Solution
     */
    public List<List<Integer>> zigzagLevelOrder2(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        Stack<BinaryTreeNode> currLevel = new Stack<BinaryTreeNode>();
        Stack<BinaryTreeNode> nextLevel = new Stack<BinaryTreeNode>();

        currLevel.add(root);
        boolean leftToRight = true;

        List<Integer> level = new ArrayList<Integer>();
        while (!currLevel.isEmpty()) {
            BinaryTreeNode<Integer> node = currLevel.pop();
            level.add(node.val);

            if (leftToRight) {
                if (node.left != null) nextLevel.push(node.left);
                if (node.right != null) nextLevel.push(node.right);
            } else {
                if (node.right != null) nextLevel.push(node.right);
                if (node.left != null) nextLevel.push(node.left);
            }

            if (currLevel.isEmpty()) {
                result.add(level);
                level = new ArrayList<Integer>();
                currLevel = nextLevel;
                nextLevel = new Stack<BinaryTreeNode>();
                leftToRight = !leftToRight;
            }
        }
        return result;
    }
}
