package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import static com.vaani.algo.ds.utils.BinaryTreeUtil.arrayToBinaryTree;

public class MaximumRootToLeafSum {

    public static int maximumPathSum(BinaryTreeNode<Integer> root) {
        if (root == null){
            return 0;
        }

        return maximumPathSumHelper(root, 0);
    }

    public static int maximumPathSumHelper(BinaryTreeNode<Integer> root, int currPathSum) {
        if (root == null) {
            return currPathSum;
        }

        if (root.left == null && root.right == null) {
            return currPathSum + root.val;
        }

        int ls = maximumPathSumHelper(root.left, currPathSum+root.val);
        int rs = maximumPathSumHelper(root.right, currPathSum + root.val);

        return Math.max(ls, rs);
    }

    public static void main(String[] args) {
        System.out.println(maximumPathSum(arrayToBinaryTree(new Integer[]{1,2,3,4,5})));
    }
}
