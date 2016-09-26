/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 06/09/2015
 * 
 * Hacker rank
 * Booking.com Hackathon
 * Problem: Similar Destinations
 * Status: timeout
 * 
 * Problem
 * 
 * At Booking.com we allow users to search accommodations in over 80,000 
 * destinations around the world. We want to improve the user's experience by 
 * suggesting destinations that are similar to the ones they've just searched for.
 *
 * To identify similar destinations, we can use tags entered by our users during 
 * the review process. These tags look like this:
 *
 * london    : theatre, museums, monuments, food, parks, architecture, nightlife
 * amsterdam : museums, shopping, architecture, nightlife, cycling, food, walking
 * berlin    : monuments, nightlife, food, architecture, city_trip
 * paris     : shopping, food, monuments, architecture, gourmet, walking, museums
 * barcelona : architecture, shopping, beach, food, tapas, nightlife
 * 
 * Using these tags, we want to create groups of destinations based on tag similarity. 
 * Destinations belong to a group if they have at least n tags in common.
 * 
 * For example, using the above tags, the possible unique groups with at least 4 tags 
 * in common are shown here:
 * 
 * amsterdam, paris     (5 tags in common: architecture, food, museums, shopping, walking)
 * london, paris        (4 tags in common: architecture, food, monuments, museums)
 * berlin, london       (4 tags in common: architecture, food, monuments, nightlife)
 * amsterdam, london    (4 tags in common: architecture, food, museums, nightlife)
 * amsterdam, barcelona (4 tags in common: architecture, food, nightlife, shopping)
 * 
 * Input Format
 * 
 * The first line of the input is the minimum number of common tags required to create a 
 * group. This number is always greater than one and smaller than 1000.
 * The next lines each specify a destination and its tags. First there is the destination 
 * name followed by a colon, then the tags appear separated by commas. Destination names 
 * and tags only contain alphanumeric characters and underscores. There are no spaces in 
 * the lines.
 * The destination names and tags are all in english. It is safe to read the input as ASCII. 
 * The destination names and tags can have between 1 and 255 characters. The input can contain 
 * up to 1,000 destinations. And there will be no more than 1,000 unique tags in total. Each
 * destination might have up to 200 tags.
 * 
 * Output Format
 * 
 * Each line of the output starts with the destinations composing each group sorted 
 * alphabetically and separated by commas. Then a colon character and then the common tags 
 * of the group also sorted alphabetically and also separated by commas.
 * There should be no spaces in the output. Groups must be unique, which means that there 
 * can not be two or more groups with the exact same destinations. The tags for a group must 
 * be all the common tags, but groups with less common tags than the minimum number provided 
 * in the input should not appear in the output.
 * The lines should be sorted by the length of the groups, with those groups with more tags 
 * in common appearing first. If two or more groups have the same number of common tags, the 
 * lines should be sorted alphabetically.
 * 
 * Sample Input
 * 
4
london:theatre,museums,monuments,food,parks,architecture,nightlife
amsterdam:museums,shopping,architecture,nightlife,cycling,food,walking
berlin:monuments,nightlife,food,architecture,city_trip
paris:shopping,food,monuments,architecture,gourmet,walking,museums
barcelona:architecture,shopping,beach,food,tapas,nightlife 
 * 
 * Sample Output
 * 
amsterdam,paris:architecture,food,museums,shopping,walking
amsterdam,barcelona:architecture,food,nightlife,shopping
amsterdam,london:architecture,food,museums,nightlife
berlin,london:architecture,food,monuments,nightlife
london,paris:architecture,food,monuments,museums
 * 
 */

package com.vaani.algo.compete.hackerrank.hackathon.bookingcom;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SimilarDestinations {

    static Map<String, Set<Integer>> cities = new HashMap<>(1000);
    static String[] tags;
    // inversion index for sorted tags
    static int[] inv;
    static int N;
    static Set<String> cache = new HashSet<>();
    static Map<Integer, Set<String>> results = new HashMap<>();
    
    private static void findGroups(Set<String> group, int tagId, BitSet set, String groupHash) 
    {
        if (cache.contains(groupHash))
            return;
        if (group.isEmpty()) return;
        if (group.size() == 1) return;
        if (tagId == tags.length) {
            cache.add(groupHash);
            if (N > set.cardinality())
                return;
            String s = group.stream().sorted().collect(Collectors.joining(",")) + ":" +
                    set.stream().
                    //peek(t -> group.forEach(c -> cities.get(c).remove(t))).
                    map(i -> inv[i]).
                    sorted().
                    <String>mapToObj(i -> tags[i]).
                    collect(Collectors.joining(","));
            results.putIfAbsent(set.cardinality(), new HashSet<>());
            results.get(set.cardinality()).add(s);
            return;
        }
        //if (N - set.cardinality() > tags.length - tagId)
        //    return;
        Set<String> newGroup = group.stream().
            filter(c -> cities.get(c).contains(tagId)).
            collect(Collectors.toSet());
                set.set(tagId);
        String newHash = newGroup.stream().collect(Collectors.joining(","));
        findGroups(newGroup, tagId + 1, set, newHash);
        if (newGroup.size() == group.size())
            return;
//        if (group.size() < newGroup.size()) {
//            Set<String> t = newGroup;
//            newGroup = group;
//            group = t;
//        }
//        group.removeAll(newGroup);
        set.clear(tagId);
        findGroups(group, tagId + 1, set, groupHash);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(SimilarDestinations.class.getResourceAsStream("SimilarDestinations1.in"));
        scanner.useDelimiter("( |:|\n)");
        N = scanner.nextInt();
        int[] c = new int[1];
        Map<String, Integer> tags = new TreeMap<>();
        while (scanner.hasNextLine()) {
            String city = scanner.next();
            cities.put(city, 
                Arrays.stream(scanner.next().split(",")).
                    peek(t -> tags.computeIfAbsent(t, k -> c[0]++)).map(tags::get).
                    collect(Collectors.toSet()));
            scanner.nextLine();
        }
        SimilarDestinations.tags = tags.keySet().toArray(new String[0]);
        inv = new int[tags.size()];
        c[0] = 0;
        tags.values().forEach(i -> inv[i] = c[0]++);
        findGroups(cities.keySet(), 0, new BitSet(), "");
        results.entrySet().stream().
            sorted((e1, e2) -> e2.getKey() - e1.getKey()).
            forEach(e -> e.getValue().stream().sorted().forEach(System.out::println));
        scanner.close();
    }
    
}
