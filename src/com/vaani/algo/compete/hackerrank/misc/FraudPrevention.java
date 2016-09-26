/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 26/07/2015
 * 
 * Hacker rank
 * Problem: Fraud Prevention
 * Status: accepted
 * 
 * At Groupon we need to take steps to detect and prevent fraudulent purchases. 
 * One form of fraud is an attempt from the same user to purchase a deal more 
 * than once using different credit card information. Given a set of orders, 
 * your task is to identify the orders that fall under this type of fraud.
 * 
 * An order is considered fraudulent if any of the following conditions apply:
 * 
 * - Two orders have the same e-mail address and deal id, but different credit 
 * card information, regardless of street address.
 * - Two orders have the same Address/City/State/Zip and deal id, but different 
 * credit card information, regardless of e-mail address.
 * 
 * Remember, people are tricky and are actively trying to get past this fraud 
 * detector. Your code must be able to handle the following tricks:
 * 
 * - E-mail and addresses (including city and state) are case insensitive: 
 * bugs@bunny.com is the same as BuGS@BuNNy.COM and 123 Sesame St. is the 
 * same as 123 SeSAME st..
 * - The username portion of an e-mail address can have ignored characters. 
 * A . in an e-mail is flat out ignored, so bugs1@bunny.com, and bugs.1@bunny.com 
 * are the same e-mail address. A + in an e-mail means the plus and everything 
 * after is ignored, so bugs@bunny.com and bugs+10@bunny.com are the same e-mail 
 * address.
 * - Street addresses often have abbreviated words. 123 Sesame St. and 123 Sesame 
 * Street are the same address. IL and Illinois are the same state. For the 
 * purposes of not making this a typing problem, you can assume that the only 
 * abbreviated words you need to worry about are Street/St. and Road/Rd., and the 
 * only states you need to worry about are IL, CA (California) and NY (New York).
 * - Zip code may or may not contain a dash -.
 * 
 * We need this detection code to run quickly. The input file will be quite large. 
 * Hence, it is important that your code runs as fast as possible. That said, please 
 * remember that this fraud system is part of a larger system and one that might change 
 * over time, and we expect the structure of your code to reflect that fact.
 * 
 * Input Format
 * 
 * The first line contains an integer, N, denoting the number of records. This is 
 * followed by N lines containing one record each.
 * Each record contains the following information separated by commas:
 * 
 * - Order id (numeric)
 * - Deal id (numeric)
 * - Email address
 * - Street address
 * - City
 * - State
 * - Zip Code
 * - Credit Card #
 * 
 * Output Format
 * 
 * A single line of comma-separated fraudulent order ids in ascending order.
 * 
 * Sample Input
 * 
3
1,1,bugs@bunny.com,123 Sesame St.,New York,NY,10011,12345689010
2,1,elmer@fudd.com,123 Sesame St.,New York,NY,10011,10987654321
3,2,bugs@bunny.com,123 Sesame St.,New York,NY,10011,12345689010
 * 
 * Sample Output
 * 
1,2
 * 
 */

package com.vaani.algo.compete.hackerrank.misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class FraudPrevention {

    static class Order {
        int id;
        int dealId;
        String mail;
        String street;
        String city;
        String state;
        int zip;
        long cc;
    }

    static Map<String, Map<Integer, Map<Long, List<Integer>>>> byMail = new HashMap<>();
    static Map<String, Map<Integer, Map<Long, List<Integer>>>> byAddr = new HashMap<>();
//    static Map<String, Map<Integer, Map<Long, List<Integer>>>> byStreet = new HashMap<>();
//    static Map<String, Map<Integer, Map<Long, List<Integer>>>> byCity = new HashMap<>();
//    static Map<String, Map<Integer, Map<Long, List<Integer>>>> byState = new HashMap<>();
//    static Map<Integer, Map<Integer, Map<Long, List<Integer>>>> byZip = new HashMap<>();
    
    static <T> void add(T field, Map<T, Map<Integer, Map<Long, List<Integer>>>> m, Order o) {
        Map<Integer, Map<Long, List<Integer>>> deals = m.get(field);
        if (deals == null) {
            deals = new HashMap<>();
            m.put(field, deals);
        }
        Map<Long, List<Integer>> deal = deals.get(o.dealId);
        if (deal == null) {
            deal = new HashMap<>();
            deals.put(o.dealId, deal);
        }
        List<Integer> orders = deal.get(o.cc);
        if (orders == null) {
            orders = new ArrayList<>();
            deal.put(o.cc, orders);
        }
        orders.add(o.id);
    }
    
    static <T> void add(Order o) {
        add(o.mail, byMail, o);
        add(o.street + "/" + o.city + "/" + o.state + "/" + o.zip, byAddr, o);
//        add(o.street, byStreet, o);
//        add(o.city, byCity, o);
//        add(o.state, byState, o);
//        add(o.zip, byZip, o);
    }
    
    static <T> void findFraud(Map<T, Map<Integer, Map<Long, List<Integer>>>> m, Set<Integer> fraud) {
        for (Map<Integer, Map<Long, List<Integer>>> deals: m.values()) {
            for (Map<Long, List<Integer>> deal: deals.values()) {
                if (deal.size() == 1)
                    continue;
                deal.forEach((cc, orders) -> fraud.addAll(orders));
            }
        }
    }
    
    static Set<Integer> findFraud() {
        Set<Integer> fraud = new HashSet<>();
        findFraud(byMail, fraud);
        findFraud(byAddr, fraud);
//        findFraud(byStreet, fraud);
//        findFraud(byCity, fraud);
//        findFraud(byState, fraud);
//        findFraud(byZip, fraud);
        return fraud;
    }
    
    static String parseState(String s) {
        s = s.replace("new york", "ny");
        s = s.replace("california", "ca");
        s = s.replace("illinois", "il");
        return s;
    }

    static String parseStreet(String s) {
        s = s.replace(" st.", " street");
        s = s.replace(" rd.", " road");
        return s;
    }

    static String parseZip(String s) {
        StringBuilder buf = new StringBuilder(s.length());
        for (char c: s.toCharArray()) {
            if (c == '-')
                continue;
            buf.append(c);
        }
        return buf.toString();
    }

    static String parseEmail(String s) {
        StringBuilder buf = new StringBuilder(s.length());
        boolean ignore = false;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '.')
                continue;
            if (c == '+') {
                ignore = true;
                continue;
            }
            if (c == '@') {
                buf.append(s.substring(i));
                break;
            }
            if (ignore)
                continue;
            buf.append(c);
        }
        return buf.toString();
    }

    static Order newOrder(String[] r) {
        Order o = new Order();
        o.id = Integer.parseInt(r[0]);
        o.dealId = Integer.parseInt(r[1]);
        o.mail = parseEmail(r[2].toLowerCase());
        o.street = parseStreet(r[3].toLowerCase());
        o.city = r[4].toLowerCase();
        o.state = parseState(r[5].toLowerCase());
        o.zip = Integer.parseInt(parseZip(r[6]));
        o.cc = Long.parseLong(r[7]);
        return o;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(new File("/tmp/in"));
        int N = scanner.nextInt();
        scanner.nextLine();
        IntStream.range(0, N).forEach((i) -> add(newOrder(scanner.nextLine().split(","))));
        Set<Integer> fraud = findFraud();
        scanner.close();
        Object[] s = fraud.stream().sorted().toArray();
        if (s.length == 0) return;
        int c = 0;
        while (true) {
            System.out.print(s[c++]);
            if (c == s.length)
                break;
            System.out.print(",");
        }
    }

}
