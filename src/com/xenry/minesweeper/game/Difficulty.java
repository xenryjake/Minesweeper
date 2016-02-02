package com.xenry.minesweeper.game;

/**
 * Minesweeper created by Henry Jake on February 01, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public enum Difficulty {

    TEST(15, 9);

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
