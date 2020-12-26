package com.menu;

import com.lms.Evaluation;
import com.lms.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class TeacherMenu extends Menu {
    Teacher teacher;

    public TeacherMenu(Scanner _input, Connection _con) {
        input = _input;
        con = _con;
    }

    void ShowMenu() {
        String menu = "1. Set Evaluations.\n2. Record Attendance.\n3. Display marks sheet.\n" +
                "4. Enter marks.\n5. Enter Grades.\n0. To go back a menu.\nEnter your choice: ";
        System.out.println(menu);
        int choice = input.nextInt();
        HandleChoice(choice);
    }

    private void HandleChoice(int choice) {
        switch (choice) {
            case 0 -> {
                MainMenu mainMenu = new MainMenu(input, con);
                mainMenu.ShowMenu();
            }
            case 1 -> SetEvaluations(input);
            case 2 -> RecordAttendance(input);
            case 3 -> DisplayMarksSheet(input);
            case 4 -> EnterMarks();
            case 5 -> EnterGrades();
            default -> {
                System.out.println("Invalid choice entered" + choice);
                ShowMenu();
            }
        }
    }

    boolean Authenticate(String username, String password) {
        try {
            String uname = "";
            String pass = "";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Teacher WHERE Teacher_username = '" + username + "'");
            while (rs.next()) {
                uname = rs.getString("Officer_username");
                pass = rs.getString("Officer_Password");
            }
            if (uname.equals(username) && pass.equals(password)) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Could not connect to the database " + e.getMessage());
        }
        return false;
    }

    private void SetEvaluations(Scanner input) {
        System.out.println("Enter Evaluation Name : ");
        String Name = input.next();
        System.out.println("Enter Type of Evaluation : ");
        String type = input.next();
        System.out.println("Enter Weightage of Evaluation : ");
        int weight = input.nextInt();
        System.out.println("Enter Total Marks of Evaluation : ");
        int Total_Marks = input.nextInt();
        Evaluation E = new Evaluation(Name, type, weight, Total_Marks);

        System.out.println("Choose Section for Evaluation\n");
        int inp;
        for (int i = 0; i < teacher.getSections().size(); i++) {
            System.out.println(teacher.getSections().get(i).getCourse() + "(" + teacher.getSections().get(i).getName() +
                    ")\nIf you want to select this section enter 1 else enter 0");
            inp = input.nextInt();
            if (inp == 1) {
                teacher.getSections().get(i).insertEvaluation(E);
            }
        }

    }

    private void RecordAttendance(Scanner input) {
        String date;
        char present;
        System.out.println("Enter Date");
        date = input.next();
        int inp = 0;
        for (int i = 0; i < teacher.getSections().size() && inp == 0; i++) {
            System.out.println(teacher.getSections().get(i).getCourse() + "(" + teacher.getSections().get(i).getName() +
                    ")\nIf you want to select this section enter 1 else enter 0");
            inp = input.nextInt();
            if (inp == 1) {
                System.out.println("For present press p, for absent press a after students name");
                for (int j = 0; j < teacher.getSections().get(i).getStudents().size(); j++) {
                    for (int x = 0; x < teacher.getSections().get(i).getStudents().get(j).getCourses().size(); x++) {
                        if (teacher.getSections().get(i).getStudents().get(j).getCourses().get(x).getSection() == teacher.getSections().get(i)) {
                            System.out.println(teacher.getSections().get(i).getStudents().get(j).getName() + " : (p/a) : ");
                            present = input.next().charAt(0);
                            teacher.getSections().get(i).getStudents().get(j).getCourses().get(x).Add_Attendance(date, present);
                        }
                    }
                }
            }
        }
    }

    private void DisplayMarksSheet(Scanner input) {
        int inp = 0;
        for (int i = 0; i < teacher.getSections().size() && inp == 0; i++) {
            System.out.println(teacher.getSections().get(i).getCourse() + "(" + teacher.getSections().get(i).getName() +
                    ")\nIf you want to select this section enter 1 else enter 0");
            inp = input.nextInt();
            if (inp == 1) {
                for (int j = 0; j < teacher.getSections().get(i).getStudents().size(); j++) {
                    for (int x = 0; x < teacher.getSections().get(i).getStudents().get(j).getCourses().size(); x++) {
                        if (teacher.getSections().get(i).getStudents().get(j).getCourses().get(x).getSection() == teacher.getSections().get(i)) {
                            System.out.println(teacher.getSections().get(i).getStudents().get(j).getName() + " : Evaluations : \n");
                            ArrayList<Evaluation> Eva = teacher.getSections().get(i).getStudents().get(x).getCourses().get(x).getSection().getEvaluations();
                            for (int y = 0; y < Eva.size(); y++) {
                                System.out.println("No#" + (y + 1) + " : " + Eva.get(y).getName() + " : " + Eva.get(y).getType_of_Evaluation()
                                        + " : " + Eva.get(y).getMarks_Obtained() + "/" + Eva.get(y).getTotal_Marks() + "\n\n");
                            }
                        }
                    }
                }
            }
        }
    }

    private void EnterMarks() {
        int inp = 0;
        float marks;
        for (int i = 0; i < teacher.getSections().size() && inp == 0; i++) {
            System.out.println(teacher.getSections().get(i).getCourse() + "(" + teacher.getSections().get(i).getName() +
                    ")\nIf you want to select this section enter 1 else enter 0");
            inp = input.nextInt();
            if (inp == 1) {
                for (int j = 0; j < teacher.getSections().get(i).getStudents().size(); j++) {
                    for (int x = 0; x < teacher.getSections().get(i).getStudents().get(j).getCourses().size(); x++) {
                        if (teacher.getSections().get(i).getStudents().get(j).getCourses().get(x).getSection() == teacher.getSections().get(i)) {
                            System.out.println(teacher.getSections().get(i).getStudents().get(j).getName() + " : Evaluations : \n");
                            ArrayList<Evaluation> Eva = teacher.getSections().get(i).getStudents().get(x).getCourses().get(x).getSection().getEvaluations();
                            for (int y = 0; y < Eva.size(); y++) {
                                System.out.println("No#" + (y + 1) + " Name : " + Eva.get(y).getName() + " Type : " + Eva.get(y).getType_of_Evaluation()
                                        + " Total Marks : " + Eva.get(y).getTotal_Marks() + "Enter Marks : ");
                                marks = input.nextFloat();
                                Eva.get(y).setMarks_Obtained(marks);
                            }
                        }
                    }
                }
            }
        }
    }

    private void EnterGrades() {
        int inp = 0;
        float CreditEarned;
        for (int i = 0; i < teacher.getSections().size() && inp == 0; i++) {
            System.out.println(teacher.getSections().get(i).getCourse() + "(" + teacher.getSections().get(i).getName() +
                    ")\nIf you want to select this section enter 1 else enter 0");
            inp = input.nextInt();
            if (inp == 1) {
                for (int j = 0; j < teacher.getSections().get(i).getStudents().size(); j++) {
                    for (int x = 0; x < teacher.getSections().get(i).getStudents().get(j).getCourses().size(); x++) {
                        if (teacher.getSections().get(i).getStudents().get(j).getCourses().get(x).getSection() == teacher.getSections().get(i)) {
                            System.out.println("\nName : " + teacher.getSections().get(i).getStudents().get(j).getName() + "Enter Grade : ");
                            CreditEarned = input.nextFloat();
                            teacher.getSections().get(i).getStudents().get(j).getCourses().get(x).setCreditEarned(CreditEarned);
                        }
                    }
                }
            }
        }
    }
}
