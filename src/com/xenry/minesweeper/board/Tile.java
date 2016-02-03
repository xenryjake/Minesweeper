package com.xenry.minesweeper.board;

import com.xenry.minesweeper.Minesweeper;

/**
 * Minesweeper created by Henry Jake on February 01, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public class Tile {

    public static final char FLAG_CHAR = 'X', REVEALED_CHAR = ' ', BOMB_CHAR = '*', NONE_NEARBY_CHAR = '-';

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
        if(flagged) return FLAG_CHAR;
        if(!revealed) return REVEALED_CHAR;
        if(bomb) return BOMB_CHAR;
        return nearbyBombs;
    }

    public void reveal(){
        revealed = true;
        if(bomb){
            Minesweeper.getGame().end();
            return;
        }
        finalReveal();
    }

    public void finalReveal(){
        revealed = true;
    }

    public char getNearbyBombs() {
        return nearbyBombs;
    }

    public boolean toggleFlag(){
        flagged = !flagged;
        return flagged;
    }

    public boolean isFlagged(){
        return flagged;
    }

}
