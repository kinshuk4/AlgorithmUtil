package com.vaani.algo.string;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of words and a length l, format the text such that each line has exactly l characters and is fully justified on both the left and the right. Words should be packed in a greedy approach; that is, pack as many words as possible in each line. Add extra spaces when necessary so that each line has exactly l characters.
 * <p>
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right. For the last line of text and lines with one word only, the words should be left justified with no extra space inserted between them.
 * <p>
 * Example
 * <p>
 * For
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * and l = 16, the output should be
 * <p>
 * textJustification(words, l) = ["This    is    an",
 * "example  of text",
 * "justification.  "]
 */
public class TextJustification {

    static String[] textJustification(String[] words, int l) {
        List<List<String>> outputList = new LinkedList<>();

        List<String> newRow = new LinkedList<>();
        boolean freshRow = true;
        int currRowLength = 0;
        for (int i = 0; i < words.length; ) {
            String word = words[i];
            int wl = word.length();
            if (wl <= l && freshRow) {
                newRow = new LinkedList<>();
                outputList.add(newRow);
                newRow.add(word);
                freshRow = false;
                currRowLength += wl;
                i++;
            } else if ((wl + 1 + currRowLength) <= l && !freshRow) {
                newRow.add(word);
                currRowLength += (wl + 1);
                i++;
            } else {
                freshRow = true;
                currRowLength = 0;
            }
        }


        String[] output = new String[outputList.size()];
        int rowNum = 0;
        for (List<String> row : outputList) {
            StringBuilder sb = new StringBuilder();
            int rowSumWithoutSpace = 0;
            for (String word : row) {
                rowSumWithoutSpace += word.length();
            }
            int numSpaces = l - rowSumWithoutSpace;
            int nWordsInRow = row.size();

            //if row doesn't have 1 word and is not the last line
            if (nWordsInRow != 1 && rowNum != (outputList.size() - 1)) {

                int equalSpcDist = numSpaces / (row.size() - 1);
                int wastedSpcDist = numSpaces - (equalSpcDist * (row.size() - 1));

                for (int i = 0; i < row.size() - 1; i++) {
                    sb.append(row.get(i)).append(getSpcString(equalSpcDist));
                    if (i < wastedSpcDist) {
                        sb.append(" ");
                    }
                }
                sb.append(row.get(row.size() - 1));
            } else if (nWordsInRow == 1) {
                sb.append(row.get(0)).append(getSpcString(l - row.get(0).length()));

            } else if (rowNum == (outputList.size() - 1)) {

                for (int i = 0; i < row.size() - 1; i++) {
                    sb.append(row.get(i)).append(" ");
                }
                sb.append(row.get(row.size() - 1));
                int trailingSpc = numSpaces - (row.size() - 1);
                if (trailingSpc > 0) {
                    sb.append(getSpcString(trailingSpc));
                }

            }
            output[rowNum++] = sb.toString();
        }

        return output;
    }

    static String getSpcString(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] words = {
                "This",
                "is",
                "an",
                "example",
                "of",
                "text",
                "justification."
        };
        int l = 16;
        System.out.println(Arrays.toString(textJustification(words, l)));
    }
}
