package com.vaani.algo.ds.core.list.dll;

public final class DoubleListNode<T> {
    public T value;
    public DoubleListNode<T> prev;
    public DoubleListNode<T> next;

    public DoubleListNode(T obj) {
        this(null, obj, null);
    }

    public DoubleListNode(DoubleListNode<T> previous, T value, DoubleListNode<T> next) {
        this.prev = previous;
        this.value = value;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public DoubleListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoubleListNode<T> prev) {
        this.prev = prev;
    }

    public DoubleListNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleListNode<T> next) {
        this.next = next;
    }

    // A utility function to find last node of linked list
    public <T> DoubleListNode lastNode(DoubleListNode<T> node) {
        while (node.next != null)
            node = node.next;
        return node;
    }


//	public T  TreeNode<T> convertDLLNodeToTreeNode(DoubleListNode node) {
//		if(node!=null){
//			return new TreeNode<T>(convertDLLNodeToTreeNode(node.prev), node.value, convertDLLNodeToTreeNode(node.next));
//		}else{
//			return null;
//		}
//	}


}