package com.vaani.algo.trees.binary.traversal;

import java.util.*;


/*
 *	Binary tree in-order iterator.
 *	
 *	Space cost: O(logN) in average.I
 *	Time cost: O(logN) in average.
 *
 */
public class BinaryTreeInorderIterator {

  public static class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
      this.val = val;
    }
  }

  private Stack<Node> stack;

  public BinaryTreeInorderIterator(Node node) {
    this.stack = new Stack<Node>();
    Node cur = node;
    while (cur != null) {
      this.stack.push(node);
      node = node.left;
    }
  }

  public Node next() {
    if (stack.isEmpty()) {
      return null;
    }
    Node cur = stack.pop();
    Node node = cur.right;
    while (node != null) {
      this.stack.push(node);
      node = node.left;
    }
    return cur;
  }
}
