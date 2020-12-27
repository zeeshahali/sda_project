package com.lms;

import com.Utility.Main;
import com.Utility.Utils;
import com.sun.webkit.network.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Registration {
    Section section;
    int id;
    char active;
    String deadline;
    public Registration(int _id, char _active, Section _section, String _deadline){
        this.id = _id;
        this.active = _active;
        this.section = _section;
        this.deadline = _deadline;
    }

    public void PrintRegistration(){
        System.out.println("Registration ID: "+this.id+ "\nSection: "+section.getName()+ "\nStatus: "+this.active+
                "\nDeadline: "+this.deadline);
        Utils.PrintDivider();
    }

    public int getId(){return id;}
    public char getActive(){return active;}
    public Section getSection(){return section;}
    public String getDeadline(){return deadline;}

    public void setId(int _id){
        id = _id;
    }
    public void setActive(char _active){
        Connection connection = Main.GetConnection();
        active = _active;
        try{
            Statement statement = connection.createStatement();
            String sql = "UPDATE Registration SET status = '"+active+"' WHERE ID = '"+this.id+"'";
            statement.executeUpdate(sql);
        }catch (SQLException e){System.out.println(e.getMessage());}
    }
    public void setSection(Section _section){
        section = _section;
    }
    public void setDeadline(String _deadline){
        Connection connection = Main.GetConnection();
        deadline =_deadline;
        try{
            Statement statement = connection.createStatement();
            String sql = "UPDATE Registration SET deadline = '"+deadline+"' WHERE ID = '"+this.id+"'";
            statement.executeUpdate(sql);
        }catch (SQLException e){System.out.println(e.getMessage());}
    }

    public static ArrayList<Registration> AllRegistrations(Connection connection){
        ArrayList<Registration> registrations = new ArrayList<Registration>();
        try{
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Registration";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                String section = resultSet.getString("Section_Code");
                char status = resultSet.getString("status").charAt(0);
                String deadline = resultSet.getString("deadline");
                Registration registration = new Registration(id, status, Section.GetSectionFromCode(connection, section) , deadline);
                registrations.add(registration);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return registrations;
    }
}
