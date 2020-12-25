package com.menu;

import java.util.Scanner;

public class teacherMenu {
    public teacherMenu (Scanner input){
        String menu = "1. Set Evaluations.\n2. Record Attendance.\n3. Display marks sheet.\n" +
                "4. Enter marks.\n5. Enter Grades.\n0. To go back a menu.\nEnter your choice: ";
        System.out.println(menu);
        int choice = input.nextInt();
        if(choice == 0) new mainMenu(input);
        else if(choice == 1) setEvaluations(input);
        else if(choice == 2) recordAttendance(input);
        else if(choice == 3) displayMarksSheet(input);
        else if(choice == 4) enterMarks(input);
        else if(choice == 5) enterGrades(input);
        new teacherMenu(input);
    }

    void setEvaluations(Scanner input){
        System.out.println("setEvaluations function called");
    }

    void recordAttendance(Scanner input){
        System.out.println("recordAttendance function called");
    }

    void displayMarksSheet(Scanner input){
        System.out.println("displayMarksSheet function called");
    }

    void enterMarks(Scanner input){
        System.out.println("enterMarks function called");
    }

    void enterGrades(Scanner input){
        System.out.println("enterGrades function called");
    }
}
