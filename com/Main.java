package com;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length != 1)
                throw new Exception("Invalid number of arguments");
            Equation equation = new Equation(args[0]);
            equation.sequenceOfActions();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        }
    }
}