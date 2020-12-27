package com.menu;

import com.Utility.InputReader;
import com.Utility.Main;
import com.Utility.Utils;
import com.lms.*;
import jdk.jshell.execution.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentMenu extends Menu {
    Student student;
    private String studentUsername;
    private String StudentPassword;

    public StudentMenu(Connection _con) {
        con = _con;
        studentUsername = null;
        StudentPassword = null;
    }

    void StudentData(Connection connection){
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet =  statement.executeQuery("SELECT * FROM Student WHERE Student_username = '" +studentUsername+ "'");
            while (resultSet.next()){
                String name = resultSet.getString("Student_Name");
                String rollNo = resultSet.getString("Student_RollNo");
                int batch = resultSet.getInt("Student_Batch");
                float sgpa = resultSet.getFloat("SGPA");
                float cgpa = resultSet.getFloat("CGPA");
                student = new Student(studentUsername, studentUsername, name, rollNo, batch, sgpa, cgpa);
            }
        } catch (SQLException e){
            System.out.println("Error getting admin data");
        }
    }

    public void ShowMenu(){
        if(CanTakeInput()) {
            TakeInput();
        }
        boolean result = Authenticate();
        if (result) {
            Utils.PrintDivider();
            StudentData(con);
            String menu = "1. Register to a course.\n2. Drop a course.\n3. Subscribe to a section.\n" +
                    "4. View your attendance.\n5. View your transcript.\n0. To go back a menu.\nEnter your choice: ";
            System.out.println(menu);
            int choice = InputReader.getInstance().GetInt();

            HandleChoice(choice);
        }
        else{
            System.out.println("username or password incorrect!");
            studentUsername = null;
            ShowMenu();
        }
    }

    private boolean CanTakeInput() {
        return studentUsername == null || StudentPassword == null;
    }

    private void TakeInput() {
        System.out.println("Enter username: ");
        studentUsername = InputReader.getInstance().GetString();
        System.out.println("Enter password: ");
        StudentPassword = InputReader.getInstance().GetString();
    }

    void HandleChoice(int choice) {
        switch (choice) {
            case 0 -> {
                MainMenu mainMenu = new MainMenu(con);
                mainMenu.ShowMenu();
            }
            case 1 -> RegisterCourse();
            case 2 -> DropCourse();
            case 3 -> SubscribeToSection();
            case 4 -> ViewAttendance();
            case 5 -> ViewTranscript();
            default -> {
                System.out.println("Invalid choice entered" + choice);
                ShowMenu();
            }
        }
    }

    @Override
    boolean Authenticate(){
        try {
            String uname = "";
            String pass = "";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student WHERE Student_username = '" + studentUsername + "'");
            while (rs.next()) {
                uname = rs.getString("Student_username");
                pass = rs.getString("Student_Password");
            }
            if(uname.equals(studentUsername) && pass.equals(StudentPassword)){
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Could not connect to the database " + e.getMessage());
        }
        return false;
    }

    private void RegisterCourse(){
        ArrayList<Course> semCourse = Course.ShowCoursesInSem(con, student.getBatch());
        for (int i = 0; i < semCourse.size(); i++) {
            System.out.println(i + "- ");
            semCourse.get(i).PrintCourse();
        }
        int select = InputReader.getInstance().GetInt("Enter your selection");
        student.RegisterCourse(select);
        ShowMenu();
    }



    private void DropCourse(){
        ArrayList<Section> registeredSections =  student.RegisteredSections();
        for (Section section : registeredSections) section.PrintSection();
        String sect = InputReader.getInstance().GetString("Enter Section Code");
        student.DropSection(sect);
        ShowMenu();
    }

    private void SubscribeToSection(){
        System.out.println("Select the Course you want to subscribe: ");
        ArrayList<Course> courses = Course.ShowCoursesInSem(con, student.getBatch());
        for (Course course: courses) course.PrintCourse();
        String course = InputReader.getInstance().GetString("Enter the course code for the course you want to subscribe");
        System.out.println(course+ " Subscribed successfully");
        ShowMenu();
    }

    void ViewAttendance(){
        ArrayList<Registered> courses = student.getCourses();
        for (Registered course : courses) {
            if (course.getBatch() == student.getBatch()) {
                System.out.println(course.getSection().getCourse().getName() + " : "
                        + course.getCalculated_Attendance() + "%\n");
            }
        }
        ShowMenu();
    }

    void ViewTranscript(){
        ArrayList<Registered> courses = student.getCourses();
        for(int i=0 ; i<student.getBatch() ; i++){
            System.out.println("Semester # " + i  + "\n");
            for(int j=0 ; j<courses.size() ; j++) {
                if (courses.get(i).getBatch() == i) {
                    System.out.println(courses.get(i).getSection().getCourse().getName() + " : "
                            + courses.get(i).getCreditEarned() + "/" +
                            + courses.get(i).getSection().getCourse().getCreditHours() + "\n");
                }
            }
            System.out.println("\n");
        }
        ShowMenu();
    }

}
