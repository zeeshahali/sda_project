package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    System.setProperty("java.net.preferIPv6Addresses", "true");

        String url = "jdbc:jtds:sqlserver://DESKTOP-LQBADC6/lmsSystem;instance=SQLEXPRESS;user=sa;password=12345678";

        //System.out.println(java.lang.System.getProperty("java.library.path"));

        try {

            String driverName = "net.sourceforge.jtds.jdbc.Driver";

            Class.forName(driverName);

            Connection con = DriverManager.getConnection(url);
            System.out.println("Successfully Connected to the database!");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
            while (rs.next()) {
                int Roll_Number = rs.getInt("Roll_Number");
                System.out.println(Roll_Number);
            }

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
