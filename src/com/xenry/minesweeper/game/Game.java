package com.xenry.minesweeper.game;

import com.xenry.minesweeper.U;
import com.xenry.minesweeper.board.Board;
import com.xenry.minesweeper.ui.MFrame;

/**
 * Minesweeper created by Henry Jake on February 01, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public class Game {

    private boolean active;
    private Board board;
    private MFrame frame;
    private Difficulty difficulty;

    public Game(Difficulty difficulty){
        this.difficulty = difficulty;
        this.active = true;
        board = new Board(difficulty.getBoardSize(), difficulty.getChance());
    }

    public void enable(){
        U.p("Starting Minesweeper...");
        this.frame = new MFrame(this);
    }

    public Board getBoard() {
        return board;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean isActive() {
        return active;
    }

    public void end(){
        if(!active) return;
        U.p("Game over!");
        active = false;
        board.revealAll();
        frame.updateAll();
    }

    public MFrame getFrame() {
        return frame;
    }

}
