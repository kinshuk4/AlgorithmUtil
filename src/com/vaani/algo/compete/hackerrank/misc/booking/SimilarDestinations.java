package com.vaani.algo.compete.hackerrank.misc.booking;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * An efficient solution without memory optimization.Also for this programming problem,encapsulation is not adhered.
 * 
 * */

class SimilarDestinations {
    
    private static Map<String,Integer> tagMap = new HashMap<String,Integer>();
    private static Map<BitSet,Group> patterns = new HashMap<BitSet,Group>();
    private static Set<String> tagSet = new HashSet<>();
    private static Map<Integer,String> tagMapIndex = new HashMap<Integer,String>();
    
    public static void main(String[] args) {
    	try {
    		
        Scanner sc = new Scanner(System.in);
        int min = sc.nextInt();
        sc.nextLine();
        List<Location> locs = new ArrayList<>();
        while(sc.hasNextLine()){
            String dest = sc.nextLine();
            String[] tokens = dest.split(":");
            String[] tags = tokens[1].split(",");
            Location loc = new Location(tokens[0]);
            for(String tag : tags){
                loc.addTag(tag.trim()); 
                tagSet.add(tag.trim());
            }           
            locs.add(loc);
        }
        List<String> tags= new ArrayList<>(tagSet);
        Collections.sort(tags);
        for(int i=0;i<tags.size();i++){
            tagMap.put(tags.get(i),i+1);
            tagMapIndex.put(i+1,tags.get(i));
        }
        for(Location loc:locs){
            loc.generateTagBits();
        }
        for(int i =0;i<locs.size();i++){
            for(int j =i+1;j<locs.size();j++){
                BitSet pattern = commonTags(locs.get(i),locs.get(j));
                if(pattern.cardinality() >= min){
                    if(patterns.containsKey(pattern)){
                        Group group = patterns.get(pattern);   
                        //group.addLocation(locs.get(i));
                        //group.addLocation(locs.get(j));
                    } else {
                        Group group = new Group(pattern);
                        //group.addLocation(locs.get(i));
                        //group.addLocation(locs.get(j));
                        patterns.put(pattern,group);   
                    }
                }
            }
        }
        for(Location location :locs){            
            Iterator<BitSet> iterator = patterns.keySet().iterator();
            while(iterator.hasNext()){
                BitSet currentPattern = iterator.next();
                BitSet tempPattern = (BitSet)currentPattern.clone();
                tempPattern.andNot(location.tagBits);
                if(tempPattern.isEmpty()){
                    patterns.get(currentPattern).addLocation(location);
                }
            }
        }
        
        List<Group> groups = new ArrayList(patterns.values());
        for(Group group : groups) {
        	group.postProcess();
        }
        Collections.sort(groups);
        for(Group group:groups){
        	System.out.println(group.output);
        }
        
    	}catch(Exception ex){
	    	StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		System.out.println(errors);	
    	}
    }
    
    private static BitSet commonTags(Location l1,Location l2){
        BitSet pattern = (BitSet)l1.tagBits.clone();
        pattern.and(l2.tagBits);
        return pattern;
    }
    
    
    private static class Location implements Comparable<Location>{
        public String name;
        public List<String> tags = new ArrayList<>();
        public BitSet tagBits = new BitSet(200);
        Location(String name){
            this.name = name;
        }
        
        void addTag(String tag){
            tags.add(tag);
        }
        
        void generateTagBits(){
            for(int i=0;i<tags.size();i++){
                tagBits.set(tagMap.get(tags.get(i)));    
            }            
        }
        
        public String toString(){
            return name;
        }
        
        public int compareTo(Location loc){
        	return this.name.compareTo(loc.name);
        }
        
    }
    
    static class Group implements Comparable<Group>{
        
        public BitSet pattern;
        private Set<Location> locs= new HashSet<>();
        private int count;
        private String locationsAsString;
        private String tagsAsString;
        public String output;
        
        public Group(BitSet pattern){
            this.pattern = pattern;
            this.count = pattern.cardinality();
        }
        
        private void addLocation(Location location){
            this.locs.add(location);
        }
        
        private void postProcess(){
        	List<Location> locsList = new ArrayList<>(locs);
        	Collections.sort(locsList);
        	StringBuffer locationsBuffer = new StringBuffer();
        	String prefix = "";
			for(Location loc:locsList){
				locationsBuffer.append(prefix);
  				prefix = ",";
        		locationsBuffer.append(loc.name);
        	}
        	this.locationsAsString = locationsBuffer.toString();
        	
        	
        	StringBuffer tagsBuffer = new StringBuffer();
        	prefix = "";
        	for(int i =0;i<pattern.size();i++){
        		if(pattern.get(i)){
        			tagsBuffer.append(prefix);
        			prefix=",";
        			tagsBuffer.append(tagMapIndex.get(i));
        		} 
        	}
        	this.tagsAsString = tagsBuffer.toString();
        	
        	output = locationsAsString+":"+tagsAsString;
        }
        
        public int compareTo(Group other){
            int flag = other.count - count;
            if(flag == 0) flag = locationsAsString.compareTo(other.locationsAsString);
            return flag;
        }
    }
    
}	
