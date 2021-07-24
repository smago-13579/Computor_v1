package com;

import java.util.ArrayList;

public class Equation {

    private ArrayList<Token> list;

    public Equation(String str) throws Exception {
        Parse parse = new Parse(str);
        list = parse.getList();

        Lexer.isValid(list);
    }

    public void reduceForm() {
        Solution.reduceForm(list);
        System.out.print("Reduced form: ");
        Token.printTokens(list);
    }

    public void steps(int i) {
        if (i == 0) {
            Solution.transferAndAlign(list);
            System.out.print("Step 0: ");
            Token.printTokens(list);
        }
        else if (i == 1) {
            Maths.addition(list, 0, list.size() - 1, 0);
            Solution.removeEmptyToken(list);
            System.out.print("Step 1: ");
            Token.printTokens(list);
            Lexer.checkPower(list);
        }
    }


}