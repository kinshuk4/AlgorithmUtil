package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.TreeLinkNode;

/**
 * Given a binary tree
 * <p>
 * struct TreeLinkNode {
 * TreeLinkNode *left;
 * TreeLinkNode *right;
 * TreeLinkNode *next;
 * }
 * <p>
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * Note:
 * <p>
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * <p>
 * For example,
 * Given the following perfect binary tree,
 * <p>
 * 1
 * /  \
 * 2    3
 * / \  / \
 * 4  5  6  7
 * <p>
 * After calling your function, the tree should look like:
 * <p>
 * 1 -> NULL
 * /  \
 * 2 -> 3 -> NULL
 * / \  / \
 * 4->5->6->7 -> NULL
 * <p>
 * Created by Xiaomeng on 8/11/2014.
 */
public class PopulatingNextRightPointers {
    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode();
        root.left = new TreeLinkNode();
        root.right = new TreeLinkNode();

        PopulatingNextRightPointers test = new PopulatingNextRightPointers();
        test.connect(root);
    }

    public void connect(TreeLinkNode root) {
        if (root == null || root.left == null || root.right == null) return;

        root.left.next = root.right;
        root.right.next = root.next == null ? null : root.next.left;

        connect(root.left);
        connect(root.right);
    }
}
