package com.geekbrains.level3.lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore sp;

    public Tunnel(Semaphore sp) {
        this.sp = sp;
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                sp.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                sp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
