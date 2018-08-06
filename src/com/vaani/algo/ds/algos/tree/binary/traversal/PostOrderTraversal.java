package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.TreeNode;

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
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<TreeNode<Integer>> reverse = new Stack<TreeNode<Integer>>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
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
    void postOrderTraversalIterative2(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        TreeNode prev = null;
        while (!s.isEmpty()) {
            TreeNode curr = s.peek();
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
    public ArrayList<Integer> postorderTraversal3(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> result = new ArrayList<Integer>();
        helper(root, result);
        return result;
    }

    public void helper(TreeNode<Integer> root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        helper(root.left, result);
        helper(root.right, result);
        result.add(root.val);
    }
}





