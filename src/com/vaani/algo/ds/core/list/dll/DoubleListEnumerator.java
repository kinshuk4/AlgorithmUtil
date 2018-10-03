package com.vaani.algo.ds.core.list.dll;

import java.util.Enumeration;
import java.util.NoSuchElementException;

final class DoubleListEnumerator<E> implements Enumeration {  
	  private DoubleLinkedList<E> list;
	  private DoubleListIterator cursor;

	

	  DoubleListEnumerator(DoubleLinkedList<E> l) {
	    list = l;
	    cursor = list.head();
	    cursor.next();
	  }

	  public boolean hasMoreElements() {  
	    return cursor.pos != list.head;
	  }
	  
	  public Object nextElement() {
	    synchronized (list) {
	      if (cursor.pos != list.head) {
	        Object object = cursor.pos.value;
	        cursor.next();
	        return object;
	      }
	    }
	    try {
			throw new NoSuchElementException("ListEnumerator");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	  }
	}