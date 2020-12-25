package com.menu;

import java.util.Scanner;

public class adminMenu {
    public adminMenu(Scanner input){
        String menu = "1. Add Course.\n2. Add Section.\n3. Open Registration.\n" +
                "4. Close Registration.\n5. Set withdrawal deadline.\n0. To go back a menu.\n" +
                "Enter your choice: ";
        System.out.println(menu);
        int choice = input.nextInt();
        if(choice == 0) new mainMenu(input);
        else if(choice == 1) addCourse(input);
        else if(choice == 2) addSection(input);
        else if(choice == 3) openRegistration(input);
        else if(choice == 4) closeRegistration(input);
        else if(choice == 5) setDeadline(input);
        new adminMenu(input);
    }

    void addCourse(Scanner input){
        System.out.println("addCourse function called");
    }

    void addSection(Scanner input){
        System.out.println("addSection function called");
    }

    void openRegistration(Scanner input){
        System.out.println("openRegistration function called");
    }

    void closeRegistration(Scanner input){
        System.out.println("closeRegistration function called");
    }

    void setDeadline(Scanner input){
        System.out.println("setDeadline function called");
    }
}
