package com.lms;

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
}
