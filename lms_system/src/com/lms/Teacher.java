package com.lms;

import java.util.ArrayList;

public class Teacher extends User{
    String username;
    ArrayList<Registered> assignedCourses = new ArrayList<Registered>();

    public Teacher(String username, String name, ArrayList<Registered> assignedCourses){
        this.username = username;
        this.name = name;
        this.assignedCourses.addAll(assignedCourses);
    }
}
