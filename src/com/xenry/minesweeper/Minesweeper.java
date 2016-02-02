package com.xenry.minesweeper;

import com.xenry.minesweeper.game.Difficulty;
import com.xenry.minesweeper.game.Game;

/**
 * Minesweeper created by Henry Jake on February 01, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public class Minesweeper {

    private static Game game;

    public static void main(String[] args){
        game = new Game(Difficulty.TEST);
        game.enable();
    }

    public static Game getGame(){
        return game;
    }

}
