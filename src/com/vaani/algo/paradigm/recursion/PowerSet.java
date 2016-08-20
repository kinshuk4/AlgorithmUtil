package com.vaani.algo.paradigm.recursion;

/**
 * 
 * Power set works on the idea that you have a subset in power set. You can add element to it to create new subset that doesn't exists.
 *
 *
 *Is the set passed empty? Done
If not, take an element out
recursively call method on the remainder of the set
return the set composed of the Union of 
(1) the powerset of the set without the element (from the recursive call) 
(2) this same set (i.e., (1)) but with each element therein unioned with the element initially taken out
Go backwards:

powerset(S) when S = {()} is {()}

powerset(S) when S = {(a)} is {(), (a)}

powerset(S) when S = {(a,b)} is {(), (a), (b), (a,b)}


 */



public class PowerSet {
	
	public static void main(String args[]) {
		
		String input="1234";
//		for(int i=0;i<input.length();i++)
		powerset("",input,3);
	}
	
	
	private static void nselectc(String input,int count){
		
	}
	
	
	private static void powerset(String prefix,String input,int count) {
		
		if(count==0){
			System.out.println(prefix);
			return;			
		}
		if(input.length()<count){
			return;
		}
		
		char[] a=input.toCharArray();
		
		for(int i=0;i<a.length;i++){
			String temp=getRestString(a,i);
			powerset(prefix+a[i],temp,count-1);
			powerset(prefix,temp,count);
		}
		
		return;	
	}
	
	
	private static String getRestString(char[] arr, int i) {
		StringBuilder sb=new StringBuilder();
		int j=0;
		for(char ch:arr){
			if(j!=i)
				sb.append(ch);
			j++;
		}
		return sb.toString();
	}
}

