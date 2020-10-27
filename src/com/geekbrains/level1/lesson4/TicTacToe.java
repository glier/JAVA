package com.geekbrains.level1.lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    private static final int SIZE = 9;
    private static final int DOTS_TO_WIN = 5;

    private static final char[][] map = new char[SIZE][SIZE];
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        prepareGame();
        playGame();
        System.out.println("Игра закончена!");
    }

    private static void playGame() {
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X, map)) {
                System.out.println("Победил человек!");
                break;
            }

            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O, map)) {
                System.out.println("Победил Искуственный Интеллект!");
                break;
            }

            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
    }

    private static void prepareGame() {
        initMap();
        printMap();
    }

    private static boolean isMapFull() {
        for (char[] row : map) {
            for (char cell : row) {
                if (cell == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("RedundantIfStatement")
    private static boolean checkWin(char symbol, char[][] field) {
        if (checkLine(symbol, field,  0, 1)) return true;
        if (checkLine(symbol, field, 1, 0)) return true;
        if (checkDiagonal(symbol, field, 1, 1)) return true;
        if (checkDiagonal(symbol, field, -1, 1)) return true;

        return false;
    }

    private static boolean checkDiagonal(char symbol, char[][] field, int rInc, int cInc) {
        int countChar = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int iInc = i;
                int jInc = j;
                while ((iInc >= 0) && (iInc < field.length) && (jInc >= 0) && (jInc < field[i].length)) {
                    if (field[iInc][jInc] == symbol) countChar++;
                    if (field[iInc][jInc] != symbol) countChar = 0;
                    if (countChar == DOTS_TO_WIN) return true;
                    iInc += rInc;
                    jInc += cInc;
                }
                countChar = 0;
            }
        }

        return false;
    }

    private static boolean checkLine(char symbol, char[][] field, int rInc, int cInc) {
        int countChar = 0;

        for (int i = 0; i < SIZE; i++) {
            int iInc = rInc == 0 ? i : 0;
            int jInc = cInc == 0 ? i : 0;
            while ((iInc >= 0) && (iInc < field.length) && (jInc >= 0) && (jInc < field[i].length)) {
                if (field[iInc][jInc] == symbol) countChar++;
                if (field[iInc][jInc] != symbol) countChar = 0;
                if (countChar == DOTS_TO_WIN) return true;
                iInc += rInc;
                jInc += cInc;
            }
            countChar = 0;

        }

        return false;
    }

    private static void aiTurn() {
        int rowIndex, colIndex;
        do {
            rowIndex = random.nextInt(SIZE);
            colIndex = random.nextInt(SIZE);
        } while (!isCellValid(rowIndex, colIndex));

        char[][] mapTmp = map.clone();
        for(int i = 0; i < mapTmp.length; i++)
            mapTmp[i] = map[i].clone();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mapTmp[i][j] == DOT_EMPTY) {
                    mapTmp[i][j] = DOT_X;
                    if (checkWin(DOT_X, mapTmp)) {
                        rowIndex = i;
                        colIndex = j;
                    }
                    mapTmp[i][j] = DOT_EMPTY;
                }
            }
        }

        map[rowIndex][colIndex] = DOT_O;
    }

    private static void humanTurn() {
        int rowIndex = -1, colIndex = -1;
        do {
            System.out.println("Введите координаты в формате '<номер строки> <номер колонки>'");
            String[] stringData = scanner.nextLine().split(" ");
            if (stringData.length != 2) {
                System.out.println("Были введены некорректные данные!");
                continue;
            }
            try {
                rowIndex = Integer.parseInt(stringData[0]) - 1;
                colIndex = Integer.parseInt(stringData[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Были введены некорректные данные!");
            }
        } while (!isCellValid(rowIndex, colIndex));

        map[rowIndex][colIndex] = DOT_X;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean isCellValid(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex >= SIZE || colIndex < 0 || colIndex >= SIZE) {
            return false;
        }

        return map[rowIndex][colIndex] == DOT_EMPTY;
    }

    private static void printMap() {
        printColumnNumbers();
        printRows();
        System.out.println();
    }

    private static void printRows() {
        for (int i = 0; i < map.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printColumnNumbers() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void initMap() {
        for (char[] row : map) {
            Arrays.fill(row, DOT_EMPTY);
        }
    }


}
