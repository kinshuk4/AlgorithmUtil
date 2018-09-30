package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Stack;

// @formatter:off
/*
Given a binary tree, return the inorder traversal of its nodes' values.
For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].
Note: Recursive getTreeHeight is trivial, could you do it iteratively?
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
*/

// @formatter:on

public class InorderTraversal {
    //iterative
    public static ArrayList<Integer> inorderTraversalIterative(BinaryTreeNode<Integer> root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();

        BinaryTreeNode<Integer> node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                result.add(node.val);
                node = node.right;
            }
        }
        return result;
    }

    //recursive
    public ArrayList<Integer> inorderTraversalRecurive(BinaryTreeNode<Integer> root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> result = new ArrayList<Integer>();
        inorderTraversalRecursiveHelper(root, result);
        return result;
    }

    public void inorderTraversalRecursiveHelper(BinaryTreeNode<Integer> root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        inorderTraversalRecursiveHelper(root.left, result);
        result.add(root.val);
        inorderTraversalRecursiveHelper(root.right, result);
    }
}
