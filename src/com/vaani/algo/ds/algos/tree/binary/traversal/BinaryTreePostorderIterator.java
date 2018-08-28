package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.Stack;

public class BinaryTreePostorderIterator {


    private Stack<BinaryTreeNode> stack;
    private BinaryTreeNode current;

    public BinaryTreePostorderIterator(BinaryTreeNode node) {
        stack = new Stack<BinaryTreeNode>();
        BinaryTreeNode cur = node;
        while (node != null) {
            stack.push(node);
            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public BinaryTreeNode next() {
        BinaryTreeNode node = stack.pop();
        BinaryTreeNode ret = node;

        if (!stack.isEmpty()) {
            BinaryTreeNode prev = stack.peek();
            if (prev.left == node) { // current is left child
                node = prev.right;
                while (node != null) {
                    stack.push(node);
                    if (node.left != null) {
                        node = node.left;
                    } else {
                        node = node.right;
                    }
                }
            }
        }

        return ret;
    }

}
