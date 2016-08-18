package com.vaani.algo.search.string;

/* Title:     Knuth-Morris-Pratt string matching algorithm
 * Author:    H.W. Lang
 *            Fachhochschule Flensburg, University of Applied Sciences
 *            Flensburg, Germany
 * Date:      2007
 * Mail:      lang@fh-flensburg.de
 * Web:       http://www.inf.fh-flensburg.de/lang/algorithmen/pattern/kmpen.htm
 * Reference: D.E. Knuth, J.H. Morris, V.R. Pratt: Fast Pattern Matching in Strings.
 *            SIAM Journal of Computing 6, 2, 323-350 (1977)
 */

public class KmpStringMatcher {
    private static String name = "Knuth-Morris-Pratt";
    private char[] pattern, text;       // pattern, text
    private int patternLength, textLength;          // pattern length, text length
    private String matches;    // string of match positions
    private char[] showmatches;// char array that shows matches
    private int[] b;           // used by the KMP algorithm

    // only for test purposes
    public static void main(String[] args) {
        KmpStringMatcher kmpMatcher = new KmpStringMatcher();
        System.out.println(name);

        String text_, pattern_;
        text_ = "abcdabcd";
        pattern_ = "abc";
        kmpMatcher.search(text_, pattern_);
        System.out.println(pattern_);
        System.out.println(text_);
        System.out.println(kmpMatcher.showmatches);
        System.out.println(kmpMatcher.getMatches());

        text_ = "abababaa";
        pattern_ = "aba";
        kmpMatcher.search(text_, pattern_);
        System.out.println(pattern_);
        System.out.println(text_);
        System.out.println(kmpMatcher.showmatches);
        System.out.println(kmpMatcher.getMatches());
    }

    /**
     * searches the text tt for the pattern pp
     */
    public void search(String tt, String pp) {
        setText(tt);
        setPattern(pp);
        kmpSearch();
    }

    /**
     * sets the text
     */
    private void setText(String text_) {
        textLength = text_.length();
        text = text_.toCharArray();
        initmatches();
    }

    /**
     * sets the pattern
     */
    private void setPattern(String pattern_) {
        patternLength = pattern_.length();
        pattern = pattern_.toCharArray();
        b = new int[patternLength + 1];
        kmpPreprocess();
    }

    /**
     * initializes match positions and the array showmatches
     */
    private void initmatches() {
        matches = "";
        showmatches = new char[textLength];
        for (int i = 0; i < textLength; i++)
            showmatches[i] = ' ';
    }

    /**
     * preprocessing of the pattern
     */
    private void kmpPreprocess() {
        int i = 0, j = -1;
        b[i] = j;
        while (i < patternLength) {
            while (j >= 0 && pattern[i] != pattern[j])
                j = b[j];
            i++;
            j++;
            b[i] = j;
        }
    }

    /**
     * searches the text for all occurences of the pattern
     */
    private void kmpSearch() {
        int i = 0, j = 0;
        while (i < textLength) {
            while (j >= 0 && text[i] != pattern[j])
                j = b[j];
            i++;
            j++;
            if (j == patternLength) // a match is found
            {
                report(i - patternLength);
                j = b[j];
            }
        }
    }

    /**
     * reports a match
     */
    private void report(int i) {
        matches += i + " ";
        showmatches[i] = '^';
    }

    /**
     * returns the match positions after the search
     */
    public String getMatches() {
        return matches;
    }

}    // end class KmpStringMatcher
