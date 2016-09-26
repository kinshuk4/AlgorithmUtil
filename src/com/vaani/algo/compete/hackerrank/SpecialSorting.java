package com.vaani.algo.compete.hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by andersonkmi on 7/23/2016.
 */
public class SpecialSorting {

    public List<String> sortItems(List<String> items) {
        List<BookingRecord> records = new ArrayList<>();

        for(String line : items) {
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            String name = tokenizer.nextToken();
            int age = Integer.valueOf(tokenizer.nextToken());
            String gender = tokenizer.nextToken();

            BookingRecord record = new BookingRecord();
            record.setAge(age);
            record.setName(name);
            record.setGender(gender);
            records.add(record);
        }

        Collections.sort(records, (r1, r2) -> r1.getAge() - r2.getAge());

        List<String> results = new ArrayList<>();
        for(BookingRecord record : records) {
            results.add(record.toString());
        }
        return results;
    }
}
