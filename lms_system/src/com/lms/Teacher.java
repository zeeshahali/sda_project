package com.lms;

import com.Utility.Main;
import com.menu.MainMenu;

import java.lang.annotation.Target;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class Teacher extends User{
    ArrayList<Section> sections = new ArrayList<Section>();

    Teacher(){}

    public Teacher(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public void PrintTeacher(){
        System.out.println("Username: "+username+"\nTeacher: "+this.name);
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

    public static ArrayList<Teacher> AllTeachers(Connection con){
        ArrayList<Teacher> teachers = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Teacher");
            while (resultSet.next()){
                String name = resultSet.getString("Teacher_Name");
                String username = resultSet.getString("Teacher_username");
                String password = resultSet.getString("Teacher_Password");
                Teacher teacher = new Teacher(username, password, name);
                teachers.add(teacher);
            }
        }catch (SQLException e){
            System.out.println("No Teachers Found " + e.getMessage());
        }
        return teachers;
    }

    public static Teacher SectionTeacher(Connection con, String teacher_Username){
        Teacher teacher = new Teacher();
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Teacher where Teacher_username = '"+teacher_Username+"'");
            while (resultSet.next()){
                String name = resultSet.getString("Teacher_Name");
                String username = resultSet.getString("Teacher_username");
                String password = resultSet.getString("Teacher_Password");
                teacher = new Teacher(username, password, name);
            }
        }catch (SQLException e){
            System.out.println("No Teachers Found " + e.getMessage());
        }
        return teacher;
    }
}
