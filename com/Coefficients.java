package com;

import java.util.ArrayList;

public class Coefficients {

    public static ArrayList<Double> getCoefficients(ArrayList<Token> list) {
        ArrayList<Double> coeffs = new ArrayList<>();
        double a = list.get(0).getNum();
        double b = 0, c = 0, disc;

        if (list.get(1).getType() == Token.Type.x1)
            b = list.get(1).getNum();
        else if (list.get(1).getType() == Token.Type.number)
            c = list.get(1).getNum();
        if (list.get(2).getType() == Token.Type.number)
            c = list.get(2).getNum();
        disc = b * b - 4 * a * c;
        coeffs.add(disc);
        coeffs.add(a);
        coeffs.add(b);
        coeffs.add(c);

        return coeffs;
    }
}
