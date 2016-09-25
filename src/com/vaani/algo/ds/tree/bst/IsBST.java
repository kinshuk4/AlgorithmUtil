package com.vaani.algo.ds.tree.bst;

import com.vaani.algo.ds.core.TreeNode;
import com.vaani.algo.ds.tree.binary.traversal.BinaryTreeInorderIterator;
import com.vaani.algo.ds.tree.binary.traversal.BinaryTreePostorderIterator;
import com.vaani.algo.ds.tree.binary.traversal.BinaryTreePreOrderIterator;

/**
 * determine if a given binary tree is a binary search tree.
 */
public class IsBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode node5 = new TreeNode(5);
        TreeNode node15 = new TreeNode(15);
        TreeNode node6 = new TreeNode(6);
        TreeNode node20 = new TreeNode(20);
        TreeNode node3 = new TreeNode(3);
        TreeNode node12 = new TreeNode(12);

        root.insertForBst(node5);
        root.insertForBst(node6);
        root.insertForBst(node15);
        root.insertForBst(node20);
        root.insertForBst(node3);
        root.insertForBst(node12);

        System.out.println(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println(isBST1(root, Integer.MIN_VALUE));

        BinaryTreeInorderIterator treeIterator = new BinaryTreeInorderIterator(root);
        while (treeIterator.hasNext()) {
            TreeNode next = treeIterator.next();
            System.out.print(next.val + " ");
        }
        System.out.println();

        BinaryTreePostorderIterator postOrderIter = new BinaryTreePostorderIterator(root);
        while (postOrderIter.hasNext()) {
            TreeNode next = treeIterator.next();
            System.out.print(next.val + " ");
        }
        System.out.println();

        BinaryTreePreOrderIterator preOrderIter = new BinaryTreePreOrderIterator(root);
        while (preOrderIter.hasNext()) {
            TreeNode next = preOrderIter.next();
            System.out.print(next.val + " ");
        }
        System.out.println();
    }

    public static boolean isBST(TreeNode<Integer> node, int min, int max) {
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

    public static boolean isBST1(TreeNode<Integer> node, int prev) {
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
