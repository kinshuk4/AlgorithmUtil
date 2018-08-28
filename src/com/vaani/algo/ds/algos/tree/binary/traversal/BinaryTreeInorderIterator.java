package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.Stack;


/*
 *	Binary tree in-order iterator.
 *	
 *	Space cost: O(logN) in average.I
 *	Time cost: O(logN) in average.
 *
 */
public class BinaryTreeInorderIterator {

    private Stack<BinaryTreeNode> stack;

    public BinaryTreeInorderIterator(BinaryTreeNode node) {
        this.stack = new Stack<BinaryTreeNode>();
        BinaryTreeNode cur = node;
        while (cur != null) {
            this.stack.push(node);
            node = node.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public BinaryTreeNode next() {
        if (stack.isEmpty()) {
            return null;
        }
        BinaryTreeNode cur = stack.pop();
        BinaryTreeNode node = cur.right;
        while (node != null) {
            this.stack.push(node);
            node = node.left;
        }
        return cur;
    }
}
