package com.xenry.minesweeper.ui;

import com.xenry.minesweeper.board.Tile;
import com.xenry.minesweeper.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Minesweeper created by Henry Jake on February 02, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public class MFrame extends JFrame {

    private boolean flagMode = false;

    private Game game;
    private MButton[][] buttons;
    private JLabel infoLabel;

    public MFrame(final Game game){
        this.game = game;
        int length = game.getDifficulty().getBoardSize();
        this.buttons = new MButton[length][length];
        setSize(0,0);
        setVisible(true);
        setMinimumSize(new Dimension(300, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper Beta");
        setLayout(new BorderLayout());
        Panel pan = new Panel(new GridLayout(length, length));
        for(Tile[] tiles : game.getBoard().getTiles()){
            for(final Tile tile : tiles){
                MButton button = new MButton(tile);
                pan.add(button);
                buttons[tile.getX()][tile.getY()] = button;
            }
        }
        add(pan, BorderLayout.CENTER);
        infoLabel = new JLabel("loading...");
        add(infoLabel, BorderLayout.NORTH);
        {
            final JButton button = new JButton("Switch to Flag mode");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    flagMode = !flagMode;
                    button.setText("Switch to " + (flagMode ? "Mine" : "Flag") + " mode");
                }
            });
            add(button, BorderLayout.SOUTH);
        }
        setSize(400,400);
    }

    public Game getGame() {
        return game;
    }

    public MButton getButton(int x, int y){
        return buttons[x][y];
    }

    public void updateAll(){
        for(MButton[] buttons : this.buttons)
            for(MButton button : buttons)
                button.update();
    }

    public boolean isFlagMode() {
        return flagMode;
    }

}
