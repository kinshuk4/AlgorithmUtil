package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.*;

public class ZigZagLevelOrder {

    @SuppressWarnings("Duplicates")
    public static <T> List<List<T>> zigzagLevelOrderUsingQueue(BinaryTreeNode<T> root) {
        List<List<T>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Queue<BinaryTreeNode<T>> queue1 = new LinkedList<>();
        Queue<BinaryTreeNode<T>> queue2 = new LinkedList<>();

        List<T> level = new LinkedList<>();
        queue1.add(root);

        while (!queue1.isEmpty()) {
            BinaryTreeNode<T> node = queue1.poll();
            level.add(node.val);

            if (node.left != null) {
                queue2.add(node.left);
            }
            if (node.right != null) {
                queue2.add(node.right);
            }

            if (queue1.isEmpty()) {
                result.add(level);
                level = new ArrayList<T>();
                queue1.addAll(queue2);
                queue2.clear();
            }
        }
        for (int i = 1; i < result.size(); i += 2) {
            Collections.reverse(result.get(i));
        }
        return result;
    }

    /**
     * Two Stack Solution
     */
    public static <T> List<List<T>> ZigZagLevelOrderUsingStack(BinaryTreeNode<T> root) {
        List<List<T>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }


        Stack<BinaryTreeNode<T>> currLevel = new Stack<>();
        Stack<BinaryTreeNode<T>> nextLevel = new Stack<>();
        Stack<BinaryTreeNode<T>> temp;

        boolean leftToRight = true;

        // push root in stack
        currLevel.push(root);
        List<T> level = new LinkedList<>();

        while (!currLevel.empty()) {
            BinaryTreeNode<T> node = currLevel.pop(); //top();
            level.add(node.val);

            if (leftToRight) {
                //if left to right, start pushing from left
                pushToStack(nextLevel, node.left);
                pushToStack(nextLevel, node.right);
            } else {
                //else push the right node first
                pushToStack(nextLevel, node.right);
                pushToStack(nextLevel, node.left);
            }


            if (currLevel.isEmpty()) {
                result.add(level);
                level = new LinkedList<T>();

                leftToRight = !leftToRight;
                //swap stacks
                temp = currLevel;
                currLevel = nextLevel;
                nextLevel = temp;
                //we can also set currLevel = nextLevel and instantiate nextLevel again
                //nextLevel = new Stack<BinaryTreeNode>();

            }
        }
        return result;
    }

    private static <T> void pushToStack(Stack<BinaryTreeNode<T>> stack, BinaryTreeNode<T> node) {
        if (node != null) {
            stack.push(node);
        }
    }


}
