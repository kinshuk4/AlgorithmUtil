package com.vaani.algo.compete.codility;

public class IntMapper {
    
    public int solution(final int A , final int B) {
        int zipped = -1;
        if(A > 10000000 || B > 10000000) {
            return zipped;
        }
        final String strA = Integer.toString(A);
        final String strB = Integer.toString(B);
        StringBuilder zippedString = new StringBuilder();
        final int length = strA.length() > strB.length() ? strA.length() : strB.length();
        for(int i = 0 ; i < length; i++) {
            if(strA.length() > i) {
                zippedString.append(strA.charAt(i));
            }
            if(strB.length() > i) {
                zippedString.append(strB.charAt(i));
            }
        }
        try {
            zipped = Integer.parseInt(zippedString.toString());
        } catch(NumberFormatException e) {
            zipped = -1;
        }
        return zipped > 10000000 ? -1 : zipped;
    }

}
