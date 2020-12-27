package com.lms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Attendance {
    String date;
    char Value;

    public Attendance(String date, char value) {
        this.date = date;
        Value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public char getValue() {
        return Value;
    }

    public void setValue(char value) {
        Value = value;
    }

    public static ArrayList<Attendance> RegisteredAttandance(Connection connection, String sectionCode, String rno){
        ArrayList<Attendance> attendance = new ArrayList<Attendance>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Attendance WHERE Registered_Section_Code = '"+
                    sectionCode+"' AND Registered_Student_RollNo = '"+rno+"'");
            while (resultSet.next()){
                String date = resultSet.getString("Date");
                String pre = resultSet.getString("present");
                char present = pre.charAt(0);
                Attendance attendance1 = new Attendance(date, present);
                attendance.add(attendance1);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return attendance;
    }
}
