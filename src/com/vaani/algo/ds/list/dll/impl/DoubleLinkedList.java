package com.vaani.algo.ds.list.dll.impl;



import java.util.Enumeration;



public class DoubleLinkedList <E>{   
	public DoubleListNode<E> head;
	private DoubleListNode<E> tail;
	private int size=0;
	/*
	 * creates an empty list
	 */
	public DoubleLinkedList() {
		//	    head = new DoubleListNode<E>(null);
		//	    head.next = head.prev = head;
		head.setPrev(null);
		head.setNext(tail);
		tail.setPrev(head);
		tail.setNext(null);
	}

	public DoubleListNode<E> get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else {
			DoubleListNode<E> cursor = head;
			for (int i = 0; i < index; i++) {
				cursor = cursor.getNext();
			}
			return cursor;
		}
	}

	public E remove(int index) throws IndexOutOfBoundsException {
		if (index == 0) {
			throw new IndexOutOfBoundsException();
		} else {
			DoubleListNode<E> result = get(index);
			result.getNext().setPrev(result.getPrev());
			result.getPrev().setNext(result.getNext());
			size--;
			return result.getValue();
		}
	}
	/*
	 * remove all elements in the list
	 */
	public final  void clear() {
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

	//		public boolean isEmpty() {
	//			return length == 0;
	//		}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("(head) - ");
		DoubleListNode<E> temp = head;
		while (temp.getNext() != tail) {
			temp = temp.getNext();
			result.append(temp.getValue() + " - ");
		}
		result.append("(tail)");
		return result.toString();
	}
	
	public void add(E value){
		DoubleListNode<E> newNode = new DoubleListNode<>(null, value, null);
        if (head == null) { // list empty
            head = newNode;
        } else { // add to the end of list
        	DoubleListNode<E> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
            newNode.prev = curr;
        }
        size++;
//        return true;
	}

	public void add(int index, E value) throws IndexOutOfBoundsException {
		DoubleListNode<E> cursor = get(index);
		DoubleListNode<E> temp = new DoubleListNode<E>(value);
		temp.setPrev(cursor);
		temp.setNext(cursor.getNext());
		cursor.getNext().setPrev(temp);
		cursor.setNext(temp);
		size++;
	}


	public void addHead(E value) {
		DoubleListNode<E> cursor = head;
		DoubleListNode<E> temp = new DoubleListNode<E>(value);
		temp.setPrev(cursor);
		temp.setNext(cursor.getNext());
		cursor.getNext().setPrev(temp);
		cursor.setNext(temp);
		size++;
	}

	public void addTail(E value) {
		DoubleListNode<E> cursor = tail.getPrev();
		DoubleListNode<E> temp = new DoubleListNode<E>(value);
		temp.setPrev(cursor);
		temp.setNext(cursor.getNext());
		cursor.getNext().setPrev(temp);
		cursor.setNext(temp);
		size++;
	}

	//	/*
	//	 * insert element after current position
	//	 */
	//	public final synchronized void insertAfter(E obj, DoubleListIterator cursor) {
	//		DoubleListNode<E> newItem = new DoubleListNode<E>(cursor.pos, obj, cursor.pos.next);
	//		newItem.next.prev = newItem;
	//		cursor.pos.next = newItem;
	//	}

	//	/*
	//	 * insert element before current position
	//	 */
	//	public final  void insertBefore(E obj, DoubleListIterator cursor) {
	//		DoubleListNode<E> newItem = new DoubleListNode<E>(cursor.pos.prev, obj, cursor.pos);
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