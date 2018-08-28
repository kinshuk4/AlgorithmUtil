package com.vaani.algo.ds.algos.convert;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Stack;


/**
 * Convert a BST to a sorted circular doubly-linked list in-place. Think of the
 * left and right pointers as synonymous to the previous and next pointers in a
 * doubly-linked list.
 * <p/>
 * This is a modified in-order traversal adapted to this problem. prev (init to
 * NULL) is used to keep track of previously traversed node. head pointer is
 * updated with the list's head as recursion ends.
 * <p/>
 * do it in place. time complexity: O(n)
 */
public class BSTtoDLL {
    // in place approach
    static BinaryTreeNode prev, head;

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(10);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node15 = new BinaryTreeNode(15);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node20 = new BinaryTreeNode(20);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node12 = new BinaryTreeNode(12);
        BinaryTreeNode node14 = new BinaryTreeNode(14);

//        root.insertForBst(node5);
//        root.insertForBst(node6);
//        root.insertForBst(node15);
//        root.insertForBst(node20);
//        root.insertForBst(node3);
//        root.insertForBst(node12);
//        root.insertForBst(node14);

        ArrayList<BinaryTreeNode> dll = bstToDLL(root);
        printDll(dll);

        bstToDllinPlace(root);
        printDll(head);

    }

    public static void printDll(BinaryTreeNode<Integer> n) {
        if (n == null) {
            System.out.println("null");
        } else {
            int s = n.val;
            System.out.print(n.val + " ");
            n = n.right;
            while (n.val != s) {
                System.out.print(n.val + " ");
                n = n.right;
            }
        }
        System.out.println();
    }

    public static void printDll(ArrayList<BinaryTreeNode> dll) {
        if (dll.size() != 0) {
            for (BinaryTreeNode n : dll) {
                System.out.print(n.val + " ");
            }
        }
        System.out.println();
    }

    // not in place
    public static ArrayList<BinaryTreeNode> bstToDLL(BinaryTreeNode node) {
        if (node == null)
            return null;

        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        ArrayList<BinaryTreeNode> dll = new ArrayList<BinaryTreeNode>();
        BinaryTreeNode current = node;
        boolean done = false;
        while (!done) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                if (stack.isEmpty()) {
                    done = true;
                } else {
                    current = stack.pop();
                    dll.add(current);
                    current = current.right;
                }
            }
        }
        return dll;
    }

    public static void bstToDllinPlace(BinaryTreeNode p) {
        if (p == null)
            return;

        bstToDllinPlace(p.left);
        p.left = prev;
        // current node (smallest element) is head of
        // the list if previous node is not available
        if (prev == null) {
            head = p;
        } else {
            prev.right = p;
        }

        System.out.print("parent: " + p.val + " prev: ");
        nodeToString(prev);
        System.out.print(" head: ");
        nodeToString(head);

        // as soon as the recursion ends, the head's left pointer
        // points to the last node, and the last node's right pointer
        // points to the head pointer.

        BinaryTreeNode rightTreeNode = p.right;
        head.left = p;
        p.right = head;
        prev = p;

        bstToDllinPlace(rightTreeNode);

        System.out.print("parent: " + p.val + " prev: ");
        nodeToString(prev);
        System.out.print(" head: ");
        nodeToString(head);

    }

    public static void nodeToString(BinaryTreeNode n) {
        if (n == null) {
            System.out.println("null");
        } else {
            System.out.println(n.val);
        }

    }

}