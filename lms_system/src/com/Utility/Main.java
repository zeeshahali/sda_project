package com.Utility;

import com.lms.Attendance;
import com.lms.Section;
import com.menu.MainMenu;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static String url = "jdbc:jtds:sqlserver://DESKTOP-LQBADC6/lmsSystem;instance=SQLEXPRESS;user=sa;password=12345678";
    static Connection connection;
    /*@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }*/

    public static Connection GetConnection(){
        return connection;
    }

    public static void main(String[] args) {
        //launch(args);
        Scanner input = new Scanner(System.in);

        System.setProperty("java.net.preferIPv6Addresses", "true");

        //System.out.println(java.lang.System.getProperty("java.library.path"));

        try {

            String driverName = "net.sourceforge.jtds.jdbc.Driver";

            Class.forName(driverName);

            connection = DriverManager.getConnection(url);
            System.out.println("Successfully Connected to the database!");
            MainMenu mainMenu = new MainMenu(connection);
            mainMenu.ShowMenu();

        }catch (ClassNotFoundException e) {

                System.out.println("Could not find the database driver " + e.getMessage());

        } catch (SQLException e) {

            System.out.println("Could not connect to the database " + e.getMessage());
        }

        //Statement stmt = con.createStatement();
        //ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
        //int Roll_Number = rs.getInt("Roll_Number");
        //System.out.println(Roll_Number);

        //Student Std = new Student("Zeeshah Shafique Butt", "2001-02-24", "l181246@lhr.nu.edu.pk", "18L-1246", "CS", "Semester 5");

        //Std.print();
    }
};
