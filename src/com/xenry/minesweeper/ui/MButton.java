package com.xenry.minesweeper.ui;

import com.xenry.minesweeper.Minesweeper;
import com.xenry.minesweeper.board.Tile;
import com.xenry.minesweeper.game.Game;

import javax.swing.*;
import java.awt.event.*;

/**
 * Minesweeper created by Henry Jake on February 02, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public class MButton extends JButton implements ActionListener {

    private Tile tile;

    public MButton(Tile tile){
        this.tile = tile;
        addActionListener(this);
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
        String s = String.valueOf(tile.getChar());
        setText(s);
        setVisible(!s.equals(Tile.NONE_NEARBY_CHAR));
    }

}
