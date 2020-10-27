package com.geekbrains.level1.lesson3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        guessNumber();
    }

    public static void guessNumber() {
        int a = 0;
        int b = 9;
        int hiddenNumber = getHiddenNumber(a, b);
        int numberGuesses = 3;
        int cycleNumber = 0;
        boolean gameProcess = true;

        while (gameProcess) {
            cycleNumber += 1;

            if (cycleNumber == 1)  {
                hiddenNumber = getHiddenNumber(a, b);
                printStartGameGuessNumber(a, b, numberGuesses);
            }

            System.out.print("Введи число: ");
            int inNumber = getIntFromConsole();

            if (inNumber == hiddenNumber) {
                guessedNumber();
                gameProcess = isPlayAgain();
                cycleNumber = 0;
            } else if (inNumber > hiddenNumber) {
                hiddenNumberLt();
            } else {
                hiddenNumberGt();
            }

            if (cycleNumber == numberGuesses) {
                printYouLose();
                gameProcess = isPlayAgain();
                cycleNumber = 0;
            }
        }
    }

    public static Boolean isPlayAgain() {
        System.out.print("Повторить игру еще раз? 1 – да / 0 – нет: ");
        return getIntFromConsole() == 1;
    }

    private static void hiddenNumberLt() {
        System.out.println("Загаданное число меньше.");
    }

    private static void hiddenNumberGt() {
        System.out.println("Загаданное число больше.");
    }

    public static void printStartGameGuessNumber(int beginNumber, int endNumber, int numberGuesses) {
        System.out.printf("Программа загадала число от %s до %s%n", beginNumber, endNumber);
        System.out.printf("Количество попыток угадывания: %s%n", numberGuesses);
    }

    public static void printYouLose() {
        System.out.println("Число не угадано!");
    }

    public static void guessedNumber() {
        System.out.println("Число угадано верно!");
    }

    public static int getIntFromConsole() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public static int getHiddenNumber(int a, int b) {
        return a + (int) (Math.random() * b);
    }
}
