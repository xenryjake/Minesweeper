package com.xenry.minesweeper.ui;

import com.xenry.minesweeper.Minesweeper;
import com.xenry.minesweeper.board.Tile;
import com.xenry.minesweeper.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Minesweeper created by Henry Jake on February 02, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public class MPanel extends JPanel implements ActionListener {

    private Tile tile;
    private JButton button;
    private JLabel label;

    public MPanel(Tile tile){
        this.tile = tile;
        setLayout(new GridLayout(1, 1));
        button = new JButton();
        button.addActionListener(this);
        add(button);
        label = new JLabel("", SwingConstants.CENTER);
        update();
    }

    public void actionPerformed(ActionEvent e){
        Game game = Minesweeper.getGame();
        if(!game.isActive()) return;
        if(game.getFrame().isFlagMode()){
            tile.toggleFlag();
        }else if(!tile.isFlagged()){
            game.getBoard().flood(tile.getPos());
        }
        game.getFrame().updateAll();
    }

    public void update(){
        if(!tile.isRevealed() || tile.isBomb()){
            if(tile.isFlagged())
                button.setText(String.valueOf(Tile.FLAG_CHAR));
            else if(tile.isBomb() && tile.isRevealed())
                button.setText(String.valueOf(Tile.BOMB_CHAR));
            else button.setText("");
            button.setVisible(true);
            remove(label);
            add(button);
            return;
        }
        label.setText(String.valueOf(tile.getChar()));
        button.setVisible(false);
        remove(button);
        add(label);
    }

}
