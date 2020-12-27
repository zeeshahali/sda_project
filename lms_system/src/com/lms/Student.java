package com.lms;

import com.Utility.Main;
import com.Utility.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public void PrintStudent(){
        System.out.println("Username: "+username+"\nName: "+name+ "\nRoll Number: "+rollNumber+ "\nBatch: "+batch
                + "\nSGPA: "+sGPA+ "\nCGPA: "+cGPA);
        Utils.PrintDivider();
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

    public void calculate_SGPA(){
        int total_CH = 0;
        float CH_earned = 0;
        for(int i=0; i<courses.size() ; i++){
            if(courses.get(i).getSection().getCourse().offeredInSemester == batch){
                total_CH = total_CH + courses.get(i).getSection().getCourse().getCreditHours();
                CH_earned = CH_earned + courses.get(i).getCreditEarned();
            }
        }
        sGPA = CH_earned / total_CH;
    }

    public void calculate_CGPA(){
        int total_CH = 0;
        float CH_earned = 0;
        for(int i=0; i<courses.size() ; i++){
            total_CH = total_CH + courses.get(i).getSection().getCourse().getCreditHours();
            CH_earned = CH_earned + courses.get(i).getCreditEarned();
        }
        cGPA = CH_earned / total_CH;
    }

    public void Calculate_attendance(){
        int total = 0;
        int presents = 0;
        for(int i=0; i<courses.size() ; i++){
            if(courses.get(i).getSection().getCourse().offeredInSemester == batch){
                for(int j=0; i<courses.get(i).attendance.size() ; j++) {
                    total = (total + 1);
                    if (courses.get(i).attendance.get(j).Value == 'p') {
                        presents = (presents + 1);
                    }
                }
                courses.get(i).calculated_Attendance = presents/total;
            }
        }
    }

    public static ArrayList<Student> AllStudents(Connection connection){
        ArrayList<Student> students = new ArrayList<Student>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * from Student");
            while (resultSet.next()){
                String username = resultSet.getString("Student_username");
                String name = resultSet.getString("Student_Name");
                String password = resultSet.getString("Student_Password");
                String rollNo = resultSet.getString("Student_RollNo");
                int batch = resultSet.getInt("Student_Batch");
                float sgpa = resultSet.getFloat("SGPA");
                float cgpa = resultSet.getFloat("CGPA");
                students.add(new Student(username, password, name, rollNo, batch, sgpa, cgpa));
            }
        }catch (SQLException e){
            System.out.println("No Course Exists " + e.getMessage());
        }
        return students;
    }

    public void RegisterCourse(int sec){
        System.out.println("Course Registered Successfully");
    }

    public ArrayList<Section> RegisteredSections(){
        ArrayList<Section> sections = new ArrayList<>();
        Connection connection = Main.GetConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery("SELECT * FROM RegisteredSection WHERE Student_RollNo = '" +this.rollNumber+ "'");
            while (resultSet.next()){
                sections.add(Section.GetSectionFromCode(connection, resultSet.getString("Section_Code")));
            }
        } catch (SQLException e){
            System.out.println("Error getting admin data");
        }
        return sections;
    }

    public void DropSection(String code) {
        Connection connection = Main.GetConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from RegisteredSection where Course_Code = '"+code+"'" +
                    "AND Student_RollNo = '"+this.rollNumber+"'");
            System.out.println("Course Dropped successfully");
        } catch (SQLException e) {
            System.out.println("No Course Exists " + e.getMessage());
        }
    }
}
