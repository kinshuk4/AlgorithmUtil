package com.vaani.algo.cc150.chapter2;


/**
 * Implement an algorithm to find the kth to last element of a singly linked
 * list.
 * 
 */
// O(1) space, O(n) time
public class Question2 {

  public static class Node<T> {
    public T elem;
    public Node<T> next;
    public Node(T elem) {
      this.elem = elem;
    }
  }

  public <T> Node<T> reverse(Node<T> head) {
    if (head == null) {
      return null;
    }
    Node<T> prev = null;
    Node<T> cur = head;
    while (cur != null) {
      Node<T> next = cur.next;
      cur.next = prev;
      prev = cur;
      cur = next;
    }
    return prev;
  }

  public <T> Node<T> findKthToLast(int k, Node<T> head) {
    // write implementation here
    Node<T> newHead = reverse(head);
    Node<T> kthNode = newHead;
    for (int i = 0; i < k; ++i) {
      kthNode = kthNode.next;
    }
    return kthNode;
  }
  
}
