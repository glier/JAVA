package com.geekbrains.level2.lesson5;

import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        initializeArray(arr);
        System.out.println("Init time " + (System.currentTimeMillis() - startTime) + "ms");

        startTime = System.currentTimeMillis();
        calcArray(arr);
        System.out.println("Calc one thread time " + (System.currentTimeMillis() - startTime) + "ms");

        startTime = System.currentTimeMillis();
        calcArrayThread(arr);
        System.out.println("Calc two thread time " + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void initializeArray(float[] arr) {
        Arrays.fill(arr, 1);
    }

    public static void calcArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (float)i / 5) * Math.cos(0.2f + (float)i / 5) * Math.cos(0.4f + (float)i / 2));
        }
    }

    public static void calcArrayThread(float[] arr) throws InterruptedException {
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread thread1 = new Thread(() -> calcArray(a1));
        Thread thread2 = new Thread(() -> calcArray(a2));
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
    }
}
