package com.vaani.algo.ds.core.stack;

public interface IStack {
    void push(Object x);

    Object pop();

    Object top();

    boolean isEmpty();

    void makeEmpty();
}