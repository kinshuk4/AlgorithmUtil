package com.vaani.algo.ds.algos.list.dll;

import com.vaani.algo.ds.core.list.dll.DLLNode;

/**
 * Created by kchandra on 21/08/16.
 * http://www.geeksforgeeks.org/quicksort-for-linked-list/
 */
public class QuickSortDLL {
    /* Considers last element as pivot, places the pivot element at its
   correct position in sorted array, and places all smaller (smaller than
   pivot) to left of pivot and all greater elements to right of pivot */
    DLLNode partition(DLLNode<Integer> l, DLLNode<Integer> h)
    {
        // set pivot as h element
        int x = h.value;

        // similar to i = l-1 for array implementation
        DLLNode<Integer> i = l.prev;

        // Similar to "for (int j = l; j <= h- 1; j++)"
        for(DLLNode<Integer> j = l; j!=h; j=j.next)
        {
            if(j.value <= x)
            {
                // Similar to i++ for array
                i = (i==null) ? l : i.next;
                int temp = i.value;
                i.value = j.value;
                j.value = temp;
            }
        }
        i = (i==null) ? l : i.next;  // Similar to i++
        int temp = i.value;
        i.value = h.value;
        h.value = temp;
        return i;
    }

    /* A recursive implementation of quicksort for linked list */
    void _quickSort(DLLNode l, DLLNode h)
    {
        if(h!=null && l!=h && l!=h.next){
            DLLNode temp = partition(l,h);
            _quickSort(l,temp.prev);
            _quickSort(temp.next,h);
        }
    }

    // The main function to sort a linked list. It mainly calls _quickSort()
    public void quickSort(DLLNode node)
    {
        // Find last node
        DLLNode head = node.getLastNode(node);

        // Call the recursive QuickSort
        _quickSort(node,head);
    }
}
