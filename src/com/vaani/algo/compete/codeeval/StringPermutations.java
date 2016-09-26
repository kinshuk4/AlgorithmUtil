package com.vaani.algo.compete.codeeval;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by mykola on 12.05.15.
 */
public class StringPermutations {

    public static void main(String... args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line;
        while ((line = reader.readLine()) != null) {
            printPermutations(line);
            System.out.println();
        }
    }

    private static void printPermutations(String word) {
        char[] wordChars = word.toCharArray();
        Arrays.sort(wordChars);
        addPermutations(wordChars, 0, word.length() > 1);
    }

    private static void addPermutations(char[] word, int index, boolean last) {
        for (int i = index; i < word.length; i++) {
            char[] permutation = Arrays.copyOf(word, word.length);
            swap(permutation, i, index);
            boolean currentLast =  i == word.length - 1;
            if (index < word.length - 1) {
                addPermutations(permutation, index + 1, currentLast && last);
            } else {
                System.out.print(new String(permutation));
                if(!last){
                    System.out.print(",");
                }
            }
        }
    }

    private static void swap(char[] word, int f, int s) {
        int min = Math.min(f, s);
        int max = Math.max(f, s);
        for (int i = max; i > min; i--) {
            char buffer = word[i];
            word[i] = word[i - 1];
            word[i - 1] = buffer;
        }
    }

}
