package com.menu;

import java.sql.Connection;
import java.util.Scanner;

public class Menu {
    Scanner input;
    Connection con;

    public Menu(){}
    public Menu(Scanner _input, Connection _con) {
        this.input = _input;
        this.con = _con;
    }
    void ShowMenu(){}
    boolean Authenticate(String username, String password){ return true;}
}
