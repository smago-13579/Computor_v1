package com.company;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            if (args.length != 1)
                throw new Exception("Invalid number of arguments");
            Parse parse = new Parse(args[0]);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
    }
}