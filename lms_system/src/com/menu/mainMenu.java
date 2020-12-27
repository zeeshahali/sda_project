package com.menu;

import com.Utility.InputReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu extends Menu {

    public MainMenu(Connection _con) {
        this.con = _con;
    }
    public void ShowMenu(){
        System.out.println("***** Main Menu *****");
        String users = "1. Admin.\t 2.Teacher.\t 3.Student.\nEnter \"0\" to exit the program.\nEnter your choice: ";
        System.out.println("Choose your user type from the following:\n" + users);
        int choice = InputReader.getInstance().GetInt();
        if (choice == 0)  System.out.println("***** You are exiting the program *****");
        else{
            //authentication function will be called here
            if (choice == 1)    adminPanel(con);
            else if (choice == 2)   teacherPanel(con);
            else if (choice == 3)   studentPanel(con);
        }
    }

    @Override
    boolean Authenticate() {        return false;    }

    @Override
    void HandleChoice(int choice) {    }

    public void adminPanel(Connection con){
        System.out.println("***** Admin Panel *****");
        AdminMenu menu = new AdminMenu(con);
        menu.ShowMenu();
    }

    public void teacherPanel(Connection con){
        System.out.println("***** Teacher's Panel *****");
        TeacherMenu menu = new TeacherMenu(con);
        menu.ShowMenu();
    }

    public void studentPanel(Connection con){
        System.out.println("***** Student Panel *****");
        StudentMenu menu = new StudentMenu(con);
        menu.ShowMenu();
    }


}
