package com.vaani.algo.compete.hackerrank;

/**
 * Created by andersonkmi on 7/23/2016.
 */
public class BookingRecord {
    private String name;
    private int age;
    private String gender;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return name + "," + age + ","+ gender;
    }
}
