package com.vaani.algo.compete.codility;

public class TieRopes {
	public int solution(int K, int[] A) {
		int ropeNum = 0;
		int remain = K;
		for (int length : A) {
			remain -= length;
			if (remain <= 0) {
				ropeNum++;
				remain = K;
			}
		}
		return ropeNum;
	}
}
