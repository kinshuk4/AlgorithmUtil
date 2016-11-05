package com.vaani.algo.compete.company.wallethub.java;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by kchandra on 23/10/16.
 */
public class Palindrome {
	private static final String fileName = "/Users/kchandra/Lyf/Kode/SCM/Github/JavaK2/AlgorithmUtil/src/com/vaani/algo/compete/company/wallethub/tests/Palindrome.txt";

	/**
	 * Loop from start and end with pointers i and j, until start and end meet
	 * If all the characters at S[i] and S[j] are equal, continue, otherwise
	 * break Reached the end of loop, string is palindrome Time complexity =
	 * O(2n) = O(n) as we are looping over the array Space complexity = O(1)
	 * 
	 * @param S
	 *            String to be checked for palindrome
	 * @return
	 */
	public static boolean isPalindrome(String S) {
		for (int i = 0, j = S.length() - 1; i < j; i++, j--) {
			if (S.charAt(i) != S.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		/*
		 * Output for the file: 1221->true 1121->false malayalam->true
		 * Ana->false AnA->true
		 */
		try (Scanner scanner = new Scanner(new File(fileName))) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				System.out.println(line + "->" + Palindrome.isPalindrome(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
