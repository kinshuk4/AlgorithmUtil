/*
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 *
 * Date: 05/08/2015
 *
 * IMDB Codility
 * Problem: Task 2
 * Status: failed
 *
 * Problem
 *
 * Calculate number of full weeks between given months (inclusive).
 * Year is leap if it is div by 4.
 *
 * Input Format
 *
 * Year, starting month, ending month, weekday of 1st of January in
 * the given year
 *
 * Output Format
 *
 * Number of full weeks
 *
 * Sample Input
 *
2014, "April", "May", "Wednesday"
2014, "April", "April", "Saturday"
 *
 * Sample Output
 *
7
3
 *
 */
// asked in imdb
package com.vaani.algo.compete.codility.fullweeksdiff;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

class Solution {

    public static void main(String... args) {
        System.out.print(solution(2014, "April", "May", "Wednesday"));

        assertTrue(3 == solution(2014, "April", "April", "Saturday"));
        assertTrue(7 == solution(2014, "April", "May", "Wednesday"));
    }

    private static final HashMap<String, Integer> MONTH_MAP = new HashMap<>();

    static {
        MONTH_MAP.put("January", Calendar.JANUARY);
        MONTH_MAP.put("February", Calendar.FEBRUARY);
        MONTH_MAP.put("March", Calendar.MARCH);
        MONTH_MAP.put("April", Calendar.APRIL);
        MONTH_MAP.put("May", Calendar.MAY);
        MONTH_MAP.put("June", Calendar.JUNE);
        MONTH_MAP.put("July", Calendar.JULY);
        MONTH_MAP.put("August", Calendar.AUGUST);
        MONTH_MAP.put("September", Calendar.SEPTEMBER);
        MONTH_MAP.put("October", Calendar.OCTOBER);
        MONTH_MAP.put("November", Calendar.NOVEMBER);
        MONTH_MAP.put("December", Calendar.DECEMBER);
    }

    public static int solution(int Y, String A, String B, String W) {
        if (MONTH_MAP.get(A) == null || MONTH_MAP.get(B) == null) {
            throw new IllegalArgumentException("Illegal month string passed");
        }
        Calendar firstDate = new GregorianCalendar(Y, MONTH_MAP.get(A), 1);
        Calendar secondDate = new GregorianCalendar(Y, MONTH_MAP.get(B), 1);
        secondDate.set(Calendar.DAY_OF_MONTH, secondDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        while (firstDate.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            firstDate.add(Calendar.DAY_OF_YEAR, 1);
        }
        int weeks = 0;
        firstDate.add(Calendar.WEEK_OF_YEAR, 1);
        while (firstDate.before(secondDate)) {
            firstDate.add(Calendar.WEEK_OF_YEAR, 1);
            weeks++;
        }
        return weeks;
    }

}
