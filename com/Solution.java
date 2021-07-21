package com;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Solution {

    public static ArrayList<Token> reduceForm(ArrayList<Token> list) {
        convertX0(list);
        multiplyDivide(list);
        openParenthesis(list);

        return list;
    }

    private static void multiplyDivide(ArrayList<Token> list) {
        for (int i = 0; i < list.size(); i++) {
            i = findOp(list, i);
            if (i == -1)
                break;
            else {
                char c = list.get(i).getOp();

                if (list.get(i - 1).getType() != Token.Type.parenthesis &&
                        list.get(i + 1).getType() != Token.Type.parenthesis) {
                    i = makeOp(list, c, i);
                }
            }
        }
        setMark(list);
    }

    private static void openParenthesis(ArrayList<Token> list) {
        int open = 0;
        int first = 0;
        int close = 0;

        for (int i = 0; i < list.size(); i++) {
            Token token = list.get(i);
            if (token.getType() == Token.Type.parenthesis && token.getOp() == '(') {
                if (first == 0) {
                    first = i;
                    open = i;
                } else {
                    open = i;
                }
            }
            if (token.getType() == Token.Type.parenthesis && token.getOp() == ')') {
                close = i;
                close = mathOp(list, open, close, 0);
                i = close;
                removeParentheses(list, open, close); // ???
            }
        }
        for (int i = 0; i < list.size(); i++) {
            Token token = list.get(i);
        }
    }

    private static void removeParentheses(ArrayList<Token> list, int open, int close) {
        if (close - open == 2) {
            list.remove(close);
            list.remove(open);
            setMark(list);
        }
        else if (open != 0 && list.get(open - 1).getOp() == '*') {
            char c = '*';
            Token token1 = list.get(open - 2);
            for (int i = open + 1; i < close; i++) {
                Token token2 = list.get(i);
                makeOp(token1, token2, c);
            }
        }
//        else if ((open == 0 || "*/".indexOf(list.get(open - 1).getOp()) == -1) &&
//                (close + 1 == list.size() || "*/".indexOf(list.get(close + 1).getOp()) == -1)) {
//            if (open != 0 && list.get(open - 1).getOp() == '-') {
//                for (int i = open + 1; i < close; i++) {
//                    list.get(i).setNum('-');
//                }
//            }
//            list.remove(close);
//            list.remove(open);
//        }
    }

    private static int mathOp(ArrayList<Token> list, int open, int close, int count) {
        for (int var = open + 1; var < close; var++) {
            Token token1 = list.get(var);
            for (int i = var + 1; i < close; i++) {
                Token token2 = list.get(i);
                if (token1.getType() == token2.getType()) {
                    token1.setNum(token1.getNum() + token2.getNum());
                    list.remove(i--);
                    close--;
                }
            }
        }
        if (count == 0)
            close = mathOp(list, open, close, 1);
        return close;
    }

    private static Token makeOp(Token token1, Token token2, char c) {
        Token token = new Token(1);
        double num;

        if (token1.getType() == Token.Type.number) {
            if (token1.getNum() == 0)
                num = 0;
            else if (c == '*')
                num = token1.getNum() * token2.getNum();
            else if (token2.getType() == Token.Type.number &&
                    token2.getNum() != 0) {
                token2.setType(Token.Type.number);
                num = token1.getNum() / token2.getNum();
            }
            else if (token2.getNum() == 0) {
                throw new RuntimeException("Can't divide by zero: " + token1.getToken()
                        + " " + token2.getToken() + " " + token2.getToken());
            }
            else {
                throw new RuntimeException("Invalid operation: " + token1.getToken()
                        + " " + c + " " + token2.getToken());
            }
//            list.get(i + 1).setNum(num);
//            list.remove(i - 1);
//            list.remove(i - 1);
        }

        return token1;
    }

    private static int makeOp(ArrayList<Token> list, char c, int i) {
        double num;

        if (list.get(i - 1).getType() == Token.Type.number) {
            if (list.get(i - 1).getNum() == 0)
                num = 0;
            else if (c == '*')
                num = list.get(i - 1).getNum() * list.get(i + 1).getNum();
            else if (list.get(i + 1).getType() == Token.Type.number &&
                    list.get(i + 1).getNum() != 0) {
                list.get(i + 1).setType(Token.Type.number);
                num = list.get(i - 1).getNum() / list.get(i + 1).getNum();
            }
            else if (list.get(i + 1).getNum() == 0) {
                throw new RuntimeException("Can't divide by zero: " + list.get(i - 1).getToken()
                        + " " + list.get(i).getToken() + " " + list.get(i + 1).getToken());
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
            int x = 1, y = 1;
            if (list.get(i - 1).getStr().length() != 1)
                x = (int)(list.get(i - 1).getStr().charAt(2)) - 48;
            if (list.get(i + 1).getStr().length() != 1)
                y = (int)(list.get(i + 1).getStr().charAt(2)) - 48;

            if (c == '*') {
                num = list.get(i - 1).getNum() * list.get(i + 1).getNum();
                x = x + y;
            }
            else if (list.get(i - 1).getStr().compareTo(list.get(i + 1).getStr()) >= 0) {
                num = list.get(i - 1).getNum() / list.get(i + 1).getNum();
                x = x - y;
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

    private static void setMark(ArrayList<Token> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() == Token.Type.operator &&
                    "+-".indexOf(list.get(i).getOp()) != -1 &&
                    list.get(i + 1).getType() != Token.Type.parenthesis) {
                list.get(i + 1).setNum(list.get(i).getOp());
                list.remove(i--);
            }
        }
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
