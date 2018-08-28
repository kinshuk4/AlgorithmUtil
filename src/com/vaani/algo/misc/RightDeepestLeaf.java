package com.vaani.algo.misc;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;



/**
 * Find the right deepest leaf.
 * If two or more nodes have the same depth, pick the right one.
 */
public class RightDeepestLeaf {

    public BinaryTreeNode findRightDeepestLeaf(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.offer(root);

        BinaryTreeNode cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        return cur;
    }
}
