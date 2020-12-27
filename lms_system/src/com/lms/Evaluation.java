package com.lms;

import java.awt.geom.Arc2D;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Evaluation {
    String Name;
    String Type_of_Evaluation;
    int Weight;
    float Marks_Obtained;
    int Total_Marks;

    public Evaluation(String Name, String TOE, int Weight, int Total_Marks){
        this.Name = Name;
        this.Type_of_Evaluation = TOE;
        this.Weight = Weight;
        this.Total_Marks = Total_Marks;
    }

    public int getTotal_Marks() {
        return Total_Marks;
    }

    public void setTotal_Marks(int total_Marks) {
        Total_Marks = total_Marks;
    }

    public float getMarks_Obtained() {
        return Marks_Obtained;
    }

    public void setMarks_Obtained(float marks_Obtained) {
        Marks_Obtained = marks_Obtained;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType_of_Evaluation() {
        return Type_of_Evaluation;
    }

    public void setType_of_Evaluation(String type_of_Evaluation) {
        Type_of_Evaluation = type_of_Evaluation;
    }

    public int getWeight() { return Weight; }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public static ArrayList<Evaluation> AllEvaluations(Connection connection){
        ArrayList<Evaluation> evaluations = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * From Evaluations");
            while(resultSet.next()){
                String name = resultSet.getString("Evaluation_Name");
                String type = resultSet.getString("Evaluation_Type");
                int weight = resultSet.getInt("Weightage");
                float marksObt = resultSet.getInt("Marks_Obtained");
                int total = resultSet.getInt("Total_Marks");
                Evaluation evaluation = new Evaluation(name, type, weight, total);
                evaluation.setMarks_Obtained(marksObt);
                evaluations.add(evaluation);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return evaluations;
    }

    public static ArrayList<Evaluation> SectionEvaluations(Connection connection, Section section){
        ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * From Evaluations WHERE Registered_Section_Code = '"+section.getCode()+"'");
            while(resultSet.next()){
                String name = resultSet.getString("Evaluation_Name");
                String type = resultSet.getString("Evaluation_Type");
                int weight = resultSet.getInt("Weightage");
                float marksObt = resultSet.getInt("Marks_Obtained");
                int total = resultSet.getInt("Total_Marks");
                Evaluation evaluation = new Evaluation(name, type, weight, total);
                evaluation.setMarks_Obtained(marksObt);
                evaluations.add(evaluation);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return evaluations;
    }
}
