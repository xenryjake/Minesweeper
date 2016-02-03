package com.xenry.minesweeper.game;

import com.xenry.minesweeper.U;
import com.xenry.minesweeper.board.Board;
import com.xenry.minesweeper.board.Tile;
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
    private Timer timer;
    private Difficulty difficulty;

    public Game(Difficulty difficulty){
        this.difficulty = difficulty;
        this.active = true;
        this.timer = new Timer();
        board = new Board(difficulty.getBoardSize(), difficulty.getChance());
    }

    public void enable(){
        U.p("Starting Minesweeper...");
        this.frame = new MFrame(this);
        timer.start();
        timer.setActive(true);
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
        stop();
    }

    public void stop(){
        timer.setActive(false);
        active = false;
        board.revealAll();
        frame.updateAll();
        U.p("TIMER: " + timer.getCurrentSecs());
    }

    public boolean checkWin(){
        if(!active) return false;
        boolean won = true;
        for(Tile[] tiles : board.getTiles())
            for(Tile tile : tiles)
                if(!tile.isBomb() && !tile.isRevealed()) won = false;
        if(!won) return false;
        U.p("You win!");
        stop();
        return true;
    }

    public MFrame getFrame() {
        return frame;
    }

    public Timer getTimer() {
        return timer;
    }

}
