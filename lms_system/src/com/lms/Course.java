package com.lms;

import com.Utility.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    String code;
    String name;
    int offeredInSemester;
    String preReq;
    int creditHours;
    ArrayList<Section> course_section = new ArrayList<Section>();

    Course(){}

    public Course(String code, String name, int offeredInSemester, String preReq, int CH) {
        this.code = code;
        this.name = name;
        this.offeredInSemester = offeredInSemester;
        this.preReq = preReq;
        this.creditHours = CH;
    }

    public void PrintCourse(){
        System.out.println("Course code: "+this.code+ "\nCourse Name: "+this.name+
                "\nSemester: "+this.offeredInSemester+ "\nPreReq: "+this.preReq+
                "\nCreditHours: "+this.creditHours);
        Utils.PrintDivider();
    }

    public void add_section(Section s) {
        this.course_section.add(s);
    }

    public void Remove_section(Section s) {
        this.course_section.remove(s);
    }

    public ArrayList<Section> getCourse_section() {
        return course_section;
    }

    public void setCourse_section(ArrayList<Section> course_section) {
        this.course_section = course_section;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOfferedInSemester() {
        return offeredInSemester;
    }

    public void setOfferedInSemester(int offeredInSemester) {
        this.offeredInSemester = offeredInSemester;
    }

    public String getPreReq() {
        return preReq;
    }

    public void setPreReq(String preReq) {
        this.preReq = preReq;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public static ArrayList<Course> ShowAllCourses(Connection con){
        ArrayList<Course> courses = new ArrayList<Course>();
        try{
            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * from Course");
            while (resultSet.next()){
                String code = resultSet.getString("Course_code");
                String name = resultSet.getString("Course_Name");
                int semester = resultSet.getInt("Offered_in_Semester");
                String preReq = resultSet.getString("Course_PreReq");
                int credits = resultSet.getInt("Course_CH");
                Course course = new Course(code, name, semester, preReq, credits);

                courses.add(course);
            }
        }catch (SQLException e){
            System.out.println("No Course Exists " + e.getMessage());
        }
        return courses;
    }

    public static ArrayList<Course> ShowCoursesInSem(Connection connection, int semseter){
        ArrayList<Course> courses = new ArrayList<Course>();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * from Course WHERE Offered_In_Semester = '"+semseter+"'");
            while (resultSet.next()){
                String code = resultSet.getString("Course_code");
                String name = resultSet.getString("Course_Name");
                int semester = resultSet.getInt("Offered_in_Semester");
                String preReq = resultSet.getString("Course_PreReq");
                int credits = resultSet.getInt("Course_CH");
                Course course = new Course(code, name, semester, preReq, credits);
                courses.add(course);
            }
        }catch (SQLException e){
            System.out.println("No Course Exists " + e.getMessage());
        }
        return courses;
    }

    public static Course SectionCourse(Connection connection, String courseCode){
        Course course = new Course();
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * from Course WHERE Course_Code = '"+courseCode+"'");
            while (resultSet.next()){
                String code = resultSet.getString("Course_code");
                String name = resultSet.getString("Course_Name");
                int semester = resultSet.getInt("Offered_in_Semester");
                String preReq = resultSet.getString("Course_PreReq");
                int credits = resultSet.getInt("Course_CH");
                course = new Course(code, name, semester, preReq, credits);

            }
        }catch (SQLException e){
            System.out.println("No Course Exists " + e.getMessage());
        }
        return course;
    }
}
