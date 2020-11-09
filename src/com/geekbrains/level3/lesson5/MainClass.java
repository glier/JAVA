package com.geekbrains.level3.lesson5;


import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final int CARS_IN_TUNNEL = 2;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        ExecutorService executor = Executors.newFixedThreadPool(CARS_COUNT);
        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT, new StartRace());
        Semaphore sp = new Semaphore(CARS_IN_TUNNEL);

        Race race = new Race(new Road(60), new Tunnel(sp), new Road(40));

        for (int i = 0; i < CARS_COUNT; i++) {
            executor.submit(new Car(race, 20 + (int) (Math.random() * 10), cb));
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

