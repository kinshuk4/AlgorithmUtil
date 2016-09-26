package com.vaani.algo.compete.hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by andersonkmi on 8/1/2016.
 */
public class TimeInWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hour = sc.nextInt();
        int minute = sc.nextInt();

        Map<Integer, String> hourWords = new HashMap<>();
        hourWords.put(1, "one");
        hourWords.put(2, "two");
        hourWords.put(3, "three");
        hourWords.put(4, "four");
        hourWords.put(5, "five");
        hourWords.put(6, "six");
        hourWords.put(7, "seven");
        hourWords.put(8, "eight");
        hourWords.put(9, "nine");
        hourWords.put(10, "ten");
        hourWords.put(11, "eleven");
        hourWords.put(12, "twelve");

        Map<Integer, String> minuteWords = new HashMap<>();
        minuteWords.put(0, "o' clock");
        minuteWords.put(1, "one minute");
        minuteWords.put(2, "two minutes");
        minuteWords.put(3, "three minutes");
        minuteWords.put(4, "four minutes");
        minuteWords.put(5, "five minutes");
        minuteWords.put(6, "six minutes");
        minuteWords.put(7, "seven minutes");
        minuteWords.put(8, "eight minutes");
        minuteWords.put(9, "nine minutes");
        minuteWords.put(10, "ten minutes");
        minuteWords.put(11, "eleven minutes");
        minuteWords.put(12, "twelve minutes");
        minuteWords.put(13, "thirteen minutes");
        minuteWords.put(14, "fourteen minutes");
        minuteWords.put(15, "quarter");
        minuteWords.put(16, "sixteen minutes");
        minuteWords.put(17, "seventeen minutes");
        minuteWords.put(18, "eighteen minutes");
        minuteWords.put(19, "nineteen minutes");
        minuteWords.put(20, "twenty minutes");
        minuteWords.put(21, "twenty one minutes");
        minuteWords.put(22, "twenty two minutes");
        minuteWords.put(23, "twenty three minutes");
        minuteWords.put(24, "twenty four minutes");
        minuteWords.put(25, "twenty five minutes");
        minuteWords.put(26, "twenty six minutes");
        minuteWords.put(27, "twenty seven minutes");
        minuteWords.put(28, "twenty eight minutes");
        minuteWords.put(29, "twenty nine minutes");
        minuteWords.put(30, "half");

        int normalizedHour = hour;
        int normalizedMinute = minute;
        boolean pastHalfHour = false;

        if(minute == 0) {
            System.out.println(hourWords.get(normalizedHour) + " " + minuteWords.get(normalizedMinute));
        } else if(minute == 30) {
            System.out.println(minuteWords.get(normalizedMinute) + " past " + hourWords.get(normalizedHour));
        } else {
            if(normalizedMinute > 30) {
                normalizedHour++;
                normalizedMinute = 60 - minute;
                pastHalfHour = true;
            }

            System.out.println(minuteWords.get(normalizedMinute) +
                    (pastHalfHour ? " to " : " past ") + hourWords.get(normalizedHour));
        }
    }
}
