package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * <p>
 * Created by Xiaomeng on 8/11/2014.
 */
public class MinimumDepthBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
//        root.left = new BinaryTreeNode(2);

        MinimumDepthBinaryTree test = new MinimumDepthBinaryTree();
        System.out.println(test.minDepth(root));
    }

    /**
     * DFS
     */
    public int minDepth(BinaryTreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * Level-first traversal
     */
    public int minDepth2(BinaryTreeNode root) {
        if (root == null) return 0;
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                BinaryTreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return level;
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            level++;
        }
        return 0;
    }
}
