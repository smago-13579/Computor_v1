package com;

import java.util.ArrayList;

public class Lexer {

    public static void isValid(ArrayList<Token> list) {
        Parentheses(list);
        Equality(list);
        Operators(list);
        Variables(list);
    }

    public static void checkPower(ArrayList<Token> list) {
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
    }

    private static void Variables(ArrayList<Token> list) {
        char c = 'a';

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() == Token.Type.x0 ||
                    list.get(i).getType() == Token.Type.x1 ||
                    list.get(i).getType() == Token.Type.x2) {
                c = list.get(i).getStr().charAt(0);
                break;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            Token token = list.get(i);
            if (token.getType() != Token.Type.operator &&
                    token.getType() != Token.Type.parenthesis && i + 1 != list.size()) {
                if (list.get(i + 1).getType() != Token.Type.operator &&
                        list.get(i + 1).getType() !=  Token.Type.parenthesis) {
                    throw new RuntimeException("Invalid Expression: \"" + token.getToken() +
                            " " + list.get(i + 1).getToken() + "\"");
                } else if (list.get(i + 1).getType() == Token.Type.parenthesis &&
                        list.get(i + 1).getOp() == '(') {
                    throw new RuntimeException("Invalid Expression: \"" + token.getToken() + " (\"");
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            Token token = list.get(i);
            if ((token.getType() == Token.Type.x0 ||
                    token.getType() == Token.Type.x1 ||
                    token.getType() == Token.Type.x2) && c != token.getStr().charAt(0)) {
                throw new RuntimeException("Different Variables: \"" + c + " and " +
                        token.getStr().charAt(0) + "\"");
            }
        }
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
                } else if (list.get(i + 1).getType() == Token.Type.parenthesis && list.get(i + 1).getOp() == ')') {
                    throw new RuntimeException("Variable is missing after operator - \"" + token.getOp() + "\"");
                }
            } else if (token.getType() == Token.Type.parenthesis) {
                if (token.getOp() == ')' && i + 1 < list.size()) {
                    if (list.get(i + 1).getType() != Token.Type.operator &&
                            list.get(i + 1).getType() != Token.Type.parenthesis) {
                        throw new RuntimeException("Operator is missing after - \")\"");
                    } else if (list.get(i + 1).getType() == Token.Type.parenthesis &&
                            list.get(i + 1).getOp() == '(') {
                        throw new RuntimeException("Invalid parentheses - )(");
                    }
                } else if (token.getOp() == '(') {
                    if (list.get(i + 1).getType() == Token.Type.parenthesis &&
                            list.get(i + 1).getOp() == ')') {
                        throw new RuntimeException ("Invalid parentheses - ()");
                    } else if (list.get(i + 1).getType() == Token.Type.operator &&
                            list.get(i + 1).getOp() != '-') {
                        throw new RuntimeException ("Invalid operator \"" +
                                list.get(i + 1).getOp() + "\" after \"(\"");
                    }
                }
            }
        }
    }

    private static void Parentheses(ArrayList<Token> list) {
        for (int i = 0, count = 0; i < list.size(); i++) {
            Token token = list.get(i);
            if (token.getType() == Token.Type.parenthesis) {
                if (token.getOp() == ')' && count == 0) {
                    throw new RuntimeException("The open parenthesis is missing - \"(\"");
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
                    throw new RuntimeException("The closed parenthesis is missing - \")\"");
            }
            if (i + 1 == list.size() && count != 0) {
                throw new RuntimeException("The closed parenthesis is missing - \")\"");
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
