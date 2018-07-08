package com.vaani.algo.compete.cc150.chap4treegraph;

/**
 * Write an algorithm to find the 'next' node (i.e., in-order successor) of a
 * given node in a binary search tree. You may assume that each node has a link
 * to its parent.
 */
// O(1) space, O(n) time
public class Question6 {

    public void connect(TreeLinkNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        populate(root);
    }

    private void populate(TreeLinkNode node) {
        if (node == null) {
            return;
        }
        TreeLinkNode needNext = null;
        if (node.left != null) {
            needNext = node.left;
            if (node.right != null) {
                node.left.next = node.right;
            }
        }

        if (node.right != null) {
            needNext = node.right;
        }

        if (needNext != null && node.next != null) {
            TreeLinkNode nextParent = node.next;
            while (nextParent != null) {
                if (nextParent.left != null) {
                    needNext.next = nextParent.left;
                    break;
                }
                if (nextParent.right != null) {
                    needNext.next = nextParent.right;
                    break;
                }
                nextParent = nextParent.next;
            }
        }
        populate(node.right);
        populate(node.left);
    }

    // this question is available at leetcode, question Populating Next Right
    // Pointers in Each ListNode I and II
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

}
