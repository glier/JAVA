package com.geekbrains.level1.lesson1;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        byte b = 127;
        short sh = 32767;
        int in = 2147483647;
        long lo = 9223372036854775807L;
        float fl = 12.23f;
        double du = 23.123;
        char ch = '*';
        boolean bl = true;
        String s = "String";
        Integer i = 5;
        Long l = 5L;
        Float f = 55.5f;
        Double d = 6.33;

        System.out.println(calc(11.11f, 22.22f, 33.33f, 44.44f));
        System.out.println(isValid(5, 10));
        System.out.println(isValid(5, 1));
        positiveOrNegativeNumber(-10);
        System.out.println(isPositiveOrNegativeNumber(5));
        helloName("Stiven");
        leapYear();
    }

    public static float calc(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    public static Boolean isValid(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    public static void positiveOrNegativeNumber(int a) {
        String positive = "Positive number";
        String negative = "Negative number";

        System.out.println(a >= 0 ? positive : negative);
    }

    public static Boolean isPositiveOrNegativeNumber(int a) {
        return !(a >= 0);
    }

    public static void helloName(String name) {
        System.out.printf("Hello, %s!%n", name);
    }

    public static void leapYear() {
        String leapYear = "This year is leap";
        String notLeapYear = "This year is not leap";

        LocalDate date = LocalDate.now();
        int year = date.getYear();

        if ((year % 4 != 0) || (year % 100 == 0) && (year % 400 != 0)) {
            System.out.println(notLeapYear);
        } else {
            System.out.println(leapYear);
        }
    }
}
