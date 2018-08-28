package com.vaani.algo.ds.algos.tree.bst;


import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree.
 * Print all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key of given BST.
 * Print all the keys in increasing order.
 */
public class FindRangeInBST {
    public static List<Integer> findElements(BinaryTreeNode root, int start, int end) {
        List<Integer> result = new ArrayList<Integer>();
        findElements(root, start, end, result);
        return result;
    }

    private static void findElements(BinaryTreeNode<Integer> root, int start, int end, List<Integer> result) {
        if (root == null) return;
        if (root.val > start) findElements(root.left, start, end, result);
        if (root.val >= start && root.val <= end) result.add(root.val);
        if (root.val < end) findElements(root.right, start, end, result);
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(20);
        root.left = new BinaryTreeNode(8);
        root.right = new BinaryTreeNode(22);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(12);
        System.out.println(findElements(root, 10, 22));
    }
}
