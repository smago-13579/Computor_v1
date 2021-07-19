package com;

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
            str += "^1";
        } else if (str.charAt(2) == '0') {
            this.type = Type.x0;
        } else if (str.charAt(2) == '1') {
            this.type = Type.x1;
        } else {
            this.type = Type.x2;
        }
        this.num = 1;
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

    public void setNum(double num) {
        this.num = num;

        if (this.num == 0) {
            this.type = Type.number;
            this.str = "";
        }
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
        else if (x == 1)
            this.type = Type.x1;
        else if (x >= 2)
            this.type = Type.x2;
        this.str = this.str.substring(0, 2) + x;
    }

    public String getToken() {
        if (type == Type.operator || type == Type.parenthesis)
            return Character.toString(op);
        if (type == Type.number)
            return Double.toString(num);
        if (this.num != 1) {
            String string = Double.toString(num) + " * " + this.str;
            return string;
        }
        return this.str;
    }
}
