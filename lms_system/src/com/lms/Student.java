package com.lms;

import java.util.ArrayList;

public class Student extends User{
    String rollNumber;
    int batch;
    ArrayList<Registered> courses = new ArrayList<>();
    float sGPA;
    float cGPA;

    public Student(String username, String password, String name, String rollNumber, int batch, float sGPA, float cGPA){
        this.username = username;
        this.password = password;
        this.name = name;
        this.rollNumber = rollNumber;
        this.batch = batch;
        this.sGPA = sGPA;
        this.cGPA = cGPA;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public ArrayList<Registered> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Registered> courses) {
        this.courses = courses;
    }

    public float getsGPA() {
        return sGPA;
    }

    public void setsGPA(float sGPA) {
        this.sGPA = sGPA;
    }

    public float getcGPA() {
        return cGPA;
    }

    public void setcGPA(float cGPA)  {this.cGPA = cGPA; }
}
