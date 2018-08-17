package com.vaani.algo.ds.utils;

import com.vaani.algo.ds.core.tree.BstNode;
import com.vaani.algo.ds.core.tree.TreeNode;

public class BstUtil {
    private BstUtil() {

    }

    public static <T extends Comparable> BstNode<T> findLowestCommonAncestor(BstNode<T> node, T num1, T num2) {
        while (node != null) {
            if (node.val.compareTo(num1) > 0 && node.val.compareTo(num2) > 0)
                node = node.left;
            else if (node.val.compareTo(num1) < 0 && node.val.compareTo(num2) < 0)
                node = node.right;
            else
                return node;
        }
        return null;
    }

    public static <T extends Comparable> boolean insert(TreeNode<T> root, T n) {

        if (n == null)
            return false;
        if (root.val.compareTo(n) <= 0) {
            if (root.left == null) {
                TreeNode<T> node = new TreeNode<>(n);
                root.left = node;
                return true;
            } else {
                return insert(root.left, n);
            }
        } else {
            if (root.right == null) {
                TreeNode<T> node = new TreeNode<>(n);
                root.right = node;
                return true;
            } else {
                return insert(root.right, n);
            }
        }
    }

    public static <T extends Comparable> TreeNode<T> search(TreeNode<T> root, T key) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.val == key)
            return root;

        // val is greater than root's key
        if (root.val.compareTo(key) > 0)
            return search(root.left, key);

        // val is less than root's key
        return search(root.right, key);
    }

    public static <T extends Comparable> TreeNode<T>[] searchWithParent(TreeNode<T> root, T key, TreeNode<T> parent) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.val == key)
            return new TreeNode[]{root, parent};
        parent = root;
        // val is greater than root's key
        if (root.val.compareTo(key) > 0)
            return searchWithParent(root.left, key, parent);

        // val is less than root's key
        return searchWithParent(root.right, key, parent);
    }


    public static <T extends Comparable> TreeNode<T> findMaxInBst(TreeNode<T> root) {
        TreeNode<T> x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public static <T extends Comparable> TreeNode<T> findMin(TreeNode<T> root) {
        TreeNode<T> x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public static<T extends Comparable> TreeNode<T> deleteMaxInBst(TreeNode<T> root) {
        TreeNode<T> prev = null;
        TreeNode<T> curr = root;
        while (curr.right != null) {
            prev = curr;
            curr = curr.right;
        }
        if (curr != root) {
            prev.right = curr.left;
            return root;
        }
        return curr.left;
    }


}
