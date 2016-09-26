package com.vaani.algo.compete.hackerrank;

import java.util.*;

/**
 * Created by anderson.ito on 29/07/2016.
 */
public class HotelSorting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String keywordsLine = sc.nextLine();
        System.out.println("keywords line: " + keywordsLine);
        List<String> keywords = extractKeywords(keywordsLine);
        int testCases = Integer.parseInt(sc.nextLine());
        System.out.println("Test cases:" + testCases);

        Map<Integer, Integer> occurences = new HashMap<>();
        while(testCases-- > 0) {
            int hotel = Integer.parseInt(sc.nextLine());
            String review = sc.nextLine();
            List<String> reviewWords = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(review);
            while(tokenizer.hasMoreTokens()) {
                reviewWords.add(tokenizer.nextToken());
            }

            reviewWords.retainAll(keywords);

            if(occurences.containsKey(hotel)) {
                Integer counter = occurences.get(hotel);
                counter += reviewWords.size();
                occurences.put(hotel, counter);
            } else {
                occurences.put(hotel, reviewWords.size());
            }

        }

        Set<Map.Entry<Integer, Integer>> entrySet = occurences.entrySet();
        List<Map.Entry<Integer, Integer>> items = new ArrayList<>(entrySet);
        Collections.sort(items, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        });

        for(Map.Entry<Integer, Integer> item : items) {
            System.out.print(item.getKey() + " ");
        }

        sc.close();
    }


    private static List<String> extractKeywords(String line) {
        List<String> keywords = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(line);
        while(tokenizer.hasMoreTokens()) {
            keywords.add(tokenizer.nextToken());
        }

        return keywords;
    }
}
