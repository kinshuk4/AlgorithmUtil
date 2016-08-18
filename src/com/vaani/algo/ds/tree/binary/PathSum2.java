package com.vaani.algo.ds.tree.binary;

import com.vaani.algo.ds.core.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * <p>
 * return
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * <p>
 * Created by Xiaomeng on 8/13/2014.
 */
public class PathSum2 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        PathSum2 test = new PathSum2();
        for (List<Integer> path : test.pathSum(root, 22)) {
            System.out.println(path);
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> single = new ArrayList<Integer>();
        pathSum(root, sum, 0, single, result);
        return result;
    }

    public void pathSum(TreeNode root, int sum, int count, List<Integer> single, List<List<Integer>> result) {
        if (root == null) return;
        count += root.val;
        single.add(root.val);

        if (root.left == null && root.right == null) {
            if (count == sum) result.add(new ArrayList<Integer>(single));
        } else {
            pathSum(root.left, sum, count, single, result);
            pathSum(root.right, sum, count, single, result);
        }
        single.remove(single.size() - 1);
    }
}
