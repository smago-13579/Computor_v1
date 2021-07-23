package com;

import java.util.ArrayList;

public class Token {

    enum Type {
        operator,
        number,
        x0,
        x1,
        x2,
        parenthesis
    }

    private Type type;
    private double num;
    private char op;
    private String str;

    public Token(double d) {
        this.num = d;
        this.type = Type.number;
    }

    public Token(char c) {
        this.op = c;
        if ("+-*/=".indexOf(c) != -1)
            this.type = Type.operator;
        else
            this.type = Type.parenthesis;
    }

    public Token(String str) {
        if (str.length() == 1) {
            this.type = Type.x1;
        } else if (str.charAt(2) == '0') {
            this.type = Type.x0;
        } else if (str.charAt(2) == '1') {
            this.type = Type.x1;
        } else {
            this.type = Type.x2;
        }
        this.num = 1;
        if (this.type == Type.x1)
            this.str = str.substring(0, 1);
        else
            this.str = str;
    }

    public Type getType() {
        return type;
    }

    public char getOp() {
        return op;
    }

    public double getNum() {
        return num;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setNum(double num) {
        this.num = num;

        if (this.num == 0) {
            this.type = Type.number;
            this.str = "";
        }
    }

    public void setNum(char c) {
        if (c == '-')
            this.num = -this.num;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setType(int x) {
        if (x == 0) {
            this.type = Type.number;
            this.str = "";
            return;
        }
        else if (x == 1) {
            this.type = Type.x1;
            this.str = this.str.substring(0, 1);
            return;
        }
        this.type = Type.x2;
        this.str = this.str.substring(0, 1) + "^" + x;
    }

    public String getToken() {
        if (type == Type.operator || type == Type.parenthesis)
            return Character.toString(op);
        if (type == Type.number)
            return Double.toString(num);
        if (this.num == -1){
            String string = "- " + this.str;
            return string;
        }
        if (this.num != 1) {
            String string = Double.toString(num) + " * " + this.str;
            return string;
        }
        return this.str;
    }

    public static void printTokens(ArrayList<Token> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i != 0 && list.get(i - 1).getType() != Type.operator &&
                    list.get(i).getNum() > 0) {
                System.out.print("+ " + list.get(i).getToken() + " ");
            }
            else {
                System.out.print(list.get(i).getToken() + " ");
            }
        }
        System.out.println();
    }
}
