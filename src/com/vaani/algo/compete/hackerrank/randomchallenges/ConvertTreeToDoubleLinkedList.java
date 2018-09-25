package com.vaani.algo.compete.hackerrank.randomchallenges;


import java.util.LinkedList;
import java.util.Queue;

import com.vaani.algo.ds.core.list.dll.DLLNode;
import com.vaani.algo.ds.core.tree.BinaryTreeNode;


public class ConvertTreeToDoubleLinkedList {


    public static void main(String... args) {
        DLLNode<Character> tail = ConvertTreeToDoubleLinkedList.<Character>convertToDLLDFS(new BinaryTreeNode(4));
        DLLNode<Character> head = tail;
        while (head.prev != null) {
            head = head.prev;
        }
        printLinkedListFromTail(tail);
        System.out.println();
        printLinkedListFromHead(head);
    }

    /**
     * Returns tail of a queue
     *
     * @param <E>
     * @return
     */
    private static <E> DLLNode<E> convertToDLLBFS(BinaryTreeNode<E> root) {
        if (root == null) {
            return null;
        }
        DLLNode<E> currentNode = null;
        Queue<BinaryTreeNode<E>> treeNodesQueue = new LinkedList<>();
        treeNodesQueue.offer(root);
        while (!treeNodesQueue.isEmpty()) {
            BinaryTreeNode<E> n = treeNodesQueue.remove();
            DLLNode<E> dllNode = new DLLNode<>(n.val);
            if (currentNode != null) {
                currentNode.linkNext(dllNode);
            }
            currentNode = dllNode;
            if (n.left != null) treeNodesQueue.offer(n.left);
            if (n.right != null) treeNodesQueue.offer(n.right);
        }
        return currentNode;
    }

    private static <E> DLLNode<E> convertToDLLDFS(BinaryTreeNode<E> root) {
        if (root == null) {
            return null;
        }
        return convertToDLLDFS(root, null);
    }

    private static <E> DLLNode<E> convertToDLLDFS(BinaryTreeNode<E> root, DLLNode<E> currentTail) {
        DLLNode<E> tail = new DLLNode<>(root.val);
        if (currentTail != null) {
            currentTail.linkNext(tail);
        }
        if (root.left != null) tail = convertToDLLDFS(root.left, tail);
        if (root.right != null) tail = convertToDLLDFS(root.right, tail);
        return tail;
    }

    private static <E> void printLinkedListFromHead(DLLNode<E> head) {
        do {
            System.out.print(head.value.toString() + " ");
            head = head.next;
        } while (head != null);
    }

    private static <E> void printLinkedListFromTail(DLLNode<E> tail) {
        do {
            System.out.print(tail.value.toString() + " ");
            tail = tail.prev;
        } while (tail != null);
    }

}
