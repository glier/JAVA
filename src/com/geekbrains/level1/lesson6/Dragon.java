package com.geekbrains.level1.lesson6;

public class Dragon extends Animal {
    public Dragon(String name, int jumpMax, int swimMax, int runMax) {
        super(name, jumpMax, swimMax, runMax);
    }

    @Override
    public void jump(int height) {
        say("Я прыгнул на " + height + "м." );
    }

    @Override
    public void swim(int distance) {
        say("Я проплыл " + distance + "м." );
    }

    @Override
    public void run(int distance) {
        say("Я пробежал " + distance + "м." );
    }

    @Override
    public void say(String message) {
        super.say(message + " У меня нет ограничений - Я дракон!");
    }
}
