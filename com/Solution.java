package com;

import java.util.ArrayList;

public class Solution {

    public static ArrayList<Token> reduceForm(ArrayList<Token> list) {
        int equal;

        for (int i = 0; i < list.size(); i++) {
            Token token = list.get(i);
            if (token.getType() == Token.Type.x0) {
                token.setType(0);
            }
        }
        openParenthesis(list);
        Maths.multiplyDivide(list, 0, list.size());

        for (equal = 0; list.get(equal).getOp() != '='; equal++);
        Maths.addition(list, 0, equal, 0);
        Maths.addition(list, equal + 1, list.size(), 0);
        removeEmptyToken(list);
        System.out.print("Reduced form: ");
        Token.printTokens(list);
        transferAndAlign(list);

        System.out.print("Step 0: ");
        Token.printTokens(list);
        Maths.addition(list, 0, list.size() - 1, 0);
        removeEmptyToken(list);
        System.out.print("Step 1: ");
        Token.printTokens(list);

        Lexer.checkPower(list);

        return list;
    }

    private static void transferAndAlign(ArrayList<Token> list) {
        int equal = 0;

        for (; list.get(equal).getOp() != '='; equal++);

        for (int i = list.size() - 1; i > equal; i--) {
            Token token = list.get(i);
            if (token.getNum() != 0) {
                token.setNum('-');
                list.remove(i++);
                list.add(equal++, token);
            }
        }
        if (equal + 1 == list.size())
            list.add(new Token(0.0));
    }

    private static void removeEmptyToken(ArrayList<Token> list) {
        int equal = 0;

        for (; list.get(equal).getOp() != '='; equal++);

        for (int i = 0; i < list.size(); i++) {
            if (i == equal)
                continue;
            if (list.get(i).getType() == Token.Type.number &&
                    list.get(i).getNum() == 0 &&
                    ((i < equal  && equal > 1) || equal + 2 < list.size())) {
                list.remove(i--);
            }
        }
    }

    private static void openParenthesis(ArrayList<Token> list) {
        int open = 0;
        int close = 0;

        for (int i = 0; i < list.size(); i++) {
            Token token = list.get(i);
            if (token.getType() == Token.Type.parenthesis && token.getOp() == '(') {
                open = i;
            }
            if (token.getType() == Token.Type.parenthesis && token.getOp() == ')') {
                close = i;
                close = Maths.multiplyDivide(list, open, close);
                close = Maths.addition(list, open, close, 0);
                if (removeParentheses(list, open, close))
                    i = -1;
            }
        }
    }

    private static boolean removeParentheses(ArrayList<Token> list, int open, int close) {
        if (close - open == 2) {
            list.remove(close);
            list.remove(open);
        }
        else if (open != 0 && list.get(open - 1).getOp() == '*') {
            char c = '*';
            if (list.get(open - 2).getOp() != ')') {
                Token token1 = list.get(open - 2);
                for (int i = open + 1; i < close; i++) {
                    Token token2 = list.get(i);
                    token2 = Maths.multiplyDivide(token1, token2, c);
                    list.set(i, token2);
                }
                list.remove(open - 2);
                list.remove(open - 2);
            } else {
                ArrayList<Token> tmp = new ArrayList<>();
                int close2 = open - 2;
                int open2 = open - 2;

                while (list.get(open2).getOp() != '(')
                    open2--;
                for (int i = open2 + 1; i < close2; i++) {
                    Token token1 = list.get(i);
                    Token token3;
                    for (int len = open + 1; len < close; len++) {
                        Token token2 = list.get(len);
                        token3 = Maths.multiplyDivide(token1, token2, c);
                        tmp.add(token3);
                    }
                }
                for (; close >= open - 1; close--)
                    list.remove(close);
                while (--close2 > open2)
                    list.remove(close2);
                list.addAll(open2 + 1, tmp);
            }
        } else if (open != 0 && list.get(open - 1).getOp() == '/') {
            String except = "I can't solve this: \"/ ";
            for (int i = open; i <= close; i++) {
                except += list.get(i).getToken() + " ";
            }
            except += "\"";
            throw new RuntimeException(except);
        } else if (close + 1 < list.size() && "*/".indexOf(list.get(close + 1).getOp()) != -1) {
            if (list.get(close + 2).getOp() != '(') {
                char c = list.get(close + 1).getOp();
                Token token2 = list.get(close + 2);
                for (int i = open + 1; i < close; i++) {
                    Token token1 = list.get(i);
                    token1 = Maths.multiplyDivide(token1, token2, c);
                    list.set(i, token1);
                }
                list.remove(close + 1);
                list.remove(close + 1);
            } else
                return false;
        } else if (open != 0 && list.get(open - 1).getOp() == '-') {
            Token token1 = new Token(-1.0);
            for (int i = open + 1; i < close; i++) {
                Token token2 = list.get(i);
                token2 = Maths.multiplyDivide(token1, token2, '*');
                list.set(i, token2);
            }
            list.remove(open - 1);
        } else {
            list.remove(close);
            list.remove(open);
        }
        return true;
    }
}
