package com.vaani.algo.ds.tree.binary;

import com.vaani.algo.ds.core.tree.TreeNode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * For example,
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * <p>
 * Return the sum = 12 + 13 = 25.
 * <p>
 * Created by Xiaomeng on 8/14/2014.
 */
public class SumRootToLeafNumbers {
    int sum = 0;

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);

        SumRootToLeafNumbers test = new SumRootToLeafNumbers();
        System.out.println(test.sumNumbers(node));
    }

    public int sumNumbers(TreeNode root) {
        sumNumbers(root, 0);
        return sum;
    }

    public void sumNumbers(TreeNode<Integer> root, int count) {
        if (root == null) return;
        count = 10 * count + root.val;
        if (root.left == null && root.right == null) {
            sum += count;
            return;
        }
        sumNumbers(root.left, count);
        sumNumbers(root.right, count);
    }
}
