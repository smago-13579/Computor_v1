package com;

import java.util.ArrayList;

public class Parse {

    private static final String validSymbols = "() +-*/^=1234567890.";
    private ArrayList<Token> list = new ArrayList<>();
    private String str;

    public Parse(String str) throws Exception {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!isValid(c)) {
                throw new Exception("Invalid symbol: " + c);
            }
        }
        this.str = str;
        createTokens();

    }

    private void    createTokens() throws Exception {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                i = parseNum(i);
            }
            else if ("+-*/=()".indexOf(c) != -1) {
                list.add(new Token(c));
            }
            else if (c != ' ' && c != '.') {
                i = parseX(i);
            }
            else if (c == '.')
                throw new Exception("Invalid equation - \".\"");
        }
    }

    private int     parseX(int i) throws Exception {
        String token = "";
        int len = i;

        if (str.charAt(len) == '^')
            throw new Exception("Invalid equation - \"^\"");
        token += str.charAt(len++);

        while (len != str.length() && str.charAt(len) == ' ')
            len++;
        if (len == str.length() || str.charAt(len) != '^') {
            list.add(new Token(token));
        } else if (len != str.length() && str.charAt(len) == '^') {
            token += str.charAt(len++);

            while (len != str.length() && str.charAt(len) == ' ')
                len++;

            while (len != str.length() && "1234567890".indexOf(str.charAt(len)) != -1)
                token += str.charAt(len++);
            if (token.length() != 3)
                throw new Exception("Invalid equation degree");
            list.add(new Token(token));
        }
        return (len - 1);
    }

    private int     parseNum(int i) {
        double d;
        int len = i;
        char c = str.charAt(len);
        while ((c >= '0' && c <= '9') || c == '.') {
            len++;
            if (str.length() == len) {
                break;
            }
            c = str.charAt(len);
        }
        d = Double.parseDouble(str.substring(i, len));
        list.add(new Token(d));
        return (len - 1);
    }

    private boolean isValid(char c) {
        if (validSymbols.indexOf(c) == -1 && (c < 65 || c > 90) ) {
            return false;
        }
        return true;
    }

    public ArrayList<Token> getList() {
        return list;
    }
}
