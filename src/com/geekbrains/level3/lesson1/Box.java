package com.geekbrains.level3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {
    ArrayList<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public Box(T... fruits) {
        this.fruits = new ArrayList<>(Arrays.asList(fruits));
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public void removeAll() {
        fruits.removeAll(fruits);
    }

    public ArrayList<T> getAll() {
        return fruits;
    }

    public boolean compare(Box box) {
        return this.getBoxWeight() == box.getBoxWeight();
    }

    private float getBoxWeight() {
        float sum = 0;

        for (Fruit fruit: fruits) {
            sum += fruit.getWeight();
        }

        return sum;
    }

    public void pourFruit(Box<T> box) {
        fruits.addAll(box.getAll());
        box.removeAll();
    }

    public int fruitsCount() {
        return fruits.size();
    }
}
