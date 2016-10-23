package com.vaani.algo.compete.hackerrank.misc.booking;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


/**
 * For this programming problem,encapsulation is not adhered.
 * String split is better than StringTokenizer and BufferedReader is better than Scanner.
 * 
 * */
 
class TravelProfiles {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int N;
        N = in.nextInt();
        Hotel[] hotels = new Hotel[N];
        in.nextLine();
        for(int i =0;i<N;i++){
        	String current = in.nextLine();
            //System.out.println("current :"+current);
            StringTokenizer st = new StringTokenizer(current);
            Hotel h = new Hotel(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            while(st.hasMoreTokens()){
                h.addFac(st.nextToken());
            }
            hotels[i] = h;                    
        }
        Arrays.sort(hotels);
        int M;
        M = in.nextInt();
        in.nextLine();
        for(int i = 0;i<M;i++){
            String current = in.nextLine();
            String[] tokens = current.split("\\s");
            int price = Integer.parseInt(tokens[0]);
            List<Hotel> result = new ArrayList<Hotel>();
            for(int j =0;j<N;j++){
                Hotel hotel = hotels[j];
                boolean flag = false;
                if(price >= hotel.price ){
                	flag = true;
                    for(int k =1;k<tokens.length;k++){
                        if(!hotel.facs.contains(tokens[k])){
                        	//System.out.println(" break ");
                        	flag = false; break;
                        }
                    }
                }
                if(flag){
                    result.add(hotel);                    
                }
            }
            
            if(result.isEmpty()){
                System.out.println("");
            } else {
                Collections.sort(result,
                                 new Comparator<Hotel>() {
                                     public int compare(Hotel h1 ,Hotel h2){
                                         int flag = h2.facs.size() - h1.facs.size();
                                         if(flag == 0) flag = h1.price - h2.price;
                                         if(flag == 0) flag = h1.id - h2.id;
                                         return flag;
                                     }
                                 }
                                );
                StringBuffer resultBuffer = new StringBuffer();
                for(Hotel h:result){
                    resultBuffer.append(h.id).append(" ");
                }
                System.out.println(resultBuffer);
            }
        }
          
    }

    private static class Hotel implements Comparable<Hotel>{
        public int id;
        public int price;
        public Set<String> facs= new HashSet<>();
        public Hotel(int id,int price){
            this.id = id;
            this.price = price;
        }
        
        public void addFac(String fac){
            facs.add(fac);
        }
        
        public int compareTo(Hotel h){
            return this.price - h.price;
        }
    }
}
    
    
