package com.geekbrains.lesson6;

public class Main {
    public static void main(String[] args) {
        Animal cat1 = new Cat("Кот 1", 3, 1, 200);
        Animal cat2 = new Cat("Кот 2", 2, 0, 300);

        Animal dog1 = new Dog("Собака 1", 1, 10, 500);
        Animal dog2 = new Dog("Собака 2", 1, 15, 400);

        Animal drago = new Dragon("Дракон", 0,0,0);

        dog1.run(100);
        dog1.run(100);
        dog1.run(100);
        dog1.jump(2);
        dog1.jump(3);
        dog1.jump(4);
        dog1.swim(1);
        dog1.swim(2);
        dog2.swim(2);

        cat1.run(100);
        cat1.run(100);
        cat1.run(100);
        cat1.jump(2);
        cat1.jump(3);
        cat1.jump(4);
        cat1.swim(1);
        cat1.swim(2);
        cat2.swim(2);

        drago.run(10000);
    }
}
