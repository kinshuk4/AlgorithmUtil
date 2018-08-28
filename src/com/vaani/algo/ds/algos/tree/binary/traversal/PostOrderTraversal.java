package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Stack;

/*
Given a binary tree, return the postorder traversal of its nodes' values.
For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].
Note: Recursive getTreeHeight is trivial, could you do it iteratively?
*/


public class PostOrderTraversal {
    //iterative, two stacks
    public ArrayList<Integer> postorderTraversal(BinaryTreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        Stack<BinaryTreeNode<Integer>> reverse = new Stack<BinaryTreeNode<Integer>>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();
            reverse.add(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (!reverse.isEmpty()) {
            result.add(reverse.pop().val);
        }
        return result;
    }

    //iterative, one stack
    void postOrderTraversalIterative2(BinaryTreeNode root) {
        if (root == null) return;
        Stack<BinaryTreeNode> s = new Stack<>();
        s.push(root);
        BinaryTreeNode prev = null;
        while (!s.isEmpty()) {
            BinaryTreeNode curr = s.peek();
            if (prev != null || prev.left == curr || prev.right == curr) {
                if (curr.left != null)
                    s.push(curr.left);
                else if (curr.right != null)
                    s.push(curr.right);
            } else if (curr.left == prev) {
                if (curr.right != null)
                    s.push(curr.right);
            } else {
                System.out.println(curr.val + " ");
                s.pop();
            }
            prev = curr;
        }
    }

    //Recursive
    public ArrayList<Integer> postorderTraversal3(BinaryTreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> result = new ArrayList<Integer>();
        helper(root, result);
        return result;
    }

    public void helper(BinaryTreeNode<Integer> root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        helper(root.left, result);
        helper(root.right, result);
        result.add(root.val);
    }
}





