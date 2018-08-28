package com.vaani.algo.compete.codesignal.interviewpractice.dfsbfs;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;


import java.util.*;

import static com.vaani.algo.ds.utils.BinaryTreeUtil.arrayToBinaryTree;

/**
 * We're going to store numbers in a tree. Each node in this tree will store a single digit (from 0 to 9), and each path from root to leaf encodes a non-negative integer.
 * <p>
 * Given a binary tree t, find the sum of all the numbers encoded in it.
 * <p>
 * Example
 * <p>
 * For
 * t = {
 * "value": 1,
 * "left": {
 * "value": 0,
 * "left": {
 * "value": 3,
 * "left": null,
 * "right": null
 * },
 * "right": {
 * "value": 1,
 * "left": null,
 * "right": null
 * }
 * },
 * "right": {
 * "value": 4,
 * "left": null,
 * "right": null
 * }
 * }
 * the output should be
 * digitTreeSum(t) = 218.
 * There are 3 numbers encoded in this tree:
 * <p>
 * Path 1->0->3 encodes 103
 * Path 1->0->1 encodes 101
 * Path 1->4 encodes 14
 * and their sum is 103 + 101 + 14 = 218
 */
public class DigitTreeSum {
    static long digitTreeSum(BinaryTreeNode<Integer> root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return root.val;
        }
        List<Long> leaves = new LinkedList<>();

        digitTreeSumHelper(root, 0, leaves);

        return leaves.stream().mapToLong(Long::longValue).sum();
    }

    static void digitTreeSumHelper(BinaryTreeNode<Integer> root, long currParent, List<Long> leaves) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leaves.add(currParent * 10 + root.val);
            return;
        }

        digitTreeSumHelper(root.left, currParent * 10 + root.val, leaves);
        digitTreeSumHelper(root.right, currParent * 10 + root.val, leaves);
    }

    public static void main(String[] args) {
        System.out.println(digitTreeSum(arrayToBinaryTree(new Integer[]{1, 0, 4, 3, 1})));
    }
}
