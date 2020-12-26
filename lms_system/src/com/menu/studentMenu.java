package com.menu;

import com.lms.Registered;
import com.lms.Student;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentMenu extends Menu {
    Student student;
    public StudentMenu(Scanner _input, Connection _con) {
        input = _input;
        con = _con;
    }
    void ShowMenu(){
        String menu = "1. Register to a course.\n2. Drop a course.\n3. Subscribe to a section.\n" +
                "4. View your attendance.\n5. View your transcript.\n0. To go back a menu.\nEnter your choice: ";
        System.out.println(menu);
        int choice = input.nextInt();

        HandleChoice(choice);
    }

    private void HandleChoice(int choice) {
        switch (choice) {
            case 0 -> {
                MainMenu mainMenu = new MainMenu(input, con);
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

    private void RegisterCourse(){
        System.out.println("registerCourse function called");
    }

    private void DropCourse(){
        System.out.println("dropCourse function called");
    }

    private void SubscribeToSection(){
        System.out.println();
    }

    void ViewAttendance(){
        ArrayList<Registered> courses = student.getCourses();
        for (Registered course : courses) {
            if (course.getBatch() == student.getBatch()) {
                System.out.println(course.getSection().getCourse().getName() + " : "
                        + course.getCalculated_Attendence() + "%\n");
            }
        }

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
    }

}
