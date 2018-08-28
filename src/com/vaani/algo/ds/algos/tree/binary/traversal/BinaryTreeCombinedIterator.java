package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeCombinedIterator {
    private BinaryTreeNode root;
    private Stack<BinaryTreeNode> stack;
    private Stack<BinaryTreeNode> preOrderStack;
    private Queue<BinaryTreeNode> postOrderQueue;

    public BinaryTreeCombinedIterator(BinaryTreeNode n) {
        root = n;
        stack = new Stack<BinaryTreeNode>();
        pushLeft(root);

        // for post-order, no need for in-order
        postOrderQueue = new LinkedList<BinaryTreeNode>();
        pushPostOrder(root);
        // for pre-order
        preOrderStack = new Stack<BinaryTreeNode>();
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

    public BinaryTreeNode next() {
        return inOrderNext();
    }

    // in-order : left, root, right
    public BinaryTreeNode inOrderNext() {
        if (hasNext()) {
            BinaryTreeNode node = stack.pop();
            pushLeft(node.right);
            return node;
        } else {
            throw new NoSuchElementException();
        }
    }

    private void pushLeft(BinaryTreeNode node) {
        if (node != null) {
            stack.push(node);
            pushLeft(node.left);
        }
    }

    public boolean hasNextPreOrder() {
        return !preOrderStack.isEmpty();
    }

    //pre-order: root, left, right
    public BinaryTreeNode preOrderNext() {
        if (!preOrderStack.isEmpty()) {
            BinaryTreeNode node = preOrderStack.pop();

            if (node.right != null)
                preOrderStack.push(node.right);
            if (node.left != null)
                preOrderStack.push(node.left);

            return node;
        } else {
            return null;
        }
    }

    public void pushPostOrder(BinaryTreeNode n) {
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
    public BinaryTreeNode postOrderNext() {
        if (!postOrderQueue.isEmpty()) {
            return postOrderQueue.poll();
        } else {
            throw new NoSuchElementException();
        }
    }
}
