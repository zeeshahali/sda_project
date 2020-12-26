package com.menu;

import java.sql.Connection;
import java.util.Scanner;

public class MainMenu extends Menu {

    public MainMenu(Scanner _input, Connection _con) {
        this.input = _input;
        this.con = _con;
    }
    void ShowMenu(){
        System.out.println("***** Main Menu *****");
        String users = "1. Admin.\t 2.Teacher.\t 3.Student.\nEnter \"0\" to exit the program.\nEnter your choice: ";
        System.out.println("Choose your user type from the following:\n" + users);
        int choice = input.nextInt();
        if (choice == 0)  System.out.println("***** You are exiting the program *****");
        else{
            //authentication function will be called here
            if (choice == 1)    adminPanel(input, con);
            else if (choice == 2)   teacherPanel(input, con);
            else if (choice == 3)   studentPanel(input, con);
        }
    }

    public void adminPanel(Scanner input, Connection con){
        System.out.println("***** Admin Panel *****");
        AdminMenu menu = new AdminMenu(input, con);
        menu.ShowMenu();
    }

    public void teacherPanel(Scanner input, Connection con){
        System.out.println("***** Teacher's Panel *****");
        TeacherMenu menu = new TeacherMenu(input, con);
        menu.ShowMenu();
    }

    public void studentPanel(Scanner input, Connection con){
        System.out.println("***** Student Panel *****");
        StudentMenu menu = new StudentMenu(input, con);
        menu.ShowMenu();
    }

}
