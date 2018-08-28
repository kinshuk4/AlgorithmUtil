package com.vaani.algo.compete.cc150.chap4treegraph;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, design an algorithm which creates a linked list
 * of all the nodes at each depth (e.g., if you have a tree with depth D, you'll
 * have D linked lists).
 */
// O(n) space, O(1) time
public class Question4 {

    public List<List<BinaryTreeNode>> createLists(BinaryTreeNode root) {
        //  write implementation here
        List<List<BinaryTreeNode>> lists = new ArrayList<List<BinaryTreeNode>>();
        createListsIterative(root, lists);
        return lists;
    }

    private void createListsRecursive(BinaryTreeNode node, List<List<BinaryTreeNode>> lists, int level) {
        if (node == null) {
            return;
        }
        if (lists.size() == level) {
            List<BinaryTreeNode> list = new ArrayList<BinaryTreeNode>();
            lists.add(list);
        }
        List<BinaryTreeNode> list = lists.get(level);
        list.add(node);
        createListsRecursive(node.left, lists, level + 1);
        createListsRecursive(node.right, lists, level + 1);
    }

    private void createListsIterative(BinaryTreeNode root, List<List<BinaryTreeNode>> lists) {
        if (root == null) {
            return;
        }
        List<BinaryTreeNode> list = new ArrayList<BinaryTreeNode>();
        list.add(root);

        while (list.size() > 0) {
            List<BinaryTreeNode> parents = list;
            lists.add(parents);
            list = new ArrayList<BinaryTreeNode>();
            for (int i = 0; i < parents.size(); ++i) {
                BinaryTreeNode parent = parents.get(i);
                if (parent.left != null) {
                    list.add(parent.left);
                }
                if (parent.right != null) {
                    list.add(parent.right);
                }
            }
        }
    }


}