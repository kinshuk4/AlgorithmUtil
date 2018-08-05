package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.TreeLinkNode;

/**
 * Follow up for problem "Populating Next Right Pointers in Each ListNode".
 * <p>
 * What if the given tree could be any binary tree? Would your previous getTreeHeight still work?
 * <p>
 * Note:
 * <p>
 * You may only use constant extra space.
 * <p>
 * For example,
 * Given the following binary tree,
 * <p>
 * 1
 * /  \
 * 2    3
 * / \    \
 * 4   5    7
 * <p>
 * After calling your function, the tree should look like:
 * <p>
 * 1 -> NULL
 * /  \
 * 2 -> 3 -> NULL
 * / \    \
 * 4-> 5 -> 7 -> NULL
 * <p>
 * Created by Xiaomeng on 8/11/2014.
 */
public class PopulatingNextRightPointers2 {
    /*
    * Why recursive the right subtree first?
    *
    * Think about what is happening in this case:
    *
    *         1
    *       /  \
    *      2    3
    *     /    / \
    *    4    5   7
    *     \      / \
    *     10    11 12
    *
    * */
    public void connect(TreeLinkNode root) {
        if (root == null) return;

        TreeLinkNode node = root.next;
        while (node != null && node.left == null && node.right == null) {
            node = node.next;
        }

        TreeLinkNode next = node == null ? null : node.left != null ? node.left : node.right;

        if (root.left != null) {
            root.left.next = root.right != null ? root.right : next;
        }

        if (root.right != null) {
            root.right.next = next;
        }

        connect(root.right);
        connect(root.left);
    }
}
