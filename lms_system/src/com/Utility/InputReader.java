package com.Utility;

import jdk.dynalink.linker.LinkerServices;

import java.util.Scanner;

public class InputReader {
    private static InputReader instance;
    public Scanner scanner;

    private InputReader() {
        scanner = new Scanner(System.in);
    }
    public static InputReader getInstance(){
        if(instance == null)
            instance = new InputReader();
        return instance;
    }

    public String GetString(String displayString){
        System.out.println(displayString);
        String input = scanner.nextLine();
        return input;
    }

    public String GetString(){
        String input = scanner.nextLine();
        return input;
    }

    public int GetInt(String displayString){
        System.out.println(displayString);
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public int GetInt(){
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public char GetChar(String displayString, int index){
        System.out.println(displayString);
        char input = scanner.next().charAt(index);
        scanner.nextLine();
        return input;
    }

    public float GetFloat(String displayString){
        System.out.println(displayString);
        float input = scanner.nextFloat();
        scanner.nextLine();
        return input;
    }

    public float GetFloat(){
        float input = scanner.nextFloat();
        scanner.nextLine();
        return input;
    }
}
