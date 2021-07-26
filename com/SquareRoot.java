package com;

import java.util.ArrayList;

public class SquareRoot {

    public static double square(double disc) {
        ArrayList<Long> numbers = new ArrayList<>();
        ArrayList<Long> fraction = new ArrayList<>();
        double value = 0;
        double interim = 0;

        System.out.println("disc: " + disc);
        String[] str = String.valueOf(disc).split("\\.");
        if (str[0].length() % 2 != 0)
            str[0] = "0" + str[0];
        for (int i = 0; i + 1 < str[0].length(); i += 2) {
            String tmp = str[0].substring(i, i + 2);
            numbers.add(Long.parseLong(tmp));
        }
        if (str[1].length() % 2 != 0)
            str[1] += "0";
        for (int i = 0; i + 1 < str[1].length(); i += 2) {
            String tmp = str[1].substring(i, i + 2);
            fraction.add(Long.parseLong(tmp));
        }

        for (int i = 0; i < numbers.size(); i++) {
            if (i == 0) {
                for (value = 1; (value + 1) * (value + 1) <= numbers.get(i); value++);
                interim = numbers.get(i) - (value * value);
            } else {
                interim = interim * 100 + numbers.get(i);
                double tmp = value * 20;
                double num = 9;
                for ( ; (tmp + num) * num > interim; num--);
                value = value * 10 + num;
                interim = interim - (tmp + num) * num;
            }
        }
        int i = 0;
        for ( ; i < fraction.size(); i++) {
            interim = interim * 100 + fraction.get(i);
            double tmp = value * 20;
            double num = 9;
            for ( ; (tmp + num) * num > interim; num--);
            value = value * 10 + num;
            interim = interim - (tmp + num) * num;
        }
        if (interim != 0) {
            for (int count = 0; count < 10; count++, i++) {
                interim = interim * 100;
                double tmp = value * 20;
                double num = 9;
                for ( ; (tmp + num) * num > interim; num--);
                value = value * 10 + num;
                interim = interim - (tmp + num) * num;
            }
        }
        for ( ; i > 0; i--) {
            value /= 10;
        }
        System.out.println(value);

        return value;
    }
}
