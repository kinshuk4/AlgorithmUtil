package com.vaani.algo.trees.binary.traversal;

import java.util.*;

public class BinaryTreePostorderIterator {
  
  public static class Node {
    int val;
    Node left;
    Node right;
  
    public Node(int val) {
      this.val = val;
    }
  }
  
  private Stack<Node> stack;
  private Node current;
  
  public BinaryTreePostorderIterator(Node node) {
    stack = new Stack<Node>();
    Node cur = node;
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
   
  public Node next() {
    Node node = stack.pop();
    Node ret = node;

    if (!stack.isEmpty()) {
      Node prev = stack.peek();
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
