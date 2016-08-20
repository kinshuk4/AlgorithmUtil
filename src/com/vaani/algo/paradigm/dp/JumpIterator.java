package com.vaani.algo.paradigm.dp;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 写jump iterator类, 构造函数传入一个普通的iterator, 然后实现next(), hasNext(). next()返回传入iterator的next().next(), 就是每次跳过一个元素输出.
 * <p>
 * http://stackoverflow.com/questions/28174339/how-to-implement-a-jump-iterator
 */
public class JumpIterator implements Iterator<Integer> {
    private Iterator<Integer> it;

    public JumpIterator(List<Integer> list) {
        it = list.iterator();
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 9);
        JumpIterator it = new JumpIterator(list);
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public Integer next() {
        int val = it.next();
        if (it.hasNext()) it.next();
        return val;
    }

    @Override
    public void remove() {

    }
}
