package com.geekbrains.lesson6;

import java.util.Objects;

public abstract class Animal {

    private final String name;
    private int jumpMax;
    private int swimMax;
    private int runMax;

    public Animal(String name, int jumpMax, int swimMax, int runMax) {
        this.name = name;
        this.jumpMax = jumpMax;
        this.swimMax = swimMax;
        this.runMax = runMax;
    }

    public void jump(int height) {
        if (height > jumpMax) {
            say("Слишком высоко");
        } else {
            say("Прыгнул на " + height + "м.");
        }
    }
    public void swim(int distance) {
        if (swimMax == 0) {
            say("Я не умею плавать");
        } else if (distance > swimMax) {
            say("Слишком далеко, я утону");
        } else {
            say("Проплыл на " + distance + "м.");
        }
    }
    public void run(int distance) {
        if (distance > runMax) {
            say("Я уже набегался");
        } else {
            say("Пробежал " + distance + "м.");
            runMax -= distance;
        }
    }

    public void say(String message) {
        System.out.println(this.getClass().getSimpleName() + " - " + name + ": " + message);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", jumpMax=" + jumpMax +
                ", swimMax=" + swimMax +
                ", runMax=" + runMax +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return jumpMax == animal.jumpMax &&
                swimMax == animal.swimMax &&
                runMax == animal.runMax &&
                Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, jumpMax, swimMax, runMax);
    }
}
