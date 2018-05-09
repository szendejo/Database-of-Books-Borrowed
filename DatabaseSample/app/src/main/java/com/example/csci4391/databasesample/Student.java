package com.example.csci4391.databasesample;

/**
 * Created by csci4391 on 4/3/18.
 */

public class Student {

    String name;
    String major;

    // setters
    public void setName(String newName) {
        this.name = newName;
    }

    public void setMajor(String newMajor) {
        this.major = newMajor;
    }

    // getters
    public String getName() {
        return this.name;
    }

    public String getMajor() {
        return this.major;
    }

}
