package com.lms;

import java.util.ArrayList;

public class Registered extends Course{
    String deadline;
    ArrayList<Section> sections = new ArrayList<Section>();
    String term;
    int batch;

    public Registered(String code, String name, int offeredInSemester, String preReq, String deadline, ArrayList<Section> sections, String term, int batch){
        super(code, name, offeredInSemester, preReq);
        this.deadline = deadline;
        this.term = term;
    }

}
