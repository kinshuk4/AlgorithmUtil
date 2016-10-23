package com.vaani.algo.compete.hackerrank.misc.booking.ddosattack;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class DDoSMain {
	private static BufferedReader br;

	public static void main(String[] args) throws NumberFormatException, IOException {
		//FileInputStream fin = new FileInputStream("input.txt");
		//br = new  BufferedReader (new InputStreamReader (fin));
		br = new BufferedReader (new InputStreamReader (System.in));
		
		//int N = Integer.parseInt(br.readLine());
		long[][] points = new long[100000][2];
		String s = br.readLine();
		String sb = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ',') {
				sb = sb + " ";
			}
			else if (s.charAt(i) != '[' && s.charAt(i) != ']') {
				sb = sb + s.charAt(i);
			}
		}

		//System.out.println(sb);
		int i = 0;
		StringTokenizer st = new StringTokenizer(sb);
		String input;
		while(st.hasMoreTokens()) {
			input = st.nextToken();
			points[i][0] = Long.parseLong(input);
			input = st.nextToken();
			points[i][1] = Long.parseLong(input);
			i++;
		}
		
		KMeansClustering kmeans = new KMeansClustering(i, points);
		kmeans.initialization();
		kmeans.startClustering();
		kmeans.displayEpochsDDoS();	
	}
}
