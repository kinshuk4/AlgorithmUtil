package com.vaani.algo.ds.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.TreeNode;

import java.util.Stack;

public class BinaryTreePreOrderIterator {


    private Stack<TreeNode> stack;
    private TreeNode current;

    public BinaryTreePreOrderIterator(TreeNode node) {
        stack = new Stack<TreeNode>();
        stack.push(node);
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public TreeNode next() {
        if (!stack.isEmpty()) {
            TreeNode node = stack.pop();

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
