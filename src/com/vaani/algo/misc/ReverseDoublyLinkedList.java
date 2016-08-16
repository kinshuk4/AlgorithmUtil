package com.vaani.algo.misc;

/**
 * Reverse a given Doubly Linked List
 *
 */
public class ReverseDoublyLinkedList {
    public static DoublyLinkedList reverse(DoublyLinkedList head){
        if(head == null) return null;

        DoublyLinkedList tmp = null;
        DoublyLinkedList curr = head;
        while(curr != null){
            tmp = curr.prev;
            curr.prev = curr.next;
            curr.next = tmp;
            curr = curr.prev;
        }
        return tmp.prev;
    }

    public static class DoublyLinkedList{
        int val;
        DoublyLinkedList prev;
        DoublyLinkedList next;

        public DoublyLinkedList(int val){
            this.val = val;
        }
    }

    public static void print(DoublyLinkedList head){
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void main(String[] args){
        DoublyLinkedList node1 = new DoublyLinkedList(1);
        DoublyLinkedList node2 = new DoublyLinkedList(2);
        DoublyLinkedList node3 = new DoublyLinkedList(3);
        node1.prev = null;
        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        node3.next = null;

        print(node1);
        System.out.println();
        print(reverse(node1));
    }
}
