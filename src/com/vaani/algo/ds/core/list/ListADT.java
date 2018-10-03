package com.vaani.algo.ds.core.list;
import java.util.Iterator;
 public interface ListADT<T, V> {
     void setDescription(String izena);
     String getDescription ();
     T removeFirst();
     T removeLast();
     boolean remove(T elem);
     T first();
     T last();
     boolean contains(T elem);
     V find(T elem);
     boolean isEmpty();
     int size();
     Iterator<T> iterator();
}
