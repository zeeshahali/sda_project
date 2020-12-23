package com.lms;

public class Course {
    String code;
    String name;
    int offeredInSemester;
    String preReq;

    public Course(String code, String name, int offeredInSemester, String preReq){
        this.code = code;
        this.name = name;
        this.offeredInSemester = offeredInSemester;
        this.preReq = preReq;
    }
}
