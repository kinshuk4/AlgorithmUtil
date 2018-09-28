package com.vaani.algo.ds.algos.list.dll;

import com.vaani.algo.ds.core.list.dll.DoubleLinkedList;
import com.vaani.algo.ds.core.list.dll.DLLNode;

import static com.vaani.algo.ds.utils.DLLUtil.reverseList;

// http://www.geeksforgeeks.org/reverse-a-doubly-linked-list/
public class ReverseDLL {



    public static void main(String a[]) {
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();
        linkedList.add(11);
        linkedList.add(22);
        linkedList.add(33);
        linkedList.add(44);
        linkedList.add(55);
        linkedList.add(66);
//        linkedList.printList();
        reverseList(linkedList);
//        linkedList.printList();
    }
}