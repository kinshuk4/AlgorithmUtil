package com.vaani.algo.ds.algos.stack.stackinarray;

/**
 * Created by kchandra on 18/08/16.
 */

class StackNode {
    int value;
    int prev;

    StackNode(int value, int prev) {
        this.value = value;
        this.prev = prev;
    }
}

public class ThreeStacksInArray {
    private StackNode[] stackNodes = null;
    private static int CAPACITY = 10;
    private int freeListTop = 0;
    private int size = 0;
    private int[] stackPointers = {-1, -1, -1};

    ThreeStacksInArray() {
        stackNodes = new StackNode[CAPACITY];
        initFreeList();
    }

    private void initFreeList() {
        for (int i = 0; i < CAPACITY; i++) {
            stackNodes[i] = new StackNode(0, i + 1);
        }
    }

    public void push(int stackNum, int value) throws Exception {
        int freeIndex;
        int currentStackTop = stackPointers[stackNum - 1];
        freeIndex = getFreeNodeIndex();
        StackNode n = stackNodes[freeIndex];
        n.prev = currentStackTop;
        n.value = value;
        stackPointers[stackNum - 1] = freeIndex;
    }

    public StackNode pop(int stackNum) throws Exception {
        int currentStackTop = stackPointers[stackNum - 1];
        if (currentStackTop == -1) {
            throw new Exception("UNDERFLOW");
        }

        StackNode temp = stackNodes[currentStackTop];
        stackPointers[stackNum - 1] = temp.prev;
        freeStackNode(currentStackTop);
        return temp;
    }

    private int getFreeNodeIndex() throws Exception {
        int temp = freeListTop;

        if (size >= CAPACITY)
            throw new Exception("OVERFLOW");

        freeListTop = stackNodes[temp].prev;
        size++;
        return temp;
    }

    private void freeStackNode(int index) {
        stackNodes[index].prev = freeListTop;
        freeListTop = index;
        size--;
    }

    public static void main(String args[]) {
        // Test Driver
        ThreeStacksInArray mulStack = new ThreeStacksInArray();
        try {
            mulStack.push(1, 11);
            mulStack.push(1, 12);
            mulStack.push(2, 21);
            mulStack.push(3, 31);
            mulStack.push(3, 32);
            mulStack.push(2, 22);
            mulStack.push(1, 13);
            StackNode node = mulStack.pop(1);
            node = mulStack.pop(1);
            System.out.println(node.value);
            mulStack.push(1, 13);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
