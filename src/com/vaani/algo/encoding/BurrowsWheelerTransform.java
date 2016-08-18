package com.vaani.algo.encoding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BurrowsWheelerTransform {

    public String bwt(String str, String eof) {
        str = str + eof;
        List<String> strList = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            sb.append(str.substring(i));
            sb.append(str.substring(0, i));
            strList.add(sb.toString());
            sb.setLength(0);
        }

        Collections.sort(strList);

        for (String s : strList) {
            sb.append(s.charAt(s.length() - 1));
        }
        return sb.toString();
    }

    public String reverse(String encoded, String eof) {
        List<StringBuilder> table = new ArrayList<StringBuilder>();
        for (int i = 0; i < encoded.length(); ++i) {
            table.add(new StringBuilder());
        }

        for (int i = 0; i < encoded.length(); ++i) {
            for (int j = 0; j < encoded.length(); ++j) {
                table.get(j).insert(0, encoded.charAt(j));
            }
            Collections.sort(table, new Comparator<StringBuilder>() {
                public int compare(StringBuilder sb1, StringBuilder sb2) {
                    return sb1.toString().compareTo(sb2.toString());
                }
            });
        }
        for (StringBuilder sb : table) {
            if (sb.substring(sb.length() - 1).equals(eof)) {
                return sb.toString().substring(0, sb.length() - 1);
            }
        }
        return null;
    }

}
