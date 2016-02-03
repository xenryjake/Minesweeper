package com.xenry.minesweeper.board;

import com.xenry.minesweeper.Minesweeper;

/**
 * Minesweeper created by Henry Jake on February 01, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public class Tile {

    public static final char BOMB_CHAR = '#', NONE_NEARBY_CHAR = ' ', BAD_FLAG_CHAR = 'X';

    private Pos pos;
    private boolean bomb, revealed, flagged;
    private char nearbyBombs;

    public Tile(int x, int y, boolean bomb){
        this.pos = new Pos(x, y);
        this.bomb = bomb;
    }

    public int getX() {
        return pos.getX();
    }

    public int getY() {
        return pos.getY();
    }

    public Pos getPos(){
        return pos;
    }

    public boolean isBomb() {
        return bomb;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setNearbyBombs(char nearbyBombs){
        this.nearbyBombs = nearbyBombs;
    }

    public char getChar(){
        if(revealed && flagged && !bomb) return BAD_FLAG_CHAR;
        if(bomb) return BOMB_CHAR;
        return nearbyBombs;
    }

    public void reveal(){
        revealed = true;
        if(bomb){
            Minesweeper.getGame().end();
            Minesweeper.getGame().getFrame().getPanel(getX(), getY()).updateDanger();
        }
    }

    public void finalReveal(){
        revealed = true;
    }

    public char getNearbyBombs() {
        return nearbyBombs;
    }

    public boolean toggleFlag(){
        if(!revealed) flagged = !flagged;
        return flagged;
    }

    public boolean isFlagged(){
        return flagged;
    }

}
