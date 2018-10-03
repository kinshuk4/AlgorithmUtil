package com.vaani.algo.ds.core.list.dll;


import com.vaani.algo.ds.core.list.ListADT;
import sun.jvm.hotspot.debugger.windbg.DLL;

import java.util.Iterator;

//https://github.com/urtxintxa/DoubleLinkedList/blob/master/src/org/minakdev/DoubleLinkedList/ListADT.java
public class DoubleLinkedList<E> implements ListADT<E, DLLNode<E>> {
    public DLLNode<E> head;
    private DLLNode<E> tail;
    private int size = 0;

    public DLLNode<E> get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else {
            DLLNode<E> cursor = head;
            for (int i = 0; i < index; i++) {
                cursor = cursor.next;
            }
            return cursor;
        }
    }

    public boolean add(E value) {
        DLLNode<E> newNode = new DLLNode<>(value);
        if (head == null) { // list empty
            head = newNode;
            tail = newNode;
        } else { // add to the end of list
            DLLNode<E> curr = tail;
            curr.next = newNode;
            newNode.prev = curr;
            tail = newNode;
        }
        size++;
        return true;
    }

    public void add(int index, E value) throws IndexOutOfBoundsException {
        DLLNode<E> cursor = get(index);
        DLLNode<E> temp = new DLLNode<>(value);
        temp.setPrev(cursor);
        temp.setNext(cursor.getNext());
        cursor.getNext().setPrev(temp);
        cursor.setNext(temp);
        size++;
    }

    /**
     * adds element at the starting of the linked list
     *
     * @param value
     */
    public void addFirst(E value) {
        DLLNode<E> newNode = new DLLNode<>(value);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        size++;
    }

    public void addTail(E value) {
        DLLNode<E> newNode = new DLLNode<E>(value);

        if (head == null) {
            head = tail = newNode;
            return;
        }
        DLLNode<E> cursor = tail.getPrev();
        newNode.setPrev(tail);
        tail.setNext(newNode);
        tail = newNode;
        size++;
    }

    public void insertAfter(E prevValue, E value) throws Exception {
        DLLNode<E> prevNode = find(prevValue);
        if (prevNode == null) {
            throw new Exception("Cannot find the given node with prevValue" + prevValue);
        }
        DLLNode<E> newNode = new DLLNode<E>(value);

        // Make next of new node as next of prev_node
        newNode.next = prevNode.next;

        // Make the next of prev_node as new_node
        prevNode.next = newNode;

        // Make prev_node as previous of new_node
        newNode.prev = prevNode;

        // Change previous of new_node's next node
        if (newNode.next != null)
            newNode.next.prev = newNode;
        size++;
    }


    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else {
            DLLNode<E> result = get(index);
            result.next.prev = result.prev;
            result.prev.next = result.next;
            size--;
            return result.value;
        }
    }

    public final void clear() {
        head.next = head.prev = head;
    }

    /*
     * returns true if this container is empty.
     */
    public final boolean isEmpty() {
        return head.next == head;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    //		public boolean isEmpty() {
    //			return length == 0;
    //		}

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("(head) - ");
        DLLNode<E> temp = head;
        while (temp.getNext() != tail) {
            temp = temp.getNext();
            result.append(temp.value + " - ");
        }
        result.append("(tail)");
        return result.toString();
    }


    @Override
    public void setDescription(String izena) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    public boolean remove(E value) {
        DLLNode<E> current = find(value); // This finds Node: [B]

        // a node with data e doesn't exist
        if (current == null) {
            return false;
        }

        // get the next and previous node
        DLLNode<E> prevNode = current.prev;
        DLLNode<E> nextNode = current.next;

        // current node is head
        if (prevNode == null) {
            this.head = current.next;
            this.head.prev = null;
        }

        // current node is tail
        if (nextNode == null) {
            this.tail = current.prev;
            this.tail.next = null;
        }

        if (prevNode != null || nextNode != null) {
            DLLNode<E> temp = current.prev;
            temp.next = current.next;
            temp = current.next;
            temp.prev = current.prev;
        }

        return true;
    }

    @Override
    public E first() {
        return null;
    }

    @Override
    public E last() {
        return null;
    }

    @Override
    public boolean contains(E elem) {
        return false;
    }

    @Override
    public DLLNode<E> find(E elem) {
        return null;
    }

    //	/*
    //	 * insert element after current position
    //	 */
    //	public final synchronized void insertAfter(E obj, DoubleListIterator cursor) {
    //		DLLNode<E> newItem = new DLLNode<E>(cursor.pos, obj, cursor.pos.next);
    //		newItem.next.prev = newItem;
    //		cursor.pos.next = newItem;
    //	}

    //	/*
    //	 * insert element before current position
    //	 */
    //	public final  void insertBefore(E obj, DoubleListIterator cursor) {
    //		DLLNode<E> newItem = new DLLNode<E>(cursor.pos.prev, obj, cursor.pos);
    //		newItem.prev.next = newItem;
    //		cursor.pos.prev = newItem;
    //	}

    //	/*
    //	 * remove the element at current position
    //	 */
    //	public final synchronized void remove(DoubleListIterator cursor) throws Exception {
    //		if (isEmpty()) {
    //			throw new IndexOutOfBoundsException("empty list.");
    //		}
    //		if (cursor.pos == head) {
    //			throw new NoSuchElementException("cannot remove the head");
    //		}
    //		cursor.pos.prev.next = cursor.pos.next;
    //		cursor.pos.next.prev = cursor.pos.prev;
    //	}

    /*
     * Return an iterator positioned at the head.
     */
    public final DoubleListIterator head() {
        return new DoubleListIterator(this, head);
    }


}