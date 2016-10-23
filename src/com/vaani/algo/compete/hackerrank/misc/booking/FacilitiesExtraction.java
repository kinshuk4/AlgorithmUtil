package com.vaani.algo.compete.hackerrank.misc.booking;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


/**
 * Not an optimized solution as complexity is O(N^2).Use trie to optimize it.
 * https://www.toptal.com/java/the-trie-a-neglected-data-structure
 * Also for this programming problem,encapsulation is not adhered.
 * 
 * */
 
class FacilitiesExtraction {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        List<String> facs = new ArrayList<>();
        for(int i =0;i<N;i++){
            facs.add(sc.nextLine());               
        }
        String text = sc.nextLine().toLowerCase();
        List<String> matchedFacs = new ArrayList<>();
        for(String fac:facs){
            if(text.indexOf(fac.trim().toLowerCase())>0){
            	matchedFacs.add(fac);
            }
        }
        Collections.sort(matchedFacs);
        for(String fac:matchedFacs){
        	System.out.println(fac);
        }
        
    }
    
}
