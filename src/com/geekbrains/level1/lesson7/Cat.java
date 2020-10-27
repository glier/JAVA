package com.geekbrains.level1.lesson7;

public class Cat {
    private final String name;
    private final int appetite;
    private boolean wellFed = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }
    public void eat(Plate p) {
        wellFed = p.decreaseFood(appetite);
    }

    public boolean isWellFed() {
        return wellFed;
    }

    public String getName() {
        return name;
    }
}
