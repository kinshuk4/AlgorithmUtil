package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class LevelOrderTraversal1 {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode(1);
        BinaryTreeNode<Integer> l1 = new BinaryTreeNode(3);
        BinaryTreeNode<Integer> l2 = new BinaryTreeNode(9);
        BinaryTreeNode<Integer> l3 = new BinaryTreeNode(11);
        BinaryTreeNode<Integer> r1 = new BinaryTreeNode(5);
        BinaryTreeNode<Integer> r2 = new BinaryTreeNode(12);
        BinaryTreeNode<Integer> r3 = new BinaryTreeNode(13);
        root.left = l1;
        root.right = r1;
        l1.left = l2;
        l2.left = l3;
        l3.right = r2;
        r2.right = r3;
        levelOrderTraversal(root);

        List<Integer> traversal = levelOrderTraversal(root);
    }

    public static <T> List<T> levelOrderTraversal(BinaryTreeNode<T> root) {
        List<T> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<BinaryTreeNode<T>> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode<T> remove = queue.remove();
            result.add(remove.val);
            if (remove.left != null) {
                queue.add(remove.left);
            }
            if (remove.right != null) {
                queue.add(remove.right);
            }

        }
        return result;
    }


}