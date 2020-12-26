package com.menu;

import com.lms.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminMenu extends Menu {
    private String adminUsername;
    private String adminPassword;

    public AdminMenu(Scanner _input, Connection _con) {
        input = _input;
        con = _con;
        adminUsername = null;
        adminPassword = null;
    }
    public void ShowMenu(){
        if(CanTakeInput()) {
            TakeInput();
        }

        boolean result = Authenticate(adminUsername, adminPassword);
        if (result) {
            String menu = "1. Add Course.\n2. Add Section.\n3. Open Registration.\n" +
                    "4. Close Registration.\n5. Set withdrawal deadline.\n0. To go back a menu.\n" +
                    "Enter your choice: ";
            System.out.println(menu);
            int choice = input.nextInt();
            switch (choice) {
                case 0 -> {
                    MainMenu mainMenu = new MainMenu(input, con);
                    mainMenu.ShowMenu();
                }
                case 1 -> addCourse();
                case 2 -> addSection();
                case 3 -> openRegistration();
                case 4 -> closeRegistration();
                case 5 -> setDeadline();
                default -> {
                    System.out.println("Invalid choice entered" + choice);
                    ShowMenu();
                }
            }
            if(choice != 0) ShowMenu();
        }
        else{
            System.out.println("username or password incorrect!");
            adminUsername = null;
            ShowMenu();
        }
    }

    private boolean CanTakeInput() {
        return adminUsername == null || adminPassword == null;
    }

    private void TakeInput() {
        System.out.println("Enter username: ");
        adminUsername = input.nextLine();
        System.out.println("Enter password: ");
        adminPassword = input.nextLine();
    }

    boolean Authenticate(String username, String password){
        try {
            String uname = "";
            String pass = "";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Academic_Officer WHERE Officer_username = '" + username + "'");
            while (rs.next()) {
                uname = rs.getString("Officer_username");
                pass = rs.getString("Officer_Password");
            }
            if(uname.equals(username) && pass.equals(password)){
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Could not connect to the database " + e.getMessage());
        }
        return false;
    }

    void addCourse(){
        System.out.println("Enter Course Name : ");
        String Name = input.next();
        System.out.println("Enter Course Code : ");
        String Code = input.next();
        System.out.println("Enter Semester in which Course is offered : ");
        int offeredInSemester = input.nextInt();
        System.out.println("Enter Course PreReq : ");
        String PreReq = input.next();
        System.out.println("Enter Course Credit Hours : ");
        int CH = input.nextInt();

        //We have to add below course in our DB
        Course c = new Course(Code,Name,offeredInSemester, PreReq,CH);

    }

    void addSection(){
        System.out.println("Enter Section Name : ");
        String Name = input.next();
        System.out.println("Enter Section Code : ");
        String Code = input.next();
        System.out.println("Enter Total Number of Seats : ");
        int Number_of_Seats = input.nextInt();
        //Give option for selecting course for this section
        //Give option for selecting teachers who teach this course
    }

    void openRegistration(){
        System.out.println("openRegistration function called");
    }

    void closeRegistration(){
        System.out.println("closeRegistration function called");
    }

    void setDeadline(){
        System.out.println("setDeadline function called");
    }
}
