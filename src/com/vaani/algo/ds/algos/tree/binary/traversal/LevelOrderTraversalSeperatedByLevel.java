package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
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
 * return its level order traversal as:
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 *
 */
public class LevelOrderTraversalSeperatedByLevel {

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(3);
        root.left = new BinaryTreeNode(9);
        root.right = new BinaryTreeNode(20);
        root.right.left = new BinaryTreeNode(15);
        root.right.right = new BinaryTreeNode(7);

        LevelOrderTraversalSeperatedByLevel test = new LevelOrderTraversalSeperatedByLevel();
        for (List<Integer> level : test.levelOrder(root)) {
            System.out.println(level);
        }

    }

    public static List<List<Integer>> levelOrder(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                BinaryTreeNode<Integer> node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(level);
        }
        return result;
    }

    public List<List<Integer>> levelOrder2(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);

        int currentLevel = 1;
        int nextLevel = 0;
        List<Integer> level = new ArrayList<Integer>();

        while (!queue.isEmpty()) {
            BinaryTreeNode<Integer> node = queue.poll();
            currentLevel--;
            level.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                nextLevel++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevel++;
            }

            if (currentLevel == 0) {
                currentLevel = nextLevel;
                nextLevel = 0;
                result.add(level);
                level = new ArrayList<Integer>();
            }
        }
        return result;
    }
}
