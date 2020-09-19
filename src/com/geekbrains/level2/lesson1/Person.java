package com.geekbrains.level2.lesson1;

public class Person extends Sportsman implements Running, Leaping {

    public Person(String name, int runDistance, int jumpHeight) {
        super(name, runDistance, jumpHeight);
    }

    @Override
    public void jump(int height) {
        if (height > jumpHeight) {
            printJumpFail(height);
            jumpStrength = false;
        } else {
            printJumpSuccess(height);
            jumpHeight -= height;
        }

    }

    @Override
    public void run(int distance) {
        if (distance > runDistance) {
            printRunFail(distance);
            runStrength = false;
        } else {
            printRunSuccess(distance);
            runDistance -= distance;
        }
    }
}
