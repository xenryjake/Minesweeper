package com.xenry.minesweeper.game;

import com.xenry.minesweeper.U;
import com.xenry.minesweeper.board.Board;
import com.xenry.minesweeper.board.Pos;
import com.xenry.minesweeper.board.Tile;

/**
 * Minesweeper created by Henry Jake on February 01, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public class Game {

    private boolean active;
    private Board board;
    private Difficulty difficulty;

    public Game(Difficulty difficulty){
        this.difficulty = difficulty;
        this.active = true;
        board = new Board(difficulty.getBoardSize(), difficulty.getChance());
    }

    public void enable(){
        U.p("Starting Minesweeper...");
        doInput();
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
        U.p("Game over!");
        active = false;
        board.revealAll();
    }

    public void doInput(){
        {
            boolean a = true;
            while(a){
                showBoard();
                Pos pos = getPos();
                if(pos == null) continue;
                board.flood(pos);
                a = false;
            }
        }
        active = true;
        while(active){
            showBoard();
            Pos pos = getPos();
            if(pos == null) continue;
            board.flood(pos);
        }
    }

    public Pos getPos(){
        String val = U.get("Enter a position (#,#): ").replace(" ", "");
        Pos pos;
        try{
            pos = new Pos(Integer.valueOf(val.split(",")[0]), Integer.valueOf(val.split(",")[1]));
        }catch(Exception ex){
            return null;
        }
        if(pos.getX() >= difficulty.getBoardSize() || pos.getY() >= difficulty.getBoardSize()) return null;
        return pos;
    }

    public void showBoard(){
        String topLine = "", sideLine = "|";
        for(int i = 0; i < difficulty.getBoardSize()+2; i++)
            topLine += "-";
        U.p();
        U.p(topLine);
        for(Tile[] tiles : board.getTiles()){
            String row = "";
            for(Tile tile : tiles)
                row += tile.getChar();
            U.p(sideLine + row + sideLine);
        }
        U.p(topLine);
        U.p();
    }

}
