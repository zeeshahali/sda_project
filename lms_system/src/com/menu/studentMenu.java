package com.menu;

import java.util.Scanner;

public class studentMenu {
    public studentMenu (Scanner input){
        String menu = "1. Register to a course.\n2. Drop a course.\n3. Subscribe to a section.\n" +
                "4. View your attendance.\n5. View your transcript.\n0. To go back a menu.\nEnter your choice: ";
        System.out.println(menu);
        int choice = input.nextInt();
        if(choice == 0) new mainMenu(input);
        else if(choice == 1) registerCourse(input);
        else if(choice == 2) dropCourse(input);
        else if(choice == 3) subscribeToSection(input);
        else if(choice == 4) viewAttendance(input);
        else if(choice == 5) viewTranscript(input);
        new studentMenu(input);
    }

    void registerCourse(Scanner input){
        System.out.println("registerCourse function called");
    }

    void dropCourse(Scanner input){
        System.out.println("dropCourse function called");
    }

    void subscribeToSection(Scanner input){
        System.out.println("subscribeToSection function called");
    }

    void viewAttendance(Scanner input){
        System.out.println("viewAttendance function called");
    }

    void viewTranscript(Scanner input){
        System.out.println("viewTranscript function called");
    }

}
