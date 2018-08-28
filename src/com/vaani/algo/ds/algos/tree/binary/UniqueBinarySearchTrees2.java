package com.vaani.algo.ds.algos.tree.binary;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * <p>
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * Created by Xiaomeng on 8/14/2014.
 */
public class UniqueBinarySearchTrees2 {
    public static void main(String[] args) {
        UniqueBinarySearchTrees2 test = new UniqueBinarySearchTrees2();
        for (BinaryTreeNode root : test.generateTrees(1)) {
            root.display();
        }
    }

    public List<BinaryTreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    public List<BinaryTreeNode> generateTrees(int a, int b) {
        List<BinaryTreeNode> result = new ArrayList<BinaryTreeNode>();
        if (a == b) {
            result.add(new BinaryTreeNode(a));
        } else if (a > b) {
            result.add(null);
        } else {
            for (int i = a; i <= b; i++) {
                List<BinaryTreeNode> leftTrees = generateTrees(a, i - 1);
                List<BinaryTreeNode> rightTrees = generateTrees(i + 1, b);
                for (BinaryTreeNode leftTree : leftTrees) {
                    for (BinaryTreeNode rightTree : rightTrees) {
                        BinaryTreeNode root = new BinaryTreeNode(i);
                        root.left = leftTree;
                        root.right = rightTree;
                        result.add(root);
                    }
                }
            }
        }
        return result;
    }
}
