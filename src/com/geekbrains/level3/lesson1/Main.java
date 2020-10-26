package com.geekbrains.level3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3};
        arrSwapTowElements(arr1);
        System.out.println(Arrays.toString(arr1));

        String[] arr2 = {"A", "B", "C"};
        arrSwapTowElements(arr2);
        System.out.println(Arrays.toString(arr2));

        ArrayList<Integer> arrayList1 = arrToList(arr1);
        arrayList1.add(5);
        System.out.println(arrayList1);

        ArrayList<String> arrayList2 = arrToList(arr2);
        arrayList2.add("Z");
        System.out.println(arrayList2);

        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        Apple apple4 = new Apple();

        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Orange orange3 = new Orange();

        Box<Apple> appleBox = new Box<>(apple1, apple2, apple3, apple4, new Apple());
        appleBox.add(new Apple());
        System.out.printf("Apple box contains %d fruits%n", appleBox.fruitsCount());

        Box<Orange> orangeBox = new Box<>(orange1, orange2, orange3, new Orange());
        orangeBox.add(new Orange());
        System.out.printf("Orange box contains %d fruits%n", orangeBox.fruitsCount());

        System.out.printf("Boxes weight is %s%n", appleBox.compare(orangeBox) ? "equal" : "not equal");

        Box<Apple> appleBox2 = new Box<>();
        System.out.printf("Apple box 2 contains %d fruits%n", appleBox2.fruitsCount());
        appleBox2.pourFruit(appleBox);
        System.out.printf("Apple box 2 contains %d fruits%n", appleBox2.fruitsCount());
        System.out.printf("Apple box contains %d fruits%n", appleBox.fruitsCount());

    }

    public static <T> void arrSwapTowElements(T[] arr) {
        if (arr.length < 2) {
            System.out.println("Array of less than two elements");
        }

        T tmp = arr[0];
        arr[0] = arr[1];
        arr[1] = tmp;
    }

    public static <T>ArrayList<T> arrToList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }
}


