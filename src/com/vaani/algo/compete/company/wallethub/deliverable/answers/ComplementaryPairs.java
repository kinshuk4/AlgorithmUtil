package com.vaani.algo.compete.company.wallethub.java;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by kchandra on 23/10/16.
 */
public class ComplementaryPairs {
	// Time complexity = O(n) for first time looping and adding values to
	// Time complexity = O(n) for again going over the array, 
	// and time complexity for getting the value from the hashmap takes O(1) time, 
	// so again Time complexity = O(n)
	// Space complexity = O(n) = as we are saving the hashmap for storing the values
	public static HashSet<String> checkPair(int[] input, int K) {
		Map<Integer, Integer> hashMap = new HashMap<>();
		HashSet<String> resultSet = new HashSet();
		int i = 0;
		int j = 0;
		// Put all elements of the array in the set,
		// EC = O(n), SC = O(n)
		while (i < input.length) {
			hashMap.put(input[i], i);
			i++;
		}
		int count = 0;
		while (j < input.length) {
			int sum = K - input[j];
			String str = "";
			try {
				if (hashMap.containsKey(sum)) {
					int mapIndex = hashMap.get(sum);
					if (mapIndex == j) {
						// The number is half of the element we have to search
						// This shouldn't be taken, as hashmap contains a[j],
						// and K-a[j],
						// But it is fine, in case, we get a[j], such that
						// K-a[i] exists
						// i.e. different indices contain mid of the array
						j++;
						continue;
					}

					if (sum > input[j]) {
						str = sum + " , " + input[j];
					} else {
						str = input[j] + " , " + sum;
					}

					resultSet.add(str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			j++;
		}

		return resultSet;

	}

	static String filePath = "/Users/kchandra/Lyf/Kode/SCM/Github/JavaK2/AlgorithmUtil/src/com/vaani/algo/compete/company/wallethub/tests/ComplementaryPairs.txt";

	public static void main(String[] args) {
		/*
		 * Output for the test cases:
		 *  K-Pair: 10 ->[ 9 , 1 ]
		 *	K-Pair: 10 ->[ 7 , 3 ][ 9 , 1 ][ 11 , -1 ]
		 *	K-Pair: 12 ->[ 7 , 5 ]
		 *	K-Pair: 12 ->[ 7 , 5 ]
		 */
		try (Scanner scanner = new Scanner(new File(filePath))) {
			while (scanner.hasNext()) {
				String kLine = scanner.nextLine();
				String[] t = kLine.split(" ");

				int K = Integer.parseInt(t[0]);

				int[] a = new int[t.length - 1];
				for (int i = 1; i < t.length; i++) {
					a[i - 1] = Integer.valueOf(t[i]);
				}

				HashSet<String> resultSet;
				Iterator<String> it;

				resultSet = ComplementaryPairs.checkPair(a, K);
				it = resultSet.iterator();
				System.out.print("K-Pair: " + K + " ->");
				try {
					while (it.hasNext()) {
						System.out.print("[ " + it.next() + " ]");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
