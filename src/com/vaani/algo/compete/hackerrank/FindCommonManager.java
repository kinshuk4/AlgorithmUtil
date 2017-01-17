package com.vaani.algo.compete.hackerrank;

import java.util.*;
/*
 * Part of Skyscanner Coding Challenge
 * Please find screenshots(FindCommonManager*.png) in the same folder
 *
 *	Solution:
 *	Use Hashmap, LinkedHashSet collections as below
 */
public class FindCommonManager {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int _count = Integer.parseInt(in.nextLine());
		
		OutputCommonManager(_count, in);
	}

	private static void OutputCommonManager(int count, Scanner in) {
		
		Map<String, String> hm = new HashMap<String, String>();
		Set<String> lhs = new LinkedHashSet<String>();
		
		String str;
		String firstName = in.nextLine();
		String secondName = in.nextLine();
		String[] stringArr;
		
		// store all the reporting structure in a HashMap
		str = in.nextLine();
		while(!str.isEmpty()){
			stringArr = str.split(" ");
			hm.put(stringArr[1], stringArr[0]);
			str = in.nextLine();
		}
		
		//Store the reporting hierarchy of firstName in a LinkedHashSet
		lhs.add(firstName);
		str = hm.get(firstName);
		while(str!=null){
			lhs.add(str);
			str = hm.get(str);
		}
		
		/*//To print the reporting chain of firstName
		System.out.println(lhs.toString());*/
		
		//to print the nearest common manager of firstName & secondName
		//for each manager in reporting hierarchy of secondName, we check if that manager
		//is present in the reporting hierarchy of firstName
		//we stored this in lhs
		str = secondName;
		while(str!=null){
			if(lhs.contains(str)){
				System.out.println("Common Manager is: " + str);
				break;
			}
			str = hm.get(str);
		}
	}

}