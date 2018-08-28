package com.vaani.algo.ds.algos.tree.bst;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;
import com.vaani.algo.ds.algos.tree.binary.traversal.BinaryTreeInorderIterator;
import com.vaani.algo.ds.algos.tree.binary.traversal.BinaryTreePostorderIterator;
import com.vaani.algo.ds.algos.tree.binary.traversal.BinaryTreePreOrderIterator;

/**
 * determine if a given binary tree is a binary search tree.
 */
public class IsBST {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(10);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node15 = new BinaryTreeNode(15);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node20 = new BinaryTreeNode(20);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node12 = new BinaryTreeNode(12);
//
//        root.insertForBst(node5);
//        root.insertForBst(node6);
//        root.insertForBst(node15);
//        root.insertForBst(node20);
//        root.insertForBst(node3);
//        root.insertForBst(node12);

        System.out.println(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println(isBST1(root, Integer.MIN_VALUE));

        BinaryTreeInorderIterator treeIterator = new BinaryTreeInorderIterator(root);
        while (treeIterator.hasNext()) {
            BinaryTreeNode next = treeIterator.next();
            System.out.print(next.val + " ");
        }
        System.out.println();

        BinaryTreePostorderIterator postOrderIter = new BinaryTreePostorderIterator(root);
        while (postOrderIter.hasNext()) {
            BinaryTreeNode next = treeIterator.next();
            System.out.print(next.val + " ");
        }
        System.out.println();

        BinaryTreePreOrderIterator preOrderIter = new BinaryTreePreOrderIterator(root);
        while (preOrderIter.hasNext()) {
            BinaryTreeNode next = preOrderIter.next();
            System.out.print(next.val + " ");
        }
        System.out.println();
    }

    public static boolean isBST(BinaryTreeNode<Integer> node, int min, int max) {
        if (node == null) {
            return true;
        }
        if ((min < node.val) && (node.val < max)) {
            return isBST(node.left, min, node.val) &&
                    isBST(node.right, node.val, max);
        } else {
            return false;
        }
    }

    public static boolean isBST1(BinaryTreeNode<Integer> node, int prev) {
        if (node == null) {
            return true;
        }
        if (isBST1(node.left, prev)) {
            if (node.val > prev) {
                prev = node.val;
                return isBST1(node.right, prev);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
