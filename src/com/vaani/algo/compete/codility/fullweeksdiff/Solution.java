package com.vaani.algo.compete.codility.fullweeksdiff;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

class Solution {

    public static void main(String... args) {
        System.out.print(new Solution().solution(2014, "April", "May", "Wednesday"));
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

    public int solution(int Y, String A, String B, String W) {
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
