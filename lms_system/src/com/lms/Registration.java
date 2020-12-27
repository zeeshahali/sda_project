package com.lms;

public class Registration {
    Section section;
    int id;
    boolean active;
    public Registration(int _id, boolean _active, Section _section){
        this.id = _id;
        this.active = _active;
        this.section = _section;
    }

    public int getId(){return id;}
    public boolean getActive(){return active;}
    public Section getSection(){return section;}

    public void setId(int _id){
        id = _id;
    }
    public void setActive(boolean _active){
        active = _active;
    }
    public void setSection(Section _section){
        section = _section;
    }
}
