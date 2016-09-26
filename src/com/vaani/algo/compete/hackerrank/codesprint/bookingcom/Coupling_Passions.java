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
 * Problem: Coupling Passions
 * Status: accepted
 * 
 * Problem
 * 
 * Mariana and Pedro are a young couple that loves to travel. Pedro 
 * is passionate about surfing, while Mariana is a wine lover. Like 
 * many couples, Mariana and Pedro always plan their trips together, 
 * choosing only those destinations that best satisfy their passions!
 * 
 * Since Booking.com launched the Destination Finder, Mariana and 
 * Pedro started using it to look for new places all over the world 
 * where they can travel and enjoy as many of their collective passions 
 * as possible. Can you help groups of guests like Mariana and Pedro 
 * find some hidden gems?
 * 
 * For this challenge, we'll provide you with the following:
 * 
 *  - A list of guests and, for each guest, a list of their respective 
 * passions.
 *  - A list of destinations and, for each destination, a list of passions 
 * that the destination is known to be good for.
 * 
 * Given the data above, find the two destinations satisfying the maximum 
 * number of the group's collective passions and print them as two 
 * space-separated strings on a single line. If more than one destination 
 * satisfies a maximum number of passions, then choose the pair of such 
 * destinations having the shortest distance between them. Use the Law of 
 * Cosines for Spherical Trigonometry to calculate distances, as 
 * demonstrated by the following pseudocode:
 * 
 *  function distance_between(point1, point2) {
 *     var EARTH_RADIUS = 6371;//in km
 *     var point1_lat_in_radians  = degree2radians( point1.latitude );
 *     var point2_lat_in_radians  = degree2radians( point2.latitude );
 *     var point1_long_in_radians  = degree2radians( point1.longitude );
 *     var point2_long_in_radians  = degree2radians( point2.longitude );
 * 
 *     return acos( sin( point1_lat_in_radians ) * sin( point2_lat_in_radians ) +
 *         cos( point1_lat_in_radians ) * cos( point2_lat_in_radians ) *
 *         cos( point2_long_in_radians - point1_long_in_radians) ) * EARTH_RADIUS;
 *  }
 * 
 * Input Format
 * 
 * The first line contains a single integer, n, denoting the number of 
 * guests traveling together.
 * Each line i of the n subsequent lines lists guest i's passions as a 
 * sequence of space-separated values:
 * 
 *  - The first value is an integer, m, denoting the number of passions 
 * that the guest has.
 *  - Each of the m subsequent space-separated strings describes one of 
 * the guest's passions.
 * 
 * The next line contains a single integer, y, denoting the number of 
 * potential destinations.
 * Each line j of the y subsequent lines describes destination j as a 
 * single line of space-separated values:
 * 
 *  - The first value is a string denoting the destination name.
 *  - The second value is a floating-point number denoting the 
 *  destination's latitude.
 *  - The third value is a floating-point number denoting the 
 *  destination's longitude. 
 *  - The fourth value is an integer, z, denoting the number of passions 
 *  available at the destination.
 *  - Each of the z subsequent space-separated strings describes a 
 *  passion available at the destination.
 * 
 * Output Format
 * 
 * Print a single line with 2 space-separated destination names that 
 * cover the largest number of passions held by the group. These 
 * destinations must be ordered alphabetically; if two or more pairs 
 * of destinations cover the same number of passions, choose the pair 
 * having the shortest distance between cities.
 * 
 * Sample Input
 * 
2
3 surfing yoga walking
3 wine relaxation beach
3
amsterdam 52.374030 4.889690 4 museums canals nightlife walking
sagres 37.129665 -8.669586 3 beach surfing relaxation
biarritz 43.480120 -1.555580 6 surfing nightlife beach food wine walking
 * 
 * Sample Output
 * 
biarritz sagres
 * 
 */

package com.vaani.algo.compete.hackerrank.codesprint.bookingcom;

import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Coupling_Passions {
    
    static Map<String, Integer> P = new HashMap<>();
    static City[] C;
    
    static class City {
        double lon, lat;
        String name;
        Set<String> p = new HashSet<>();
    }
    
    static double dist(int i, int j) {
        int EARTH_RADIUS = 6371;//in km
        double point1_lat_in_radians  = toRadians(C[i].lat);
        double point2_lat_in_radians  = toRadians(C[j].lat);
        double point1_long_in_radians  = toRadians(C[i].lon);
        double point2_long_in_radians  = toRadians(C[i].lon);

        return acos( sin( point1_lat_in_radians ) * sin( point2_lat_in_radians ) +
                     cos( point1_lat_in_radians ) * cos( point2_lat_in_radians ) *
                     cos( point2_long_in_radians - point1_long_in_radians) ) * EARTH_RADIUS;
    }
    
    static String solve() {
        double minDist = Double.MAX_VALUE;
        int max = 0;
        int[] pair = new int[2];
        for (int i = 0; i < C.length; i++) {
            for (int j = i + 1; j < C.length; j++) {
                Set<String> s = new HashSet<>();
                s.addAll(C[i].p);
                s.addAll(C[j].p);
                int m = s.stream()
                        .mapToInt(p -> P.getOrDefault(p, 0))
                        .sum();
                if (max > m)
                    continue;
                double d = dist(i, j);
                if (max < m || d < minDist) {
                    minDist = d;
                    max = m;
                    pair[0] = i;
                    pair[1] = j;
                }
            }
        }
        return Stream.of(C[pair[0]].name, C[pair[1]].name)
                .sorted()
                .collect(Collectors.joining(" "));
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Coupling_Passions.class.getResourceAsStream("Coupling_Passions.in"));
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            for (int j = 0; j < m; j++) {
                P.compute(scanner.next(), 
                        (k, v) -> v == null? 1: v + 1);
            }
        }
        int y = scanner.nextInt();
        C = new City[y];
        for (int i = 0; i < C.length; i++) {
            City c = new City();
            c.name = scanner.next();
            c.lat = scanner.nextDouble();
            c.lon = scanner.nextDouble();
            int z = scanner.nextInt();
            IntStream.range(0, z).forEach(j -> c.p.add(scanner.next()));
            C[i] = c;
        }
        System.out.println(solve());
        scanner.close();
    }
    
}
