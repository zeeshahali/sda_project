package com.menu;

import com.lms.Student;

import java.util.Scanner;

public class mainMenu {
    public mainMenu(Scanner input){
        System.out.println("***** Main Menu *****");
        String users = "1. Admin.\t 2.Teacher.\t 3.Student.\nEnter \"0\" to exit the program.\nEnter your choice: ";
        System.out.println("Choose your user type from the following:\n" + users);
        int choice = input.nextInt();
        if (choice == 0)  System.out.println("***** You are exiting the program *****");
        else{
            //authentication function will be called here
            if (choice == 1)    adminPanel(input);
            else if (choice == 2)   teacherPanel(input);
            else if (choice == 3)   studentPanel(input);
        }
    }

    public void adminPanel(Scanner input){
        System.out.println("***** Admin Panel *****");
        new adminMenu(input);
    }

    public void teacherPanel(Scanner input){
        System.out.println("***** Teacher's Panel *****");
        new teacherMenu(input);
    }

    public void studentPanel(Scanner input){
        System.out.println("***** Student Panel *****");
        new studentMenu(input);
    }

}
