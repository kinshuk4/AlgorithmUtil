package com.vaani.algo.bits;

import java.util.ArrayList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * <p>
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 * <p>
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * <p>
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * <p>
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * <p>
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * <p>
 * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 * <p>
 * Created by Xiaomeng on 9/2/2014.
 */
public class GreyCode {
    public static void main(String[] args) {
        GreyCode test = new GreyCode();
        System.out.println(test.grayCode(2));
    }

    /**
     * Reference:
     * http://yucoding.blogspot.com/2013/01/leetcode-question-33-gary-code.html
     * http://www.cnblogs.com/TenosDoIt/p/3451938.html
     * http://www.cnblogs.com/lihaozy/archive/2012/12/31/2840437.html
     * http://www.matrix67.com/blog/archives/266 (Very intuitive!)
     * <p>
     * 二进制数   Gray码
     * 000       000
     * 001       001
     * 010       011
     * 011       010
     * 100       110
     * 101       111
     * 110       101
     * 111       100
     * <p>
     * 从二进制数的角度看，“镜像”位置上的数即是对原数进行not运算后的结果。比如，第3个数010和倒数第3个数101的每一位都正好相反。
     * 假设这两个数分别为x和y，那么x xor (x shr 1)和y xor (y shr 1)的结果只有一点不同：后者的首位是1，前者的首位是0。
     * 而这正好是Gray码的生成方法。这就说明了，Gray码的第n个数确实是n xor (n shr 1)。
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        int count = (int) Math.pow(2, n);
        for (int i = 0; i < count; i++) {
            result.add((i >> 1) ^ i);
        }
        return result;
    }
}
