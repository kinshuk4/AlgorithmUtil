package com.vaani.algo.compete.company.wallethub.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class WayBack {

	/*
	 * 6
Begin on Old Madras Road
Left on Domlur Flyover
Left on 100 Feet Road
Right on Sarjapur Road
Right on Hosur Road
Right on Ganapathi Temple Road
	 */
	
	public static String BEGIN = "Begin on ";
	public static String RIGHT = "Right on ";
	public static String LEFT = "Left on ";
	 public static void main(String args[] ) throws Exception {
	        /*
	         * Read input from stdin and provide input before running
	         * Use either of these methods for input
	*/
//	        //BufferedReader
//	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	        String line = br.readLine();
//	        int N = Integer.parseInt(line);

		 	List<String> directionList = new ArrayList<String>();
		 	
	        //Scanner
	        Scanner s = new Scanner(System.in);
	        int N = s.nextInt();
	        s.nextLine();

	        for (int i = 0; i < N; i++) {
	        	
	            directionList.add(s.nextLine());
	            
	        }
	        Collections.reverse(directionList);
	        List<String> results = new ArrayList<>();
	        boolean left = false;//means right
	        for(int i=0;i<N;i++){
	        	String dir = directionList.get(i);
	        	String dir2 = "" ;
	        	if(i==0){
	        		dir2 =dir;
	        		if(dir2.contains(LEFT)){
	        			left = false;
	        			dir2 = dir2.replace(LEFT,"");
	        		}else if( dir2.contains(RIGHT)){
	        			left = true;
	        			dir2 = dir2.replace(RIGHT,"");
	        		}        		
	        		dir2 = BEGIN+dir2;
	        	}else{
	        		dir2 = dir;
	        		if(left){

        				dir2 = dir2.replace(RIGHT,LEFT);
        				dir2 = dir2.replace(BEGIN,LEFT);
        				
        			}else{
        				dir2 = dir2.replace(LEFT,RIGHT);
        				dir2 = dir2.replace(BEGIN,RIGHT);
        			}
	        		if(dir.contains(RIGHT)){
	        			
	        			left = true;
	        			
	        			
	        		}else if(dir.contains(LEFT)){
	        			left = false;
	        			
	        		}
	        	}
	        	results.add(dir2);
	        	
	        }
	        
	        for(String s2:results){
	        	System.out.println(s2);
	        }

	    }
}
