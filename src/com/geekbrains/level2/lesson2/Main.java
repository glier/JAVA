package com.geekbrains.level2.lesson2;

public class Main {

    public static String[][] arrSizeExc = new String[4][5];
    public static String[][] arrDataExc = {
            {"1", "2","3","4"},
            {"1", "2","3","4"},
            {"1", "2","D","4"},
            {"1", "2","3","4"}
    };

    public static String[][] arrDataRig = {
            {"1", "2","3","4"},
            {"1", "2","3","4"},
            {"1", "2","3","4"},
            {"1", "2","3","4"}
    };


    public static void main(String[] args) {
        example(arrSizeExc);
        example(arrDataExc);
        example(arrDataRig);
    }

    public static void example(String[][] arr) {
        int sum = 0;

        try {
            if (arr.length != 4 || arr[0].length != 4)
                throw new MyArraySizeException(
                        String.format("i[%d] != 4 OR j[%d] != 4", arr.length, arr[0].length)
                );

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    try {
                        sum += Integer.parseInt(arr[i][j]);
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException(
                                String.format("%s in array position i = %d j = %d",
                                        e.getMessage(), i, j)
                        );
                    }
                }
            }

            System.out.println("Sum in array: " + sum);
        }
        catch (MyArraySizeException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        catch (MyArrayDataException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
