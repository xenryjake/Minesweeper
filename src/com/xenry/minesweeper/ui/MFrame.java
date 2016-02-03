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
    private MPanel[][] panels;
    private JLabel infoLabel, timeLabel;
    private JButton toggleModeButton;

    public MFrame(final Game game){
        this.game = game;
        int length = game.getDifficulty().getBoardSize();
        this.panels = new MPanel[length][length];
        setSize(0,0);
        setVisible(true);
        setMinimumSize(new Dimension(300, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper Beta");
        setLayout(new BorderLayout());

        Panel pan = new Panel(new GridLayout(length, length));
        for(Tile[] tiles : game.getBoard().getTiles()){
            for(final Tile tile : tiles){
                MPanel mp = new MPanel(tile);
                pan.add(mp);
                panels[tile.getX()][tile.getY()] = mp;
            }
        }
        add(pan, BorderLayout.CENTER);

        Panel infoPan = new Panel();
        infoPan.setLayout(new GridLayout(1, 1));
        infoLabel = new JLabel("0/0 bombs remaining");
        infoPan.add(infoLabel);
        timeLabel = new JLabel("0 seconds elapsed");
        infoPan.add(timeLabel);
        add(infoPan, BorderLayout.NORTH);

        Panel togglePan = new Panel();
        togglePan.setLayout(new GridLayout(1, 1));
        toggleModeButton = new JButton("Switch to Flag mode");
        toggleModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleMode();
            }
        });
        togglePan.add(toggleModeButton);
        add(togglePan, BorderLayout.SOUTH);

        setSize(400, 400);
        updateAll();
    }

    public void updateAll(){
        for(MPanel[] buttons : this.panels)
            for(MPanel button : buttons)
                button.update();
        updateToggleModeButton();
        updateInfo();
    }

    public void updateInfo(){
        infoLabel.setText(game.getBoard().getRemainingBombs() + "/" + game.getBoard().getTotalBombs() + " bombs remaining");
    }

    public void setInfo(String status){
        infoLabel.setText(status);
    }

    public void updateTime(int seconds){
        timeLabel.setText(seconds + " seconds elapsed");
    }

    public void updateToggleModeButton(){
        toggleModeButton.setText("Switch to " + (flagMode ? "Mine" : "Flag") + " mode");
    }

    public boolean toggleMode(){
        if(game.isActive()) flagMode = !flagMode;
        updateToggleModeButton();
        return flagMode;
    }

    public boolean isFlagMode() {
        return flagMode;
    }

}
