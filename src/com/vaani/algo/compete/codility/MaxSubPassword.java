package com.vaani.algo.compete.codility;

public class MaxSubPassword {

    public static int solution(final String pass) {
        int validPassLength = -1;
        final String [] strs = pass.split("[0-9]");
        for (String s : strs) {
            final int subStringMaxLength = subStringPassLength(s);
            if(subStringMaxLength > validPassLength) {
                validPassLength = subStringMaxLength;
            }
        }
        return validPassLength;
    }
    
    private static int subStringPassLength(final String subString) {
        final char [] chars = subString.toCharArray();
        int subStringMaxLength = -1;
        for (int i = 0 ; i < chars.length ; i ++) {
            if (Character.isUpperCase(chars[i])) {
                subStringMaxLength = chars.length;
                break;
            }
        }
        return subStringMaxLength;
}
    public static void main(String[] args){
    	System.out.println(solution("a0Ba"));
    }
}
