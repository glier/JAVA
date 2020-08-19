package com.geekbrains.lesson2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        task1();
        printTaskSeparator();
        task2();
        printTaskSeparator();
        task3();
        printTaskSeparator();
        task4();
        printTaskSeparator();
        task5();
        printTaskSeparator();
        task6();
        printTaskSeparator();
        task7();
    }

    public static void task1() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        printArray(arr);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 0 ? 1 : 0;
        }

        printArray(arr);
    }

    public static void task2() {
        int[] arr = new int[8];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
        }

        printArray(arr);
    }

    public static void task3() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        printArray(arr);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) arr[i] = arr[i] * 2;
        }
        printArray(arr);
    }

    public static void task4() {
        int arrSize = 3;
        int[][] arr = new int[arrSize][arrSize];

        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = 1;
            arr[i][arr.length - 1 - i] = 1;
        }

        printTwoDimensionalArray(arr);
    }

    public static void task5() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        Arrays.sort(arr);

        System.out.println("Min: " + arr[0]);
        System.out.println("Max: " + arr[arr.length-1]);
    }

    public static void task6() {
        int[] arr1 = {2, 2, 2, 1, 2, 2, 10, 1};
        int[] arr2 = {2, 2, 2, 1, 2, 2, 10000, 1};

        System.out.println(isHalvesArrayEqual(arr1));
        System.out.println(isHalvesArrayEqual(arr2));
    }

    public static void task7() {
        int[] arr = {1, 5, 3, 2, 6};
        printArray(arrayOffset(arr, -1));
    }

    public static int[] arrayOffset(int[] arr, int offset) {
        if (offset == 0) return arr;

        for (int j = 0; j < Math.abs(offset); j++) {
            if (offset < 0) {
                int tmp = arr[0];

                for (int i = 0; i < arr.length - 1; i++) {
                    arr[i] = arr[i + 1];
                }

                arr[arr.length - 1] = tmp;
            }
            else {
                int tmp = arr[arr.length - 1];

                for (int i = arr.length - 1; i > 0; i--) {
                    arr[i] = arr[i - 1];
                }

                arr[0] = tmp;
            }
        }

        return arr;
    }

    public static Boolean isHalvesArrayEqual(int[] arr) {
        int leftSum = 0;
        int rightSum = 0;

        for (int i = 0; i < arr.length; i++) {
            leftSum += arr[i];

            for (int j = i + 1; j < arr.length; j++) {
                rightSum += arr[j];
            }

            if (leftSum == rightSum) return true;
            rightSum = 0;
        }

        return false;
    }

    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void printTwoDimensionalArray(int[][] arr) {
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void printTaskSeparator() {
        System.out.println("----------------------");
    }
}
