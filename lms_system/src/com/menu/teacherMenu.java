package com.menu;

import com.Utility.InputReader;
import com.Utility.Utils;
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
    private String teacherUsername;
    private String teacherPassword;

    public TeacherMenu(Connection _con) {
        con = _con;
        teacherUsername = null;
        teacherPassword = null;
    }

    Teacher GetTeacherData(Connection connection){
        try{
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Teacher WHERE Teacher_username = '"+teacherUsername+"'";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String name = resultSet.getString("Teacher_Name");
                teacher = new Teacher(teacherUsername, teacherPassword, name);
                //teacher.setSections();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return teacher;
    }

    public void ShowMenu() {
        if(CanTakeInput()) {
            TakeInput();
        }

        boolean result = Authenticate();
        if (result) {
            Utils.PrintDivider();
            teacher = GetTeacherData(con);
            String menu = "1. Set Evaluations.\n2. Record Attendance.\n3. Display marks sheet.\n" +
                    "4. Enter marks.\n5. Enter Grades.\n0. To go back a menu.\nEnter your choice: ";
            System.out.println(menu);
            int choice = InputReader.getInstance().GetInt();
            HandleChoice(choice);
        }
        else{
            System.out.println("username or password incorrect!");
            teacherUsername = null;
            ShowMenu();
        }
    }

    private boolean CanTakeInput() {
        return teacherUsername == null || teacherPassword == null;
    }

    private void TakeInput() {
        System.out.println("Enter username: ");
        teacherUsername = InputReader.getInstance().GetString();
        System.out.println("Enter password: ");
        teacherPassword = InputReader.getInstance().GetString();
    }

    void HandleChoice(int choice) {
        switch (choice) {
            case 0 -> {
                MainMenu mainMenu = new MainMenu(con);
                mainMenu.ShowMenu();
            }
            case 1 -> SetEvaluations();
            case 2 -> RecordAttendance();
            case 3 -> DisplayMarksSheet();
            case 4 -> EnterMarks();
            case 5 -> EnterGrades();
            default -> {
                System.out.println("Invalid choice entered" + choice);
                ShowMenu();
            }
        }
    }

    @Override
    boolean Authenticate() {
        try {
            String uname = "";
            String pass = "";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Teacher WHERE Teacher_username = '"
                    + teacherUsername + "'");
            while (rs.next()) {
                uname = rs.getString("Teacher_username");
                pass = rs.getString("Teacher_Password");
            }
            if (uname.equals(teacherUsername) && pass.equals(teacherPassword)) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Could not connect to the database " + e.getMessage());
        }
        return false;
    }

    private void SetEvaluations() {
        System.out.println("Enter Evaluation Name : ");
        String Name = InputReader.getInstance().GetString();
        System.out.println("Enter Type of Evaluation : ");
        String type = InputReader.getInstance().GetString();
        System.out.println("Enter Weightage of Evaluation : ");
        int weight = InputReader.getInstance().GetInt();
        System.out.println("Enter Total Marks of Evaluation : ");
        int Total_Marks = InputReader.getInstance().GetInt();
        Evaluation E = new Evaluation(Name, type, weight, Total_Marks);

        System.out.println("Choose Section for Evaluation\n");
        int inp;
        for (int i = 0; i < teacher.getSections().size(); i++) {
            System.out.println(teacher.getSections().get(i).getCourse() + "(" + teacher.getSections().get(i).getName() +
                    ")\nIf you want to select this section enter 1 else enter 0");
            inp = InputReader.getInstance().GetInt();
            if (inp == 1) {
                teacher.getSections().get(i).insertEvaluation(E);
            }
        }

    }

    private void RecordAttendance() {
        String date;
        char present;
        date = InputReader.getInstance().GetString("Enter Date: ");
        int inp = 0;
        for (int i = 0; i < teacher.getSections().size() && inp == 0; i++) {
            String sectionSelectionTxt = teacher.getSections().get(i).getCourse().getName() + "(" + teacher.getSections().get(i).getName() +
                    ")\nIf you want to select this section enter 1 else enter 0";
            inp = InputReader.getInstance().GetInt(sectionSelectionTxt);
            if (inp == 1) {
                System.out.println("For present press p, for absent press a after students name");
                for (int j = 0; j < teacher.getSections().get(i).getStudents().size(); j++) {
                    for (int x = 0; x < teacher.getSections().get(i).getStudents().get(j).getCourses().size(); x++) {
                        if (teacher.getSections().get(i).getStudents().get(j).getCourses().get(x).getSection() == teacher.getSections().get(i)) {
                            String string = teacher.getSections().get(i).getStudents().get(j).getName() + " : (p/a) : ";
                            present = InputReader.getInstance().GetChar(string, 0);
                            teacher.getSections().get(i).getStudents().get(j).getCourses().get(x).Add_Attendance(date, present);
                        }
                    }
                }
            }
        }
    }

    private void DisplayMarksSheet() {
        int inp = 0;
        for (int i = 0; i < teacher.getSections().size() && inp == 0; i++) {
            System.out.println(teacher.getSections().get(i).getCourse() + "(" + teacher.getSections().get(i).getName() +
                    ")\nIf you want to select this section enter 1 else enter 0");
            inp = InputReader.getInstance().GetInt();
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
            inp = InputReader.getInstance().GetInt();
            if (inp == 1) {
                for (int j = 0; j < teacher.getSections().get(i).getStudents().size(); j++) {
                    for (int x = 0; x < teacher.getSections().get(i).getStudents().get(j).getCourses().size(); x++) {
                        if (teacher.getSections().get(i).getStudents().get(j).getCourses().get(x).getSection() == teacher.getSections().get(i)) {
                            System.out.println(teacher.getSections().get(i).getStudents().get(j).getName() + " : Evaluations : \n");
                            ArrayList<Evaluation> Eva = teacher.getSections().get(i).getStudents().get(x).getCourses().get(x).getSection().getEvaluations();
                            for (int y = 0; y < Eva.size(); y++) {
                                System.out.println("No#" + (y + 1) + " Name : " + Eva.get(y).getName() + " Type : " + Eva.get(y).getType_of_Evaluation()
                                        + " Total Marks : " + Eva.get(y).getTotal_Marks() + "Enter Marks : ");
                                marks = InputReader.getInstance().GetFloat();
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
            inp = InputReader.getInstance().GetInt();
            if (inp == 1) {
                for (int j = 0; j < teacher.getSections().get(i).getStudents().size(); j++) {
                    for (int x = 0; x < teacher.getSections().get(i).getStudents().get(j).getCourses().size(); x++) {
                        if (teacher.getSections().get(i).getStudents().get(j).getCourses().get(x).getSection() == teacher.getSections().get(i)) {
                            System.out.println("\nName : " + teacher.getSections().get(i).getStudents().get(j).getName() + "Enter Grade : ");
                            CreditEarned = InputReader.getInstance().GetFloat();
                            teacher.getSections().get(i).getStudents().get(j).getCourses().get(x).setCreditEarned(CreditEarned);
                        }
                    }
                }
            }
        }
    }
}
