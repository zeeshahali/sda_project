package com.menu;

import com.Utility.InputReader;
import com.lms.Course;
import com.lms.Section;
import com.lms.Student;
import com.lms.Teacher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu extends Menu {

    public MainMenu(Connection _con) {
        this.con = _con;
    }
    public void ShowMenu(){
        System.out.println("***** Main Menu *****");
        String users = "1. AdminLogin.\t 2. TeacherLogin.\t 3. StudentLogin.\n4. See All Sections.\n" +
                "5. See All Teachers.\n6. See All Students.\n7. See All Courses.\n" +
                "0. exit the program.\nEnter your choice: ";
        System.out.println(users);
        int choice = InputReader.getInstance().GetInt();
        if (choice == 0)  System.out.println("***** You are exiting the program *****");
        else{
            if (choice == 1)    adminPanel(con);
            else if (choice == 2)   teacherPanel(con);
            else if (choice == 3)   studentPanel(con);
            else if (choice == 4)   sectionsPanel(con);
            else if (choice == 5)   teachersPanel(con);
            else if (choice == 6)   studentsPanel(con);
            else if (choice == 7)   coursesPanel(con);
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

    public void sectionsPanel(Connection con){
        System.out.println("***** Sections *****");
        ArrayList<Section> sections = Section.AllSections(con);
        for (Section section: sections) section.PrintSection();
        ShowMenu();
    }

    public void teachersPanel(Connection con){
        System.out.println("***** Teachers *****");
        ArrayList<Teacher> teachers = Teacher.AllTeachers(con);
        for (Teacher teacher: teachers) teacher.PrintTeacher();
        ShowMenu();
    }

    public void studentsPanel(Connection con){
        System.out.println("***** Students *****");
        ArrayList<Student> students = Student.AllStudents(con);
        for (Student student: students) student.PrintStudent();
        ShowMenu();
    }

    public void coursesPanel(Connection con){
        System.out.println("***** Courses *****");
        ArrayList<Course> courses = Course.ShowAllCourses(con);
        for (Course course: courses) {
            course.PrintCourse();
        }
        ShowMenu();
    }

}
