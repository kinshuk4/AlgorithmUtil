package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.TreeNode;

import java.util.Stack;


/*
 *	Binary tree in-order iterator.
 *	
 *	Space cost: O(logN) in average.I
 *	Time cost: O(logN) in average.
 *
 */
public class BinaryTreeInorderIterator {

    private Stack<TreeNode> stack;

    public BinaryTreeInorderIterator(TreeNode node) {
        this.stack = new Stack<TreeNode>();
        TreeNode cur = node;
        while (cur != null) {
            this.stack.push(node);
            node = node.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public TreeNode next() {
        if (stack.isEmpty()) {
            return null;
        }
        TreeNode cur = stack.pop();
        TreeNode node = cur.right;
        while (node != null) {
            this.stack.push(node);
            node = node.left;
        }
        return cur;
    }
}
