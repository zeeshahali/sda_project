package com.menu;

import com.Utility.InputReader;
import com.Utility.Utils;
import com.lms.Admin;
import com.lms.Course;
import com.lms.Section;
import com.lms.Teacher;

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
            Utils.PrintDivider();
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

    @Override
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
            statement.executeUpdate("INSERT into Course values ('" + c.getCode() +
                    "', '"+c.getName()+"', '"+c.getOfferedInSemester()+"', '"+c.getPreReq()+"', '"+c.getCreditHours()+"')");

        }catch (SQLException e) {

            System.out.println("Course already exists " + e.getMessage());
        }
    }

    void AddSectionToDB(Section s){
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT into Section values ('" + s.getCode()+
                    "', '"+s.getName()+"', '"+s.getTotal_Number_Of_Seats()+"', '"+s.getNumber_Of_Seats_Available()+
                    "', '"+s.getTeacher().getUsername()+
                    "', '"+s.getCourse().getCode()+"')");

        }catch (SQLException e) {

            System.out.println("Section already exists " + e.getMessage());
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
        Course c = new Course(Code,Name,offeredInSemester, PreReq,CH);
        AddCourseToDB(c);
    }

    void AddSection(){
        ArrayList<Course> courses = Course.ShowAllCourses(con);
        PrintAvailableCourses(courses);
        int course = InputReader.getInstance().GetInt("Enter your choice");
        String name = InputReader.getInstance().GetString("Enter Section Name : ");
        String code = InputReader.getInstance().GetString("Enter Section Code : ");
        int number_of_Seats = InputReader.getInstance().GetInt("Enter Total Number of Seats : ");
        ArrayList<Teacher> teachers = Teacher.AllTeachers(con);
        System.out.println("Choose from the teachers bellow: ");
        for (int i = 0; i < teachers.size(); i++) System.out.println(i+1+"- "+teachers.get(i).getName());
        int teacher = InputReader.getInstance().GetInt("Enter your choice");
        Section section = new Section(code, name, number_of_Seats, courses.get(course), teachers.get(teacher));
        teachers.get(teacher).add_Section(section);
        courses.get(course).add_section(section);
        AddSectionToDB(section);
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
        int section = InputReader.getInstance().GetInt("Enter the Section you want to open registration for: ");
    }

    void closeRegistration(){
        System.out.println("closeRegistration function called");
    }

    void setDeadline(){
        System.out.println("setDeadline function called");
    }
}
