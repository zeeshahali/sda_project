package com.lms;

public class Admin extends User{
    public Admin(String username, String password, String name){
        this.username = username;
        this.name = name;
        this.password = password;
    }
}
