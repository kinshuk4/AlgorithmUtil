package com.vaani.algo.compete.codeeval;

import java.io.File;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LongestLines {

    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(new File(args[0]));
        int linesSize = sc.nextInt();
        PriorityQueue<String> lines = new PriorityQueue<String>(linesSize, lengthComparator);
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            if (lines.size() < linesSize){
                lines.offer(currentLine);
            } else if (0 < lengthComparator.compare(currentLine, lines.peek())){
                lines.poll();
                lines.offer(currentLine);
            }
        }
        int resultSize = Math.min(linesSize, lines.size());
        String[] result = new String[resultSize];
        for(int i = resultSize - 1 ; i >=0; i--){
            result[i] = lines.poll();
        }
        for(String line : result){
            System.out.println(line);
        }
    }

    private static Comparator<String> lengthComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int firstLength = o1 == null ? -1 : o1.length();
            int secondLength = o2 == null ? -1 : o2.length();
            return Integer.compare(firstLength, secondLength);
        }
    };
}
