package com.vaani.algo.ds.convert;

import com.vaani.algo.ds.core.TreeNode;

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
    static TreeNode prev, head;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode node5 = new TreeNode(5);
        TreeNode node15 = new TreeNode(15);
        TreeNode node6 = new TreeNode(6);
        TreeNode node20 = new TreeNode(20);
        TreeNode node3 = new TreeNode(3);
        TreeNode node12 = new TreeNode(12);
        TreeNode node14 = new TreeNode(14);

        root.insertForBst(node5);
        root.insertForBst(node6);
        root.insertForBst(node15);
        root.insertForBst(node20);
        root.insertForBst(node3);
        root.insertForBst(node12);
        root.insertForBst(node14);

        ArrayList<TreeNode> dll = bstToDLL(root);
        printDll(dll);

        bstToDllinPlace(root);
        printDll(head);

    }

    public static void printDll(TreeNode<Integer> n) {
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

    public static void printDll(ArrayList<TreeNode> dll) {
        if (dll.size() != 0) {
            for (TreeNode n : dll) {
                System.out.print(n.val + " ");
            }
        }
        System.out.println();
    }

    // not in place
    public static ArrayList<TreeNode> bstToDLL(TreeNode node) {
        if (node == null)
            return null;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<TreeNode> dll = new ArrayList<TreeNode>();
        TreeNode current = node;
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

    public static void bstToDllinPlace(TreeNode p) {
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

        TreeNode rightTreeNode = p.right;
        head.left = p;
        p.right = head;
        prev = p;

        bstToDllinPlace(rightTreeNode);

        System.out.print("parent: " + p.val + " prev: ");
        nodeToString(prev);
        System.out.print(" head: ");
        nodeToString(head);

    }

    public static void nodeToString(TreeNode n) {
        if (n == null) {
            System.out.println("null");
        } else {
            System.out.println(n.val);
        }

    }

}