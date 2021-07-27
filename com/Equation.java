package com;

import java.util.ArrayList;

public class Equation {

    private ArrayList<Token> list;

    public Equation(String str) throws Exception {
        Parse parse = new Parse(str);
        list = parse.getList();

        Lexer.isValid(list);
    }

    public void sequenceOfActions() {
        steps(0,"Step 0: ");
        steps(1,"Step 1: ");
        steps(2,"Step 2: ");
        steps(3, "Reduced form: ");

        int i = checkPower();
        if (i == 2) {
            Maths.findDiscriminantAndSolve(list);
        }
        else {
            Maths.solveWithoutDiscriminant(list, i);
        }
    }

    public void steps(int i, String str) {
        if (i == 0) {
            Solution.reduceForm(list);
        }
        else if (i == 1) {
            Solution.transferAndAlign(list);
        }
        else if (i == 2) {
            Maths.addition(list, 0, list.size() - 1, 0);
            Solution.removeEmptyToken(list);
        }
        else if (i == 3) {
            Solution.sortVariables(list);
        }
        System.out.print(str);
        Token.printTokens(list);
    }

    public int checkPower() {
        int max_pow = 0;

        for (int i = 0; i < list.size(); i++) {
            Token token = list.get(i);
            if (token.getType() == Token.Type.x2) {
                String str = token.getStr().substring(2);
                if (max_pow < Integer.parseInt(str))
                    max_pow = Integer.parseInt(str);
            } else if (token.getType() == Token.Type.x1) {
                if (max_pow < 1)
                    max_pow = 1;
            }
        }
        System.out.println("Polynomial degree: " + max_pow);
        if (max_pow > 2)
            throw new RuntimeException("The polynomial degree is strictly greater than 2, I can't solve.");
        return max_pow;
    }
}