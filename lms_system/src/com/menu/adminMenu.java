package com.menu;

import com.Utility.InputReader;
import com.lms.Admin;
import com.lms.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminMenu extends Menu {
    Admin admin;
    private String adminUsername;
    private String adminPassword;

    public AdminMenu(Connection _con) {
        con = _con;
        adminUsername = null;
        adminPassword = null;
    }

    public void ShowMenu(){
        if(CanTakeInput()) {
            TakeInput();
        }

        boolean result = Authenticate();
        if (result) {
            GetAdminData();
            System.out.println("Welcome " + admin.getName());
            String menu = "1. Add Course.\n2. Add Section.\n3. Open Registration.\n" +
                    "4. Close Registration.\n5. Set withdrawal deadline.\n0. To go back a menu.\n" +
                    "Enter your choice: ";
            System.out.println(menu);
            int choice = InputReader.getInstance().GetInt();
            HandleChoice(choice);
        }
        else{
            System.out.println("username or password incorrect!");
            adminUsername = null;
            ShowMenu();
        }
    }

    void GetAdminData(){
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet =  statement.executeQuery("SELECT * FROM Academic_Officer WHERE Officer_username = '" + adminUsername + "'");
            while (resultSet.next()){
                String name = resultSet.getString("Officer_Name");
                admin = new Admin(adminUsername, adminPassword, name);
            }
        } catch (SQLException e){
            System.out.println("Error getting admin data");
        }
    }

    void HandleChoice(int choice) {
        switch (choice) {
            case 0 -> {
                MainMenu mainMenu = new MainMenu(con);
                mainMenu.ShowMenu();
            }
            case 1 -> AddCourse();
            case 2 -> AddSection();
            case 3 -> openRegistration();
            case 4 -> closeRegistration();
            case 5 -> setDeadline();
            default -> {
                System.out.println("Invalid choice entered" + choice);
                ShowMenu();
            }
        }
    }

    private boolean CanTakeInput() {
        return adminUsername == null || adminPassword == null;
    }

    private void TakeInput() {
        System.out.println("Enter username: ");
        adminUsername = InputReader.getInstance().GetString();
        System.out.println("Enter password: ");
        adminPassword = InputReader.getInstance().GetString();
    }

    boolean Authenticate(){
        try {
            String uname = "";
            String pass = "";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Academic_Officer WHERE Officer_username = '" + adminUsername + "'");
            while (rs.next()) {
                uname = rs.getString("Officer_username");
                pass = rs.getString("Officer_Password");
            }
            if(uname.equals(adminUsername) && pass.equals(adminPassword)){
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Could not connect to the database " + e.getMessage());
        }
        return false;
    }

    void AddCourseToDB(Course c){
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("INSERT into Course values ('" + c.getCode() +
                    "', '"+c.getName()+"', '"+c.getOfferedInSemester()+"', '"+c.getPreReq()+"', '"+c.getCreditHours()+"')");

        }catch (SQLException e) {

            System.out.println("Course already exists " + e.getMessage());
        }
    }

    void AddCourse(){
        String Code = InputReader.getInstance().GetString("Enter Course Code : ");
        String Name = InputReader.getInstance().GetString("Enter Course Name : ");
        System.out.println("Enter Semester in which Course is offered : ");
        int offeredInSemester = InputReader.getInstance().GetInt();
        System.out.println("Enter Course PreReq Code (Enter None in case of no preReq): "); //Input the course code of prereq of the course if any
        String PreReq = InputReader.getInstance().GetString();
        System.out.println("Enter Course Credit Hours : ");
        int CH = InputReader.getInstance().GetInt();

        //We have to add below course in our DB
        Course c = new Course(Code,Name,offeredInSemester, PreReq,CH);

        AddCourseToDB(c);

    }

    void AddSection(){
        ArrayList<Course> courses = Course.ShowAllCourses(con);
        PrintAvailableCourses(courses);
        int choice = InputReader.getInstance().GetInt("Enter your choice");
        String Name = InputReader.getInstance().GetString("Enter Section Name : ");
        String Code = InputReader.getInstance().GetString("Enter Section Code : ");
        int Number_of_Seats = InputReader.getInstance().GetInt("Enter Total Number of Seats : ");

        //Give option for selecting course for this section
        //Give option for selecting teachers who teach this course
    }

    private void PrintAvailableCourses(ArrayList<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            System.out.print(i+1 + "- ");
            courses.get(i).PrintCourse();
        }
    }

    void openRegistration(){
        System.out.println("Enter the Semester: ");
        int semester = InputReader.getInstance().GetInt();
        System.out.println("Which section");
    }

    void closeRegistration(){
        System.out.println("closeRegistration function called");
    }

    void setDeadline(){
        System.out.println("setDeadline function called");
    }
}
