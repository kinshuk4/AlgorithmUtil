package com.vaani.algo.temp;

import java.util.*;

public class ScreeningTest {
    public static void main(String[] args) {
        List<Integer> data1 = new LinkedList<Integer>(Arrays.asList(1, 4, 7));
        List<Integer> data2 = new LinkedList<Integer>(Arrays.asList(123, -2, 477, 3, 14, 6551));
                
        ScreeningTest obj = new ScreeningTest();

        int result = obj.fold(data1);
        System.out.println(result);

        int yourAnswer = obj.fold(data2); //what is the answer for this one???
        System.out.println(yourAnswer);
    }

    private int fold(List<Integer> input) {
    	if(input==null || input.size() == 0){
    		return -1;
    	}
    	int a = input.remove(0);
    	System.out.println(input);
    
        return foldHelper(input,a);
    }
    
    private int foldHelper(List<Integer> input, int a){
        if(input.size()==1){
            return input.get(0)+a;
        }else{
        	
            for(int i=0;i<input.size();i++){
            	int val = input.remove(i)+a;
            	
                input.add(i, val);
            }
            int el = input.get(0);
            input.remove(0);
            return foldHelper(input, el);
            
        }
    }
}
