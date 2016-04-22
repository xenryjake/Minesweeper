package com.xenry.minesweeper.game;

/**
 * Minesweeper created by Henry Jake on February 01, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public enum Difficulty {

    BEGINNER(7, 11),
    EASY(11, 9),
    MEDIUM(15, 8),
    HARD(21, 6);

    private int boardSize, chance;

    Difficulty(int boardSize, int chance){
        this.boardSize = boardSize;
        this.chance = chance;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getChance() {
        return chance;
    }

}
