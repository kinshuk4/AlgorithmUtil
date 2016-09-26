package com.vaani.algo.compete.cc150.chapter17;

/**
 * Consider a simple node-like data structure called BiNode, which has pointers
 * to two other nodes.
 * <p>
 * The data structure BiNode could be used to represent both a binary tree (were
 * first is the left node and second is the right node) or a doubly linked list
 * (where first is the previous node and second is the next node). Implement a
 * method to convert a binary search tree (implemented with BiNode) into a
 * doubly linked list in place.
 */
public class Question13 {

    public BiNode flatten(BiNode root) {
        if (root == null) {
            return null;
        }

        BiNode head1 = flatten(root.first);
        BiNode head2 = flatten(root.second);

        if (head1 != null) {
            head1.first = null;
            BiNode tail1 = getTail(head1);
            tail1.second = root;
            root.first = tail1;
        }
        if (head2 != null) {
            root.second = head2;
            head2.first = root;
        }

        return head1 != null ? head1 : root;
    }

    private BiNode getTail(BiNode node) {
        if (node == null) {
            return null;
        }
        BiNode cur = node;
        while (cur.second != null) {
            cur = cur.second;
        }
        return cur;
    }

    public static class BiNode {
        int val;
        BiNode first;
        BiNode second;

        BiNode(int x) {
            val = x;
        }
    }

}
