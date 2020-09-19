package com.geekbrains.level1.lesson7;

public class Plate {
    private int food;
    public Plate(int food) {
        this.food = food;
    }
    public boolean decreaseFood(int n) {
        if (n > food) return false;
        food -= n;
        return true;
    }
    public void info() {
        System.out.println("plate: " + food);
    }

    public void addFood(int food) {
        this.food += food;
    }
}
