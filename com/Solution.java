package com;

import java.util.ArrayList;

public class Solution {

    public static ArrayList<Token> reduceForm(ArrayList<Token> list) {
        convertX0(list);

        for (int i = 0; i < list.size(); i++) {
            i = findOp(list, i);
            if (i == -1)
                break;
            else {
                char c = list.get(i).getOp();
                double num = 0;

                if (list.get(i - 1).getType() != Token.Type.parenthesis &&
                        list.get(i + 1).getType() != Token.Type.parenthesis) {
                    i = makeOp(list, c, i);
                }
            }
        }
        convertX0(list);
//        openParenthesis(list);
        return list;
    }

//    private static int openParenthesis(ArrayList<Token> list) {
//        for (int i = 0; i < list.size(); i++) {
//            Token token = list.get(i);
//            if (token.getType() = Token.Type.parenthesis && token.getOp() == ')') {
//
//            }
//        }
//    }

    private static int makeOp(ArrayList<Token> list, char c, int i) {
        double num;

        if (list.get(i - 1).getType() == Token.Type.number) {
            if (c == '*')
                num = list.get(i - 1).getNum() * list.get(i + 1).getNum();
            else if (list.get(i + 1).getType() == Token.Type.number ||
                    list.get(i + 1).getType() == Token.Type.x0) {
                list.get(i + 1).setType(Token.Type.number);
                num = list.get(i - 1).getNum() / list.get(i + 1).getNum();
            }
            else {
                throw new RuntimeException("Invalid operation: " + list.get(i - 1).getToken()
                        + " " + list.get(i).getToken() + " " + list.get(i + 1).getToken());
            }
            list.get(i + 1).setNum(num);
            list.remove(i - 1);
            list.remove(i - 1);
        } else if (list.get(i + 1).getType() == Token.Type.number) {
            if (c == '*')
                num = list.get(i - 1).getNum() * list.get(i + 1).getNum();
            else {
                if (list.get(i + 1).getNum() == 0) {
                    throw new RuntimeException("Can't divide by zero: " + list.get(i - 1).getToken()
                            + " " + list.get(i).getToken() + " " + list.get(i + 1).getToken());
                }
                num = list.get(i - 1).getNum() / list.get(i + 1).getNum();
            }
            list.get(i - 1).setNum(num);
            list.remove(i);
            list.remove(i);
        } else {
            int x = (int)(list.get(i - 1).getStr().charAt(2)) - 48;
            if (c == '*') {
                num = list.get(i - 1).getNum() * list.get(i + 1).getNum();
                x = x + ((int)(list.get(i + 1).getStr().charAt(2)) - 48);

            }
            else if (list.get(i - 1).getStr().compareTo(list.get(i + 1).getStr()) >= 0) {
                num = list.get(i - 1).getNum() / list.get(i + 1).getNum();
                x = x - ((int)(list.get(i + 1).getStr().charAt(2)) - 48);
            }
            else {
                throw new RuntimeException("Invalid operation: " + list.get(i - 1).getToken()
                        + " " + list.get(i).getToken() + " " + list.get(i + 1).getToken());
            }
            list.get(i - 1).setNum(num);
            list.get(i - 1).setType(x);
            list.remove(i);
            list.remove(i);
        }

        return --i;
    }

    private static int findOp(ArrayList<Token> list, int i) {
        for (; i < list.size(); i++) {
            Token token = list.get(i);
            if (token.getType() == Token.Type.operator && "*/".indexOf(token.getOp()) != -1) {
                return (i);
            }
        }
        return -1;
    }

    private static void convertX0(ArrayList<Token> list) {
        for (int i = 0; i < list.size(); i++) {
            Token token = list.get(i);
            if (token.getType() == Token.Type.x0) {
                token.setType(0);
            }
        }
    }
}
