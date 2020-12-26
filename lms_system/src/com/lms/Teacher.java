package com.lms;

import java.util.ArrayList;

public class Teacher extends User{
    ArrayList<Section> sections = new ArrayList<Section>();

    public Teacher(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public void add_Section(Section s){
        sections.add(s);
    }

    public void remove_Section(Section s){
        sections.remove(s);
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
