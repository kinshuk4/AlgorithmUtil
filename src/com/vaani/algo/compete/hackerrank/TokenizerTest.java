package com.vaani.algo.compete.hackerrank;

import java.util.StringTokenizer;

/**
 * Created by anderson.ito on 25/07/2016.
 */
public class TokenizerTest {
    public static void main(String[] args) {
        StringTokenizer tokenizer = new StringTokenizer("/home/login", "/");
        while(tokenizer.hasMoreElements()) {
            System.out.println("Token: " + tokenizer.nextToken());
        }
    }
}
