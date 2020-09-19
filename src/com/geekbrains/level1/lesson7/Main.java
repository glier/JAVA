package com.geekbrains.level1.lesson7;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int CATS_COUNT = 10;
    private static final int FOOD_COUNT = 100;
    private static final int[] APPETITE_INTERVAL = {1, 50};

    public static void main(String[] args) {
        Plate plate = new Plate(FOOD_COUNT);
        List<Cat> cats = new ArrayList<>();

        for (int i = 0; i < CATS_COUNT; i++) {
            cats.add(new Cat("Кот " + i, getAppetite()));
            cats.get(i).eat(plate);
            System.out.println(cats.get(i).getName() + " сыт? - " + cats.get(i).isWellFed());
            plate.info();
        }

    }

    public static int getAppetite() {
        return APPETITE_INTERVAL[0] + (int) (Math.random() * APPETITE_INTERVAL[1]);
    }
}
