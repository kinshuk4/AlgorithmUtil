package com.vaani.algo.ds.tree.binary.traversal;

import com.vaani.algo.ds.core.TreeNode;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeCombinedIterator {
    private TreeNode root;
    private Stack<TreeNode> stack;
    private Stack<TreeNode> preOrderStack;
    private Queue<TreeNode> postOrderQueue;

    public BinaryTreeCombinedIterator(TreeNode n) {
        root = n;
        stack = new Stack<TreeNode>();
        pushLeft(root);

        // for post-order, no need for in-order
        postOrderQueue = new LinkedList<TreeNode>();
        pushPostOrder(root);
        // for pre-order
        preOrderStack = new Stack<TreeNode>();
        preOrderStack.push(root);
    }

    //in-order
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public void reset() {
        stack.empty();
        postOrderQueue.clear();
        pushLeft(root);
    }

    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

    public TreeNode next() {
        return inOrderNext();
    }

    // in-order : left, root, right
    public TreeNode inOrderNext() {
        if (hasNext()) {
            TreeNode node = stack.pop();
            pushLeft(node.right);
            return node;
        } else {
            throw new NoSuchElementException();
        }
    }

    private void pushLeft(TreeNode node) {
        if (node != null) {
            stack.push(node);
            pushLeft(node.left);
        }
    }

    public boolean hasNextPreOrder() {
        return !preOrderStack.isEmpty();
    }

    //pre-order: root, left, right
    public TreeNode preOrderNext() {
        if (!preOrderStack.isEmpty()) {
            TreeNode node = preOrderStack.pop();

            if (node.right != null)
                preOrderStack.push(node.right);
            if (node.left != null)
                preOrderStack.push(node.left);

            return node;
        } else {
            return null;
        }
    }

    public void pushPostOrder(TreeNode n) {
        if (n == null)
            return;
        if (n.left != null)
            pushPostOrder(n.left);
        if (n.right != null)
            pushPostOrder(n.right);

        postOrderQueue.add(n);
    }

    public boolean hasNextPostOrder() {
        return !postOrderQueue.isEmpty();
    }

    //post-order: left, right, root
    public TreeNode postOrderNext() {
        if (!postOrderQueue.isEmpty()) {
            return postOrderQueue.poll();
        } else {
            throw new NoSuchElementException();
        }
    }
}
