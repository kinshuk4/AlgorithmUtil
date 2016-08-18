package com.vaani.algo.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse the csv string, considering the escapes.
 * <p>
 * Time complexity: O(N), space complexity: O(N).
 */
public class ParseCSV {

    public String[] parseCSV(String line) {
        boolean prevEscape = false;
        List<String> list = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); ++i) {
            char cur = line.charAt(i);
            if (cur == '\\' && !prevEscape) {
                prevEscape = true;
            } else if (cur == ',') {
                if (prevEscape) {
                    sb.append(',');
                    prevEscape = false;
                } else {
                    list.add(sb.toString().trim());
                    sb = new StringBuilder();
                }
            } else {
                sb.append(cur);
                prevEscape = false;
            }
        }
        list.add(sb.toString().trim());
        String[] res = new String[list.size()];
        list.toArray(res);
        return res;
    }

}
