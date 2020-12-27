package com.menu;

import java.sql.Connection;
import java.util.Scanner;

abstract class Menu {
    Connection con;

    public Menu(){}
    public Menu(Connection _con) {
        this.con = _con;
    }
    public abstract void ShowMenu();
    abstract boolean Authenticate();
    abstract void HandleChoice(int choice);

}
