package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * return [1,3,2].
 * <p>
 * Created by Xiaomeng on 8/7/2014.
 */
public class InorderTraversal {
    public static void main(String[] args) {
        InorderTraversal test = new InorderTraversal();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.println(test.inorderTraversal(root));
    }

    public List<Integer> inorderTraversal(TreeNode<Integer> root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        Stack<TreeNode<Integer>> stack = new Stack<TreeNode<Integer>>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode<Integer> node = stack.pop();
                result.add(node.val);
                if (node.right != null) {
                    root = node.right;
                }
            }
        }
        return result;
    }
}
