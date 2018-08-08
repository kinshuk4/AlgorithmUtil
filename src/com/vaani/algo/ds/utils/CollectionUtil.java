package com.vaani.algo.ds.utils;

import java.util.*;

public class CollectionUtil {
    public static int[] convertIntegerListToIntArray(List<Integer> list){
        int[] ret = new int[list.size()];
        Set setToLook = new HashSet<>(Arrays.asList(ret));
        int i = 0;
        for (Integer e : list)
            ret[i++] = e;
        return ret;
    }
}
