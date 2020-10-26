package com.geekbrains.level2.lesson1;

public abstract class Sportsman {
    private final String name;
    protected int runDistance;
    protected int jumpHeight;
    protected boolean runStrength = true;
    protected boolean jumpStrength = true;

    public Sportsman(String name, int runDistance, int jumpHeight) {
        this.name = name;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
    }

    public boolean isStrength() {
        return !runStrength || !jumpStrength;
    }

    protected void printJumpFail(int height) {
        System.out.printf("%s: %dм. слишком высоко. Я могу не выше %dм.%n", name, height, jumpHeight);
    }

    protected void printJumpSuccess(int height) {
        System.out.printf("%s: Прыгнул на %dм.%n", name, height);
    }

    protected void printRunFail(int distance) {
        System.out.printf("%s: %dм. слишком много. Я могу не больше %dм.%n", name, distance, runDistance);
    }

    protected void printRunSuccess(int distance) {
        System.out.printf("%s: Пробежал на %dм.%n", name, distance);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Sportsman{" +
                "name='" + name + '\'' +
                ", runDistance=" + runDistance +
                ", jumpHeight=" + jumpHeight +
                ", runStrength=" + runStrength +
                ", jumpStrength=" + jumpStrength +
                '}';
    }
}
