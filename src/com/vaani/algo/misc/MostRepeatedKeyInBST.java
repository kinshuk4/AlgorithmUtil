package com.vaani.algo.misc;

import java.util.*;

/* class TreeNode { */
/*   int val; */
/*   TreeNode left, right; */
/*   public TreeNode(int val) { */
/*     this.val = val; */
/*   } */
/* } */

class BST {
  private TreeNode root;
  
  public void insert(int val) {
    if (root == null) {
      root = new TreeNode(val);
    } else {
      insert(root, val);
    }
  }
  
  private TreeNode insert(TreeNode node, int val) {
    if (node == null) {
      return new TreeNode(val);
    } else if (node.val <= val) {
      node.right = insert(node.right, val);
    } else {
      node.left = insert(node.left, val);
    }
    return node;
  }
  
  public TreeNode root() {
    return root;
  }
  
}

public class MostRepeatedKeyInBST {
  
  public int mostRepeatedBST(BST bst) {
    Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
    mostRepeatedBST(bst.root(), counts);
    int max = 0;
    for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
      max = Math.max(max, entry.getValue());
    }
    return max;
  }
  
  private void mostRepeatedBST(TreeNode node, Map<Integer, Integer> counts) {
    if (node == null) {
      return;
    } else {
      Integer count = counts.get(node.val);
      if (count == null) {
        counts.put(node.val, 1);
      } else {
        counts.put(node.val, count + 1);
      }
      mostRepeatedBST(node.left, counts);
      mostRepeatedBST(node.right, counts);
    }
  }
  
}

