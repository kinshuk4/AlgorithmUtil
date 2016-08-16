package com.vaani.algo.recursion;

public class LowestCommonAncestor {
  
  public static class NodeWithParent {
    public NodeWithParent parent;
    public int val;
    
    public NodeWithParent(int val) {
      this.val = val;
    }
  }
  
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
      this.val = val;
    }
  }
  
  private int depth(NodeWithParent node) {
    int n = 0;
    while (node != null) {
      node = node.parent;
      ++n;
    }
    return n;
  }
  
  /**
   * Find LCA for nodes with only parent pointer.
   * O(h) time, where h is the height of the tree
   * @param node1
   * @param node2
   * @return
   */
  public NodeWithParent lca(NodeWithParent node1, NodeWithParent node2) {
    int leftDepth = depth(node1);
    int rightDepth = depth(node2);
    
    if (leftDepth < rightDepth) {
      int k = 0;
      while (k++ < rightDepth - leftDepth) {
        node2 = node2.parent;
      }
    }
    else if (leftDepth > rightDepth) {
      int k = 0;
      while (k++ < leftDepth - rightDepth) {
        node1 = node1.parent;
      }
    }
    
    while (node1 != null && node2 != null && node1 != node2) {
      node1 = node1.parent;
      node2 = node2.parent;
    }
    return node1;
  }
  
  /**
   * O(n) time, where n is the size of the tree.
   * @param root
   * @param left
   * @param right
   * @return
   */
  public TreeNode lcaRecursive(TreeNode root, TreeNode left, TreeNode right) {
    if (root == null) {
      return null;
    }
    if (root == left || root == right) {
      return root;
    }
    TreeNode lcaLeft = lcaRecursive(root.left, left, right); 
    TreeNode lcaRight = lcaRecursive(root.right, left, right);  
    if (lcaLeft != null && lcaRight != null) { // both sides find either p or q
      return root;
    }
    return lcaLeft != null? lcaLeft : lcaRight;
  }

}
