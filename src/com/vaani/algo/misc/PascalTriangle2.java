package com.vaani.algo.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an index k, return the kth row of the Pascal's triangle.
 *
 * For example, given k = 3,
 * Return [1,3,3,1].
 *
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 *
 * Created by Xiaomeng on 8/26/2014.
 */
public class PascalTriangle2 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<Integer>();
        List<Integer> tmp = new ArrayList<Integer>();
        for(int i = 0; i <= rowIndex; i++){
            tmp.clear();
            for(int j = 0; j <= i; j++){
                if(j == 0 || j == i)
                    tmp.add(1);
                else
                    tmp.add(result.get(j - 1) + result.get(j));
            }
            result = new ArrayList<Integer>(tmp);
        }
        return result;
    }

    public static void main(String[] args){
        PascalTriangle2 test = new PascalTriangle2();
        System.out.println(test.getRow(3));
    }
}
