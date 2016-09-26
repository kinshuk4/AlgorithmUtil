/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 06/08/2016
 * 
 * Hacker rank
 * Booking.com CodeSprint
 * Problem: Reviews
 * Status: accepted
 * 
 * Problem
 * 
 * Guest reviews are an important part of helping travelers choose 
 * destinations that satisfy their passions. Each guest review consists 
 * of a reviewer ID (r), Unix time timestamp denoting the date of the 
 * review (t), and a string of body text (b). 
 * To help determine which reviewers are experts on a specific passion, 
 * we want to score each reviewer for their reviews mentioning that 
 * passion. 
 * A reviewer's score for a single, specific passion is calculated as 
 * follows:
 * 
 * Review Date:
 *  - Reviews having a timestamp, t, in the inclusive range between 
 * June 15th, 2016 and July 15th, 2016 (GMT) are awarded 20 points.
 *  - Reviews written outside of the aforementioned time range (i.e., 
 * before or after) are awarded 10 points.
 * 
 * Review Length:
 *  - A review body, b, having 100 or more characters is awarded 20
 * points.
 *  - A review having less than 100 characters is awarded 10 points.
 * 
 * If a reviewer has more than one review mentioning a specific passion, 
 * their expertise score for that passion is the sum of the scores for 
 * all their reviews mentioning that specific passion.
 * 
 * Determining the foremost expert reviewer with regard to a specific 
 * passion:
 * 
 *  - Score Each Reviewer. Note that a reviewer ID may have multiple 
 * reviews associated with it and a reviewer's expertise score for a 
 * passion is the sum of the scores for all their reviews mentioning 
 * that passion.
 *  - Breaking Ties. If two reviewer IDs have the same expertise score 
 * for a passion, choose the reviewer with the smaller ID.
 * 
 * Given a set of reviews and a list of passions, go through each passion 
 * (in order) and print the reviewer ID (r) for the reviewer having the 
 * highest expertise score for that passion on a new line. If no 
 * reviewers mentioned a specific passion, print -1 instead.
 * 
 * Input Format
 * 
 * The first line contains two positive space-separated integers denoting 
 * the respective values of n (the number of passions) and m (the number 
 * of reviews).
 * Each line i of the n subsequent lines contains a single word describing 
 * passion i.
 * The 2m subsequent lines describe each of the m reviews over two lines:
 * 
 * - The first line contains two space-separated integers describing the 
 * respective 
 * values of r (the reviewer ID) and t (the review's Unix time timestamp 
 * in seconds).
 * - The second line contains a string of text denoting the value of b 
 * (the review's body).
 * 
 * Output Format
 * 
 * Print n lines of output. Each line i must contain a single integer denoting 
 * the reviewer ID (r) of the expert for the ith passion received as input; 
 * if no reviewers mentioned that specific passion, print -1 instead.
 * 
 * Sample Input
 * 
3 4
Skating
Food
Climbing
1 1467720000
Skating is good in Austria
22 1464782400
I loved the Spanish food, it had so many varieties and it was super super delicious. The price was a little bit high but it was worth it. People who don't like spicy food might need to think twice as it could be a little bit problematic for them.
4 1467720000
I didn’t like the Indian food!
50  1467720000
People were really friendly, I enjoyed being there.
 * 
 * Sample Output
 * 
1
4
-1
 * 
 */

package com.vaani.algo.compete.hackerrank.codesprint.bookingcom;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Reviews {
    
    static Set<String> P = new LinkedHashSet<>();
    static Review[] R;
    
    static long FROM = LocalDateTime.of(2016, 6, 15, 0, 0)
            .toEpochSecond(ZoneOffset.ofTotalSeconds(0));
    static long TO = LocalDateTime.of(2016, 7, 15, 0, 0)
            .toEpochSecond(ZoneOffset.ofTotalSeconds(0));
    
    static class Review {
        int id, len, score;
        long t;
        Set<String> p;
        void calcScore() {
            score = len >= 100? 20: 10;
            score += t >= FROM && t < TO? 20: 10;
        }
    }

    static String format(String s) {
        return s.replaceAll("[^a-z]", "");
    }
    
    static Stream<String> fetchPassions(String w) {
        return P.stream().filter(s -> w.indexOf(s) != -1);
    }
    
    static Review readReview(Scanner s) {
        Review r = new Review();
        r.id = s.nextInt();
        r.t = s.nextLong();
        s.nextLine();
        String l = s.nextLine().toLowerCase();
        r.len = l.length();
        r.p = fetchPassions(l)
            .collect(Collectors.toSet());
        r.calcScore();
        return r;
    }
    
    static int cmpReviewers(Entry<Integer, IntSummaryStatistics> e1, 
            Entry<Integer, IntSummaryStatistics> e2) 
    {
        int res = Long.compare(e1.getValue().getSum(), 
                e2.getValue().getSum());
        if (res == 0)
            res = e2.getKey() - e1.getKey();
        return res;
    }
    
    static long findExpert(List<Review> l) {
        Optional<Entry<Integer, IntSummaryStatistics>> entry = l.stream()
            .collect(Collectors.groupingBy(r -> r.id, 
                    Collectors.summarizingInt(r -> r.score)))
            .entrySet().stream()
            .max(Reviews::cmpReviewers);
//        entry.ifPresent(System.out::println);
        return entry.map(e -> e.getKey())
            .orElse(-1);
    }
    
    static void solve() {
        Map<String, List<Review>> m = new HashMap<>();
        P.stream()
            .forEach(s -> m.put(s, new ArrayList<>()));
        for (int i = 0; i < R.length; i++) {
            int _i = i;
            R[i].p.stream()
                .filter(p -> m.containsKey(p))
                .forEach(p -> m.get(p).add(R[_i]));
        }
        P.stream()
            .map(s -> m.get(s))
            .mapToLong(Reviews::findExpert).forEach(System.out::println);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Reviews.class.getResourceAsStream("Reviews.in"));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        IntStream.range(0, n)
            .forEach(i -> P.add(scanner.next().toLowerCase()));
        R = new Review[m];
        Arrays.setAll(R, i -> readReview(scanner));
        solve();
        scanner.close();
    }
    
}
