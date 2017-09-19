package com.vaani.algo.compete.hackerrank.amazon;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

/**
 * Anagram Difference
 * <p>
 * We define an anagram to be a word whose characters can be rearranged to create another word. Given two strings, we want to know the minimum number of characters already in either string that we must modify to make the two strings anagrams; if it’s not possible to make the two strings anagrams, we consider this number to be -1. For example:
 * <p>
 * tea and ate are anagrams, so we would need to modify a minimum of 0 characters.
 * tea and toe are not anagrams, but we can modify a minimum of 1 character in either string to make them anagrams.
 * act and acts are not anagrams and cannot be converted to anagrams because they contain different numbers of characters, so the minimum number of characters to modify is -1.
 * Complete the function from the provided code. It has two parameters:
 * <p>
 * An array of n strings, a.
 * An array of n strings, b.
 * The function must return an array of integers where each element i denotes the minimum number of characters you must modify to make ai and bi anagrams; if it’s not possible to modify the existing characters in a[i] and b[i] to make them anagrams, element i should be -1 instead.
 * <p>
 * Note: You can only modify existing characters in the strings, you cannot delete or append characters to change a string’s length.
 * <p>
 * Input Format
 * <p>
 * The provided code reads the following input from stdin and passes it to the function:
 * <p>
 * The first line contains an integer, n, denoting the number of elements in a.
 * <p>
 * Each line i of the n subsequent lines contains an integer describing a[i].
 * <p>
 * The next line contains an integer, n, denoting the number of elements in b.
 * <p>
 * Each line i of the n subsequent lines contains an integer describing b[i].
 * <p>
 * Constraints
 * <p>
 * Each string consists of lowercase English alphabetic letters (i.e., a to z).
 * 1 ≤ n ≤ 100
 * It is guaranteed that a and b contain the same number of elements.
 * 0 ≤ length of a[i], length of b[i] ≤ 10^4
 * 1 ≤ length of a[i] + length of b[i] ≤ 10^4
 * Output Format
 * <p>
 * The function must return an array of integers where each element i denotes the minimum number of characters you must modify to make a[i] and b[i] anagrams; if it’s not possible to modify the existing characters in a[i] and b[i] to make them anagrams, element i should be -1 instead. This is printed to stdout by the provided code.
 * <p>
 * Sample Input 0
 * <p>
 * 5
 * a
 * jk
 * abb
 * mn
 * abc
 * 5
 * bb
 * kj
 * bbc
 * op
 * def
 * Sample Output 0
 * <p>
 * -1
 * 0
 * 1
 * 2
 * 3
 * Explanation 0
 * <p>
 * Given a = [a, jk, abb, mn, abc] and b = [bb, kj, bbc, op, def], we perform the following n = 5 calculations:
 * <p>
 * Index 0: a and bb cannot be anagrams because they contain different numbers of characters, so we return -1 at this index.
 * Index 1: jk and kj are already anagrams because they both contain the same characters at the same frequencies, so we return 0 at this index.
 * Index 2: abb and bbc differ by a minimum of one character, so we return 1 at this index.
 * Index 3: mn and op differ by a minimum of two characters, so we return 2 at this index.
 * Index 4: abc and def differ by a minimum of three characters, so we return 3 at this index.
 * After checking each pair of strings, we return the array [-1, 0, 1, 2, 3] as our answer.
 */

public class AnagramDifference {
    public static void main(String[] args) {
        String[] a = {"a", "jk", "abb", "mn", "abc", "hhpddlnnsjfoyxpci"};
        String[] b = {"bb", "kj", "bbc", "op", "def", "ioigvjqzfbpllssuj"};
        System.out.println(Arrays.toString(getMinimumDifference(a, b)));
        System.out.println(Arrays.toString(getMinimumDifferenceBest(a, b)));

        System.out.println(Arrays.toString(simpleButWillNotWorkGetMinimumDifference(a, b)));

    }

    private static int[] getMinimumDifference(String[] a, String[] b) {


        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (a[i].length() != b[i].length()) {
                result[i] = -1;
            } else {
                List<String> anagramsList = new LinkedList<>(Arrays.asList(a[i].split("")));
                Collections.sort(anagramsList);
                List<String> blist = Arrays.asList(b[i].split(""));

                for (String s : blist) {
                    int index = Collections.binarySearch(anagramsList, s);
                    if (index >= 0) {
                        anagramsList.remove(index);
                    }
                }
                result[i] = anagramsList.size();
            }
        }

        return result;
    }

    private static int[] getMinimumDifferenceBest(String[] a, String[] b) {


        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (a[i].length() != b[i].length()) {
                result[i] = -1;
            } else {

                result[i] = getAnagramDifferenceForStringPair(a[i], b[i])/2;
            }
        }

        return result;
    }

    static int getAnagramDifferenceForStringPair(String str1, String str2) {

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for (int i = 0; i < str1.length(); i++)
            count1[str1.charAt(i) - 'a']++;

        for (int i = 0; i < str2.length(); i++)
            count2[str2.charAt(i) - 'a']++;

        int result = 0;

        for (int i = 0; i < 26; i++)
            result += abs(count1[i] - count2[i]);
        return result;
    }

    private static int[] simpleButWillNotWorkGetMinimumDifference(String[] firstAnagrams, String[] secondAnagrams) {
        int[] anagramsResolution = new int[firstAnagrams.length];
        for (int i = 0; i < firstAnagrams.length; i++) {
            if (firstAnagrams[i].length() != secondAnagrams[i].length()) {
                anagramsResolution[i] = -1;
            } else {
                List<String> anagramsList = new ArrayList<>(Arrays.asList(firstAnagrams[i].split("")));
                anagramsList.removeAll(Arrays.asList(secondAnagrams[i].split("")));
                anagramsResolution[i] = anagramsList.size();
            }
        }

        return anagramsResolution;
    }

    private static int[] getMinimumDifference3NotWorking(String[] a, String[] b) {

        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (a[i].length() != b[i].length()) {
                result[i] = -1;
            } else {
                HashMap<Character, Integer> freqency = getFrequencyMap(a[i]);
                char[] bArr = b[i].toCharArray();

                for (char c : bArr) {
                    if (freqency.containsKey(new Character(c))) {
                        freqency.put(c, freqency.get(new Character(c)) - 1);
                    }
                }
                int y = 0;
                System.out.println(freqency);
                result[i] = freqency.entrySet().stream().filter(x -> x.getValue() != y).map(Map.Entry::getKey).collect(Collectors.toSet()).size();
            }
        }

        return result;
    }

    private static HashMap<Character, Integer> getFrequencyMap(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer val = map.get(new Character(c));
            if (val != null) {
                map.put(c, new Integer(val + 1));
            } else {
                map.put(c, 1);
            }
        }
        return map;

    }

}
