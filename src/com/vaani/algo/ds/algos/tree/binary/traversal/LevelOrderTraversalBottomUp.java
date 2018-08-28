package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;

/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7]
  [9,20],
  [3],
]
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

/**
 * Definition for binary tree
 * public class BinaryTreeNode {
 * int val;
 * BinaryTreeNode left;
 * BinaryTreeNode right;
 * BinaryTreeNode(int x) { val = x; }
 * }
 */
public class LevelOrderTraversalBottomUp {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(BinaryTreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return result;
        }
        ArrayList<BinaryTreeNode> list = new ArrayList<BinaryTreeNode>();
        list.add(root);
        ArrayList<BinaryTreeNode> parent = list;
        while (!parent.isEmpty()) {

            ArrayList<BinaryTreeNode> cur = new ArrayList<BinaryTreeNode>();
            ArrayList<Integer> parentData = new ArrayList<Integer>();
            for (BinaryTreeNode<Integer> n : parent) {
                parentData.add(n.val);
                if (n.left != null) {
                    cur.add(n.left);
                }
                if (n.right != null) {
                    cur.add(n.right);
                }
            }
            result.add(0, parentData);
            parent = cur;

        }
        return result;

    }
}