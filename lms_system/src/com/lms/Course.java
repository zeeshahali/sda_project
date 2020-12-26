package com.lms;

import java.util.ArrayList;

public class Course {
    String code;
    String name;
    int offeredInSemester;
    String preReq;
    int creditHours;
    ArrayList<Section> course_section = new ArrayList<Section>();

    public Course(String code, String name, int offeredInSemester, String preReq, int CH) {
        this.code = code;
        this.name = name;
        this.offeredInSemester = offeredInSemester;
        this.preReq = preReq;
        this.creditHours = CH;
    }

    public void add_section(Section s) {
        this.course_section.add(s);
    }

    public void Remove_section(Section s) {
        this.course_section.remove(s);
    }

    public ArrayList<Section> getCourse_section() {
        return course_section;
    }

    public void setCourse_section(ArrayList<Section> course_section) {
        this.course_section = course_section;
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

    public int getOfferedInSemester() {
        return offeredInSemester;
    }

    public void setOfferedInSemester(int offeredInSemester) {
        this.offeredInSemester = offeredInSemester;
    }

    public String getPreReq() {
        return preReq;
    }

    public void setPreReq(String preReq) {
        this.preReq = preReq;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
}
