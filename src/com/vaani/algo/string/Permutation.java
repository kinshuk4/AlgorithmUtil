package com.vaani.algo.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutation {

    public static void findPerms(List<String> permsList, String mystr, String chars) {

        if (chars.length() <= 1)
            permsList.add(mystr + chars);
        else
            for (int i = 0; i < chars.length(); i++) {
                String newString = chars.substring(0, i) + chars.substring(i + 1);
                findPerms(permsList, mystr + chars.charAt(i), newString);
            }

    }

    public static List<String> getPermutations(String chars) {
        List<String> myList = new ArrayList<String>();
        findPerms(myList, "", chars);

        return myList;

    }

    public static List<String> getSortedPermutations(String chars) {
        List<String> strList = getPermutations(chars);
        Collections.sort(strList);
        return strList;
    }


    public static void main(String args[]) {
        String chars = "help";
        List<String> strList = getSortedPermutations(chars);
        for (String str : strList)
            System.out.print(" " + str);


    }

}
