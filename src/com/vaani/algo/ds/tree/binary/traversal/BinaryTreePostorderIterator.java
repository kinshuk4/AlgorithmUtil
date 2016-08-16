package com.vaani.algo.ds.tree.binary.traversal;

import java.util.*;

import com.vaani.algo.ds.core.TreeNode;

public class BinaryTreePostorderIterator {
  

  private Stack<TreeNode> stack;
  private TreeNode current;
  
  public BinaryTreePostorderIterator(TreeNode node) {
    stack = new Stack<TreeNode>();
    TreeNode cur = node;
    while (node != null) {
      stack.push(node);
      if (node.left != null) {
        node = node.left;
      } else {
        node = node.right;
      }
    }
  }

  public boolean hasNext() {
    return !stack.isEmpty();
  }
   
  public TreeNode next() {
    TreeNode node = stack.pop();
    TreeNode ret = node;

    if (!stack.isEmpty()) {
      TreeNode prev = stack.peek();
      if (prev.left == node) { // current is left child
        node = prev.right;
        while (node != null) {
          stack.push(node);
          if (node.left != null) {
            node = node.left;
          } else {
            node = node.right;
          }
        }
      }
    }

    return ret;
  }
  
}
