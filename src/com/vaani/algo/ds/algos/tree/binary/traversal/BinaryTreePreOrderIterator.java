package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.Stack;

public class BinaryTreePreOrderIterator {


    private Stack<BinaryTreeNode> stack;
    private BinaryTreeNode current;

    public BinaryTreePreOrderIterator(BinaryTreeNode node) {
        stack = new Stack<BinaryTreeNode>();
        stack.push(node);
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public BinaryTreeNode next() {
        if (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();

            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);

            return node;
        } else {
            return null;
        }
    }

}
