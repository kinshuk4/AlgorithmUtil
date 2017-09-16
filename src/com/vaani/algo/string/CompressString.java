package com.vaani.algo.string;

public class CompressString {
    /*
     * Dynamic Programming approach
     * https://www.hackerrank.com/challenges/reduced-string

Shil has a string, , consisting of lowercase English letters. In one operation, he can delete any pair of adjacent letters with same value. For example, string "" would become either "" or "" after operation.

Shil wants to reduce as much as possible. To do this, he will repeat the above operation as many times as it can be performed. Help Shil out by finding and printing 's non-reducible form!

Note: If the final string is empty, print .

https://github.com/sojourner6/CompressString_HackerRank
     */
    public static String reduceString(String optimumString, String remString){
        System.out.println(optimumString + ", " + remString);
        if(optimumString.length() == 0 && remString.length() == 0){
            return "Empty String";
        }
        if(optimumString.length() == 0){
            return reduceString(remString.substring(0,1), remString.substring(1));
        }
        if(remString.length() == 0){
            return optimumString;
        }
        int optimumStringLen = optimumString.length();
        if(remString.length() < 2){
            if(optimumString.charAt(optimumStringLen - 1) == remString.charAt(0)){
                return reduceString(optimumString.substring(0, optimumStringLen - 1), "");
            }
            else{
                return optimumString + remString;
            }
        }
        else{
            if(optimumString.charAt(optimumStringLen - 1) == remString.charAt(0)){
                return reduceString(optimumString.substring(0, optimumStringLen - 1), remString.substring(1));
            }
            else{
                if(remString.charAt(0) == remString.charAt(1)){
                    return reduceString(optimumString, remString.substring(2));
                }
                else{
                    return reduceString(optimumString + remString.substring(0, 2), remString.substring(2));
                }
            }
        }
    }
}
