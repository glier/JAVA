package com.geekbrains.level3.lesson5;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    private ArrayList<Stage> stages;
    private volatile boolean winner;
    private int carsFinished = 0;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public int getCarsFinished() {
        return carsFinished;
    }

    public void finish() {
        this.carsFinished++;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
