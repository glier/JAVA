package com.geekbrains.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame {

    private JButton[][] jbs;

    public Form(int rows, int cols) {
        setSize(60 * rows, 60 * cols);
        setLocationRelativeTo(null);
        setTitle("TicTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jbs = new JButton[rows][cols];
        setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < jbs.length; i++) {
            for (int j = 0; j < jbs[i].length; j++) {
                jbs[i][j] = new JButton();
                add(jbs[i][j]);
            }
        }
        setJbsListeners();
        setVisible(true);
    }

    public void setCellLabel(String symbol, int rowInd, int colInd) {
        jbs[rowInd][colInd].setText(symbol);
    }

    public void setJbsListeners() {
        for (int i = 0; i < jbs.length; i++) {
            for (int j = 0; j < jbs[i].length; j++) {
                int finalI = i;
                int finalJ = j;
                jbs[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        TicTacToe.setHumanTurnedCell(finalI, finalJ);
                        TicTacToe.setHumanTurned(true);
                    }
                });
            }
        }
    }

}
