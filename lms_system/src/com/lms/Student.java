package com.lms;

import java.util.ArrayList;

public class Student extends User{
    String rollNumber;
    int batch;
    ArrayList<Registered> courses = new ArrayList<>();

    public Student(String rollNumber, int batch, String name){
        this.name = name;
        this.rollNumber = rollNumber;
        this.batch = batch;
    }
}
