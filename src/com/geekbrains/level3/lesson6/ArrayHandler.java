package com.geekbrains.level3.lesson6;

import java.util.Arrays;

public class ArrayHandler {
    public int[] subArray(int[] arr) {
        if (arr == null || arr.length < 1 || !arrIsExistFour(arr)) {
            throw new RuntimeException("Пустой массив или не содержит 4");
        }

        int startIndex = 0;
        int[] resArr;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) startIndex = i;
        }

        resArr = Arrays.copyOfRange(arr, startIndex + 1, arr.length);
        return resArr;
    }

    public boolean arrIsExistsOneAndFourOnly(int[] arr) {
        boolean one = false;
        boolean four = false;
        boolean other = false;

        for (int j : arr) {
            if (j == 1) {
                one = true;
            } else if (j == 4) {
                four = true;
            } else {
                other = true;
            }
        }

        return one && four && !other;
    }

    private boolean arrIsExistFour(int[] arr) {
        for (int j : arr) {
            if (j == 4) {
                return true;
            }
        }
        return false;
    }
}