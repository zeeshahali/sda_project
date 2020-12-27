package com.Utility;

import com.lms.Attendance;
import com.lms.Registration;
import com.lms.Section;
import com.menu.MainMenu;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static String url = "jdbc:jtds:sqlserver://DESKTOP-LQBADC6/lmsSystem;instance=SQLEXPRESS;user=sa;password=12345678";
    static Connection connection;

    public static Connection GetConnection(){
        return connection;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.setProperty("java.net.preferIPv6Addresses", "true");


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

    }
};
