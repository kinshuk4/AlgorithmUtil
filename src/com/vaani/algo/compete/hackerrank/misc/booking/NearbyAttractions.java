package com.vaani.algo.compete.hackerrank.misc.booking;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


/**
 * For this programming problem,encapsulation is not adhered.
 * 
 * */
 
class NearbyAttractions {

    public static void main(String[] args) {
        Map<String,Integer> speeds = new HashMap<>();
        speeds.put("metro",20);
        speeds.put("bike",15);
        speeds.put("foot",5);
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        List<Attraction> attracts = new ArrayList<Attraction>();
        for(int i = 0;i<N;i++){
            String[] tokens = sc.nextLine().split("\\s");
            attracts.add(new Attraction(Integer.parseInt(tokens[0]),Double.parseDouble(tokens[1]),Double.parseDouble(tokens[2])));
                         }
        int M = sc.nextInt();
        sc.nextLine();
        for(int i = 0;i<M;i++){
        
            String[] tokens = sc.nextLine().split("\\s");
            Double x = Double.parseDouble(tokens[0]);
            Double y = Double.parseDouble(tokens[1]);
            String by = tokens[2];
            int time = Integer.parseInt(tokens[3]);
            List<VisitableAttraction> res = new ArrayList<>();
            //System.out.println("maxtime for attraction "+time);
            for(Attraction attraction : attracts){
                Double dist = distance(x,attraction.x,y,attraction.y);               
                Double neededTime = dist/(speeds.get(by));
                if(i==2){
                    //System.out.println("dist,time for attraction "+attraction.id+" is : "+dist+","+neededTime);    
                }
                if((neededTime*60)<=time){
                    res.add(new VisitableAttraction(attraction.id,dist));
                }
            }
            Collections.sort(res);
            StringBuffer resultBuffer = new StringBuffer();
            for(VisitableAttraction vAttraction : res){
                resultBuffer.append(vAttraction.id).append(" ");
            }
            System.out.println(resultBuffer);
                
        }
    }
        
    private static double distance(double lat1, double lat2, double lon1,
        double lon2) {
        int EARTH_RADIUS = 6371;//in km
        Double point1_lat_in_radians  = Math.toRadians( lat1 );
        Double point2_lat_in_radians  = Math.toRadians( lat2 );
        Double point1_long_in_radians  = Math.toRadians( lon1 );
        Double point2_long_in_radians  = Math.toRadians( lon2 );

        double distance = Math.acos( Math.sin( point1_lat_in_radians ) * Math.sin( point2_lat_in_radians ) +
                     Math.cos( point1_lat_in_radians ) * Math.cos( point2_lat_in_radians ) *
                     Math.cos( point2_long_in_radians - point1_long_in_radians) ) * EARTH_RADIUS;
        BigDecimal bd = new BigDecimal(distance).setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
        
    }    
    
    private static class VisitableAttraction implements Comparable<VisitableAttraction>{
        public int id;
        public Double distance;
        VisitableAttraction(int id,Double distance){
            this.id = id;
            this.distance= distance;
        }
        
        public int compareTo(VisitableAttraction att){
            int flag  = Double.compare(distance,att.distance);
            if(flag ==0) flag = id - att.id;
            return flag;
        }
    }
        
    private static class Attraction{
        public int id;
        public double x;
        public double y;
        Attraction(int id,double x,double y){
            this.id = id;
            this.x= x;
            this.y = y;
        }        
        
    }
}
