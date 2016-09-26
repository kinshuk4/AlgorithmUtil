package com.vaani.algo.compete.hackerrank.randomchallenges;

/**
 * Created by mykola on 13.05.15.
 */
public class OneEditionDifference {

    public static final void main(String... args) {
        System.out.println(isOneEdit("car", "cor"));        //true
        System.out.println(isOneEdit("card", "cor"));       //false
        System.out.println(isOneEdit("car", "card"));       //true
        System.out.println(isOneEdit("carpin", "crpin"));   //true
        System.out.println(isOneEdit("coe", "cao"));        //false
        System.out.println(isOneEdit("coe", "coesfo"));     //false
    }

    private static boolean isOneEdit(String first, String second) {
        String longer = first.length() > second.length() ? first : second;
        String shorter = first.length() > second.length() ? second : first;
        boolean lengthDifferent = longer.length() != shorter.length();
        if (lengthDifferent && longer.length() - shorter.length() > 1) {
            return false;
        }
        char[] longerChars = longer.toCharArray();
        char[] shorterChars = shorter.toCharArray();
        boolean editionApplied = false;
        boolean decrementShorter = false;
        for (int i = 0; i < longerChars.length; i++) {
            if(i == shorterChars.length && !decrementShorter){
                return true;
            }
            if (shorterChars[decrementShorter ? i - 1 : i] != longerChars[i]) {
                if (editionApplied) {
                    return false;
                } else {
                    editionApplied = true;
                    if (lengthDifferent) {
                        decrementShorter = true;
                    }
                }
            }
        }
        return true;
    }

}
