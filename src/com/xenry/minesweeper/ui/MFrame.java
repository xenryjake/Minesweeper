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
    private JLabel infoLabel, timeLabel, statusLabel;
    private JButton toggleModeButton;
    private JPanel infoPan;

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

        infoPan = new JPanel();
        infoPan.setLayout(new GridLayout(1, 1));
        timeLabel = new JLabel("0 seconds elapsed");
        infoPan.add(timeLabel);
        infoLabel = new JLabel("0/0 bombs remaining");
        infoPan.add(infoLabel);
        statusLabel = new JLabel("");
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

    public void setStatus(String status){
        statusLabel.setText(status);
        if(status.equals("")) {
            infoPan.remove(statusLabel);
            infoPan.add(infoLabel);
        }else{
            infoPan.remove(infoLabel);
            infoPan.add(statusLabel);
        }
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

    public MPanel getPanel(int x, int y){
        return panels[x][y];
    }

}
