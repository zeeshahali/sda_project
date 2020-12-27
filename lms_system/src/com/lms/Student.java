package com.lms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static ArrayList<Student> SectionStudents(Connection connection, String sectionCode){
        ArrayList<Student> students = new ArrayList<Student>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * from Course WHERE Course_Code = '"+sectionCode+"'");
            while (resultSet.next()){
                String code = resultSet.getString("Course_code");
                String name = resultSet.getString("Course_Name");
                int semester = resultSet.getInt("Offered_in_Semester");
                String preReq = resultSet.getString("Course_PreReq");
                int credits = resultSet.getInt("Course_CH");
                //course = new Course(code, name, semester, preReq, credits);

            }
        }catch (SQLException e){
            System.out.println("No Course Exists " + e.getMessage());
        }
        return students;
    }
}
