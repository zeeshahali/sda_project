package com.lms;

import java.util.ArrayList;

public class Registered{
    Section section;
    String withdrawalDate;
    float creditEarned;
    ArrayList<Attendance> attendance = new ArrayList<Attendance>();
    int batch;
    float calculated_Attendance;

    public Registered(int batch, String withdrawalDate, Section section, float CE){
        this.withdrawalDate = withdrawalDate;
        this.section = section;
        this.batch = batch;
        this.creditEarned = CE;
    }

    public void Add_Attendance(String d, char Val){
        Attendance A = new Attendance(d, Val);
        attendance.add(A);
    }

    public float getCalculated_Attendance() {
        return calculated_Attendance;
    }

    public void setCalculated_Attendance(float calculated_Attendance) {
        calculated_Attendance = calculated_Attendance;
    }

    public ArrayList<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(ArrayList<Attendance> attendance) {
        this.attendance = attendance;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public float getCreditEarned() {
        return creditEarned;
    }

    public void setCreditEarned(float creditEarned) {
        this.creditEarned = creditEarned;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

}
