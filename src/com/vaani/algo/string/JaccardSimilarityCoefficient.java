package com.vaani.algo.string;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

// https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fstrings%2FJaccardSimilarityCoefficient.java
public class JaccardSimilarityCoefficient {
    public static double calcBySets(String s0, String s1) {
        if (s0.isEmpty() && s1.isEmpty()) {
            return 1.0;
        }

        Set<Character> words0 = new HashSet<Character>();
        Set<Character> words1 = new HashSet<Character>();
        for (int i = 0; i < s0.length(); i++) {
            words0.add(s0.charAt(i));
        }
        for (int i = 0; i < s1.length(); i++) {
            words1.add(s1.charAt(i));
        }

        double intersect = Sets.intersection(words0, words1).size();
        double union = Sets.union(words0, words1).size();

        System.out.println(Sets.union(words0, words1));
        return intersect / union;
    }

    public double getJSC(String str1, String str2) {
        if (StringUtils.isEmpty(str1) && StringUtils.isEmpty(str2)) {
            return 1;
        }
        Set<Character> s1 = new HashSet<Character>();
        Set<Character> s2 = new HashSet<Character>();
        str1 = Preconditions.checkNotNull(str1);
        str2 = Preconditions.checkNotNull(str2);
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        for (char c : chars1) {
            s1.add(c);
        }
        for (char c : chars2) {
            s2.add(c);
        }

        //求交集
        Set<Character> intersection = new HashSet<Character>();
        intersection.addAll(s1);
        intersection.retainAll(s2);

        //求并集
        Set<Character> union = new HashSet<Character>();
        union.addAll(s1);
        union.addAll(s2);

        return (double) intersection.size() / union.size();
    }

}
