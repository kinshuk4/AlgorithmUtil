package com.vaani.algo.ds.utils;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;
import com.vaani.algo.ds.core.tree.BstNode;

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

    public static <T extends Comparable> boolean insert(BinaryTreeNode<T> root, T n) {

        if (n == null)
            return false;
        if (root.val.compareTo(n) <= 0) {
            if (root.left == null) {
                BinaryTreeNode<T> node = new BinaryTreeNode<>(n);
                root.left = node;
                return true;
            } else {
                return insert(root.left, n);
            }
        } else {
            if (root.right == null) {
                BinaryTreeNode<T> node = new BinaryTreeNode<>(n);
                root.right = node;
                return true;
            } else {
                return insert(root.right, n);
            }
        }
    }

    public static <T extends Comparable> BinaryTreeNode<T> search(BinaryTreeNode<T> root, T key) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.val == key)
            return root;

        // val is greater than root's key
        if (root.val.compareTo(key) > 0)
            return search(root.left, key);

        // val is less than root's key
        return search(root.right, key);
    }

    public static <T extends Comparable> BinaryTreeNode<T>[] searchWithParent(BinaryTreeNode<T> root, T key, BinaryTreeNode<T> parent) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.val == key)
            return new BinaryTreeNode[]{root, parent};
        parent = root;
        // val is greater than root's key
        if (root.val.compareTo(key) > 0)
            return searchWithParent(root.left, key, parent);

        // val is less than root's key
        return searchWithParent(root.right, key, parent);
    }


    public static <T extends Comparable> BinaryTreeNode<T> findMaxInBst(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public static <T extends Comparable> BinaryTreeNode<T> findMin(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public static<T extends Comparable> BinaryTreeNode<T> deleteMaxInBst(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> prev = null;
        BinaryTreeNode<T> curr = root;
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
