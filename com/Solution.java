package com;

import java.util.ArrayList;

public class Solution {

    public static ArrayList<Token> reduceForm(ArrayList<Token> list) {
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
        return list;
    }

    private static int makeOp(ArrayList<Token> list, char c, int i) {
        double num;

        if (list.get(i - 1).getType() == Token.Type.number) {
            if (c == '*')
                num = list.get(i - 1).getNum() * list.get(i + 1).getNum();
            else
                num = list.get(i - 1).getNum() / list.get(i + 1).getNum();
            list.get(i + 1).setNum(num);
            list.remove(i - 1);
            list.remove(i - 1);
        } else if (list.get(i + 1).getType() == Token.Type.number) {
            if (c == '*')
                num = list.get(i - 1).getNum() * list.get(i + 1).getNum();
            else
                num = list.get(i - 1).getNum() / list.get(i + 1).getNum();
            list.get(i - 1).setNum(num);
            list.remove(i);
            list.remove(i);
            i--;
        }

        return i;
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
}
