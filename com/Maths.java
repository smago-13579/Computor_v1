package com;

import java.util.ArrayList;

public class Maths {

    public static int multiplyDivide(ArrayList<Token> list, int start, int end) {
        for (int i = start; i < end; i++) {
            i = findMultiplyDivide(list, i, end);
            if (i == -1)
                break;
            else {
                char c = list.get(i).getOp();

                if (list.get(i - 1).getType() != Token.Type.parenthesis &&
                        list.get(i + 1).getType() != Token.Type.parenthesis) {
                    i = makeMultiplyDivide(list, c, i);
                    end -= 2;
                }
            }
        }
        end = setMark(list, start, end);
        return end;
    }

    public static int addition(ArrayList<Token> list, int open, int close, int count) {
        for (int var = open; var < close; var++) {
            Token token1 = list.get(var);
            for (int i = var + 1; i < close; i++) {
                Token token2 = list.get(i);
                if ((token1.getType() == Token.Type.number && token2.getType() == Token.Type.number) ||
                (token1.getType() == token2.getType() && token1.getStr().equals(token2.getStr()))) {
                    token1.setNum(token1.getNum() + token2.getNum());
                    list.remove(i--);
                    close--;
                }
            }
        }
        if (count == 0)
            close = addition(list, open, close, 1);
        return close;
    }

    public static Token multiplyDivide(Token token1, Token token2, char c) {
        double num;
        Token token3 = new Token(1.0);

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
            token3.setNum(num);
            token3.setType(token2.getType());
            token3.setStr(token2.getStr());

        } else if (token2.getType() == Token.Type.number) {
            if (c == '*')
                num = token1.getNum() * token2.getNum();
            else {
                if (token2.getNum() == 0) {
                    throw new RuntimeException("Can't divide by zero: " + token1.getToken()
                            + " " + c + " " + token2.getToken());
                }
                num = token1.getNum() / token2.getNum();
            }
            token3.setNum(num);
            token3.setType(token1.getType());
            token3.setStr(token1.getStr());
        } else {
            int x = 1, y = 1;
            if (token1.getStr().length() != 1)
                x = (int)(token1.getStr().charAt(2)) - 48;
            if (token2.getStr().length() != 1)
                y = (int)(token2.getStr().charAt(2)) - 48;

            if (c == '*') {
                num = token1.getNum() * token2.getNum();
                x = x + y;
            }
            else if (token1.getStr().compareTo(token2.getStr()) >= 0) {
                num = token1.getNum() / token2.getNum();
                x = x - y;
            }
            else {
                throw new RuntimeException("Invalid operation: " + token1.getToken()
                        + " " + c + " " + token2.getToken());
            }
            token3.setNum(num);
            token3.setStr(token1.getStr());
            token3.setType(x);
        }

        return token3;
    }

    private static int makeMultiplyDivide(ArrayList<Token> list, char c, int i) {
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

    private static int findMultiplyDivide(ArrayList<Token> list, int i, int end) {
        for (; i < end; i++) {
            Token token = list.get(i);
            if (token.getType() == Token.Type.operator && "*/".indexOf(token.getOp()) != -1) {
                return (i);
            }
        }
        return -1;
    }

    public static int setMark(ArrayList<Token> list, int start, int end) {
        for (int i = start; i < end; i++) {
            if (list.get(i).getType() == Token.Type.operator &&
                    "+-".indexOf(list.get(i).getOp()) != -1 &&
                    list.get(i + 1).getType() != Token.Type.parenthesis) {
                list.get(i + 1).setNum(list.get(i).getOp());
                list.remove(i--);
                end--;
            }
        }
        return end;
    }
}
