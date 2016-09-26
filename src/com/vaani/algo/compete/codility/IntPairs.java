package com.vaani.algo.compete.codility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntPairs {

	public List<Pair> solution(final int[] A, final int sum) {

		final Set<Integer> uniqueElements = new HashSet<Integer>();
		for (int a : A) {
			uniqueElements.add(a);
		}
		List<Pair> pairs = new ArrayList<Pair>();
		for(int a : A) {
			int b = sum - a ;
			if(uniqueElements.contains(b)) {
				uniqueElements.remove(b);
				pairs.add(new Pair(a,b));
			}
			int dd;
		}
		return pairs;
	}
 
	public final class Pair {
		final int a;
		final int b;

		public Pair(final int A, final int B) {
			a = A;
			b = B;
		}
	}

}
