package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by kchandra on 18/08/16.
 */
public class BfsOnTree {
// @formatter:off
    /**
     * Tree=
     *            1
     *         3    5
     *      9          88
     *   11
     * 14  12
     *
     * @param args
     */
// @formatter:on
    public static void main(String[] args) {
        BinaryTreeNode root = DfsOnTree.getABinaryTree();

        bfsIterative(root);
    }

    public static void bfsIterative(BinaryTreeNode root) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        System.out.println(root);
        root.isVisited = true;
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.remove();

            BinaryTreeNode child = DfsOnTree.getUnvisitedChildNode(node);

            while (child != null) {
                child.isVisited = true;
                System.out.println(child);
                queue.add(child);
            }

        }

    }
}
