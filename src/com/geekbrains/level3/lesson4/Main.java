package com.geekbrains.level3.lesson4;

public class Main {
    static volatile char chr = 'A';
    static int COUNT = 5;

    public static void main(String[] args) {
        Object mon = new Object();

        class Task implements Runnable {

            private final char currentLetter;
            private final char nextLetter;

            public Task(char currentLetter, char nextLetter) {
                this.currentLetter = currentLetter;
                this.nextLetter = nextLetter;
            }

            @Override
            public void run() {
                for (int i = 0; i < COUNT; i++) {
                    synchronized (mon) {
                        try {
                            while (chr != currentLetter) {
                                mon.wait();
                            }
                            System.out.print(currentLetter);
                            chr = nextLetter;
                            mon.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        new Thread(new Task('A', 'B')).start();
        new Thread(new Task('B', 'C')).start();
        new Thread(new Task('C', 'A')).start();
    }
}
