package com.vaani.algo.ds.tree.binary;


import com.vaani.algo.ds.core.TreeNode;

import java.util.Map;
import java.util.TreeMap;

/**
 * Given a Binary Tree, find vertical sum of the nodes that are in same vertical line. Print all sums through different vertical lines.
 * <p>
 * Examples:
 * 1
 * /   \
 * 2      3
 * / \    / \
 * 4   5  6   7
 * <p>
 * The tree has 5 vertical lines
 * <p>
 * Vertical-Line-1 has only one node 4 => vertical sum is 4
 * Vertical-Line-2: has only one node 2=> vertical sum is 2
 * Vertical-Line-3: has three nodes: 1,5,6 => vertical sum is 1+5+6 = 12
 * Vertical-Line-4: has only one node 3 => vertical sum is 3
 * Vertical-Line-5: has only one node 7 => vertical sum is 7
 * <p>
 * So expected output is 4, 2, 12, 3 and 7
 */
public class BinaryTreeVerticalSum {
    public static void verticalSum(TreeNode root) {
        if (root == null) return;
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
        verticalSum(root, 0, map);
        System.out.println(map.entrySet());
    }

    public static void verticalSum(TreeNode<Integer> root, int hd, Map<Integer, Integer> map) {
        if (root == null) return;
        int sum = map.get(hd) == null ? 0 : map.get(hd);
        map.put(hd, root.val + sum);

        verticalSum(root.left, hd - 1, map);
        verticalSum(root.right, hd + 1, map);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        verticalSum(root);
    }
}
