package com.vaani.algo.ds.stack.impl;

public interface IStack {
    void push(Object x);
    Object pop();
    Object top();
    boolean isEmpty();
    void makeEmpty();
}