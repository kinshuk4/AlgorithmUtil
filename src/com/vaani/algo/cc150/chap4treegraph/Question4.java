package com.vaani.algo.cc150.chap4treegraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, design an algorithm which creates a linked list
 * of all the nodes at each depth (e.g., if you have a tree with depth D, you'll
 * have D linked lists).
 * 
 */
// O(n) space, O(1) time
public class Question4 {
  
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    
    public TreeNode(int val) {
      this.val = val;
    }
  }

  public List<List<TreeNode>> createLists(TreeNode root) {
    //  write implementation here
    List<List<TreeNode>> lists = new ArrayList<List<TreeNode>>();
    createListsIterative(root, lists);
    return lists;
  }
  
  private void createListsRecursive(TreeNode node, List<List<TreeNode>> lists, int level) {
    if (node == null) {
      return;
    }
    if (lists.size() == level) {
      List<TreeNode> list = new ArrayList<TreeNode>();
      lists.add(list);
    }
    List<TreeNode> list = lists.get(level);
    list.add(node);
    createListsRecursive(node.left, lists, level + 1);
    createListsRecursive(node.right, lists, level + 1);
  }
  
  private void createListsIterative(TreeNode root, List<List<TreeNode>> lists) {
    if (root == null) {
      return;
    }
    List<TreeNode> list = new ArrayList<TreeNode>();
    list.add(root);
    
    while (list.size() > 0) {
      List<TreeNode> parents = list;
      lists.add(parents);
      list = new ArrayList<TreeNode>();
      for (int i = 0; i < parents.size(); ++i) {
        TreeNode parent = parents.get(i);
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