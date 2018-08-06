package com.vaani.algo.ds.algos.tree.bst;


import com.vaani.algo.ds.core.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree.
 * Print all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key of given BST.
 * Print all the keys in increasing order.
 */
public class FindRangeInBST {
    public static List<Integer> findElements(TreeNode root, int start, int end) {
        List<Integer> result = new ArrayList<Integer>();
        findElements(root, start, end, result);
        return result;
    }

    private static void findElements(TreeNode<Integer> root, int start, int end, List<Integer> result) {
        if (root == null) return;
        if (root.val > start) findElements(root.left, start, end, result);
        if (root.val >= start && root.val <= end) result.add(root.val);
        if (root.val < end) findElements(root.right, start, end, result);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(12);
        System.out.println(findElements(root, 10, 22));
    }
}
