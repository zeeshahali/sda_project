package com.lms;
import java.util.ArrayList;

public class Section{
    String code;
    String name;
    int Total_Number_Of_Seats;
    int Number_Of_Seats_Available;
    Course course;
    Teacher teacher;
    ArrayList<Evaluation> Evaluations;
    ArrayList<Student> Waiting_List = new ArrayList<Student>();
    ArrayList<Student> students = new ArrayList<Student>();

public Section(String code, String name, int Total_Number_Of_Seats, Course course, Teacher teacher){
        this.code = code;
        this.name = name;
        this.Total_Number_Of_Seats = Total_Number_Of_Seats;
        this.Number_Of_Seats_Available = Total_Number_Of_Seats;
        this.course = course;
        this.teacher = teacher;
        }

public void Add_Student(Student s){
        students.add(s);
        }

public void Remove_Student(Student s){
        students.remove(s);
        }

public ArrayList<Student> getStudents() {
        return students;
        }

public void setStudents(ArrayList<Student> students) {
        this.students = students;
        }

public void Update_Section(){
        course.add_section(this);
        teacher.add_Section(this);
        }

public ArrayList<Student> getWaiting_List() {
        return Waiting_List;
        }

public void setWaiting_List(ArrayList<Student> waiting_List) {
        Waiting_List = waiting_List;
        }

public Course getCourse() {
        return course;
        }

public void setCourse(Course course) {
        this.course = course;
        }

public String getCode() {
        return code;
        }

public void setCode(String code) {
        this.code = code;
        }

public String getName() {
        return name;
        }

public void setName(String name) {
        this.name = name;
        }

public int getTotal_Number_Of_Seats() {
        return Total_Number_Of_Seats;
        }

public void setTotal_Number_Of_Seats(int total_Number_Of_Seats) {
        Total_Number_Of_Seats = total_Number_Of_Seats;
        }

public int getNumber_Of_Seats_Available() {
        return Number_Of_Seats_Available;
        }

public void setNumber_Of_Seats_Available(int number_Of_Seats_Available) {
        Number_Of_Seats_Available = number_Of_Seats_Available;
        }

public Teacher getTeacher() {
        return teacher;
        }

public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        }

public ArrayList<Evaluation> getEvaluations() {
        return Evaluations;
        }

public void setEvaluations(ArrayList<Evaluation> evaluations) {
        Evaluations = evaluations;
        }

public void addSeat(){
        Number_Of_Seats_Available = Number_Of_Seats_Available - 1;
        }

public void removeSeat(){
        Number_Of_Seats_Available = Number_Of_Seats_Available + 1;
        }

public boolean isSeatAvailable(){
        if(Number_Of_Seats_Available > 0)  return true;
        else return false;
        }

public void insertEvaluation(Evaluation E){
        Evaluations.add(E);
        }

public void insert_Student_into_WaitingList(Student s){
        Waiting_List.add(s);
        }

}