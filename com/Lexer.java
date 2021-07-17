package com;

import java.util.ArrayList;

public class Lexer {

    public static void isValid(ArrayList<Token> list) {
        Parentheses(list);
        Equality(list);
        Operators(list);
    }

    private static void Operators(ArrayList<Token> list) {
        for (int i = 0; i < list.size(); i++) {
            Token token = list.get(i);
            if (token.getType() == Token.Type.operator) {
                if (i == 0 && token.getOp() == '=') {
                    throw new RuntimeException("Equation starts with equality");
                } else if (i == 0 && token.getOp() != '-') {
                    throw new RuntimeException("Invalid operator - \"" + token.getOp() + "\"");
                } else if (i + 1 == list.size()) {
                    throw new RuntimeException("Invalid last operator - \"" + token.getOp() + "\"");
                } else if (token.getOp() != '=' && list.get(i + 1).getType() == Token.Type.operator) {
                    throw new RuntimeException("Invalid operators - \"" + token.getOp() +
                            "\" and \"" + list.get(i + 1).getOp() + "\"");
                } else if (token.getOp() == '=' && list.get(i + 1).getType() == Token.Type.operator &&
                    list.get(i + 1).getOp() != '-') {
                    throw new RuntimeException("Invalid operators - \"" + token.getOp() +
                            "\" and \"" + list.get(i + 1).getOp() + "\"");
                }
            }
        }
    }

    private static void Parentheses(ArrayList<Token> list) {
        for (int i = 0, count = 0; i < list.size(); i++) {
            Token token = list.get(i);
            if (token.getType() == Token.Type.parenthesis) {
                if (token.getOp() == ')' && count == 0) {
                    throw new RuntimeException("Incorrect open parenthesis - )");
                } else if (token.getOp() == '(') {
                    count++;
                } else {
                    count--;
                }
            }
            if (token.getType() == Token.Type.operator && token.getOp() == '=' && count != 0) {
                int len = i + 1;
                while (len < list.size() && list.get(len).getType() != Token.Type.parenthesis)
                    len++;
                if (len < list.size() && list.get(len).getOp() == ')')
                    throw new RuntimeException("Equality in parentheses - \"( = )\"");
                else
                    throw new RuntimeException("The closed parenthesis is missing - )");
            }
            if (i + 1 == list.size() && count != 0) {
                throw new RuntimeException("The closed parenthesis is missing - )");
            }
        }
    }

    private static void Equality(ArrayList<Token> list) {
        for (int i = 0, count = 0; i < list.size(); i++) {
            Token token = list.get(i);
            if (token.getType() == Token.Type.operator && token.getOp() == '=') {
                count++;
            }
            if (i + 1 == list.size() && count != 1) {
                throw new RuntimeException("Incorrect equality - \"=\" ");
            }
        }
    }

}
