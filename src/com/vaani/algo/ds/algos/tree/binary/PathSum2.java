package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

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
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(4);
        root.right = new BinaryTreeNode(8);
        root.left.left = new BinaryTreeNode(11);
        root.right.left = new BinaryTreeNode(13);
        root.right.right = new BinaryTreeNode(4);
        root.left.left.left = new BinaryTreeNode(7);
        root.left.left.right = new BinaryTreeNode(2);
        root.right.right.left = new BinaryTreeNode(5);
        root.right.right.right = new BinaryTreeNode(1);

        PathSum2 test = new PathSum2();
        for (List<Integer> path : test.pathSum(root, 22)) {
            System.out.println(path);
        }
    }

    public List<List<Integer>> pathSum(BinaryTreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> single = new ArrayList<Integer>();
        pathSum(root, sum, 0, single, result);
        return result;
    }

    public void pathSum(BinaryTreeNode<Integer> root, int sum, int count, List<Integer> single, List<List<Integer>> result) {
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
