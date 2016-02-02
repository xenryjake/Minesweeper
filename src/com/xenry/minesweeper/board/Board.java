package com.xenry.minesweeper.board;

import java.util.Random;

/**
 * Minesweeper created by Henry Jake on February 01, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public class Board {

    private Tile[][] tiles;

    public Board(int boardSizeLength, int chance){
        tiles = new Tile[boardSizeLength][boardSizeLength];

        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[x].length; y++){
                tiles[x][y] = new Tile(x, y, new Random().nextInt(chance) == 0);
            }
        }

        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[x].length; y++){
                char nearby = '0';
                if(tiles.length > x+1 && y != 0  && tiles[x+1][y-1].isBomb()) nearby++;
                if(tiles.length > x+1 && tiles[x+1][y].isBomb()) nearby++;
                if(tiles.length > x+1 && tiles[x].length > y+1 && tiles[x+1][y+1].isBomb()) nearby++;
                if(y != 0 && tiles[x][y-1].isBomb()) nearby++;
                if(tiles[x].length > y+1 && tiles[x][y+1].isBomb()) nearby++;
                if(x != 0 && y != 0 && tiles[x-1][y-1].isBomb()) nearby++;
                if(x != 0 && tiles[x-1][y].isBomb()) nearby++;
                if(x != 0 && tiles[x].length > y+1 && tiles[x-1][y+1].isBomb()) nearby++;
                tiles[x][y].setNearbyBombs(nearby == '0' ? ' ' : nearby);
            }
        }
    }

    public Tile[][] getTiles(){
        return tiles;
    }

    public Tile[] getTiles(int y){
        return tiles[y];
    }

    public Tile getTile(int x, int y){
        return tiles[x][y];
    }

    public Tile getTile(Pos pos){
        return getTile(pos.getX(), pos.getY());
    }

    public void revealAll(){
        for(Tile[] tiles : this.tiles)
            for(Tile tile : tiles)
                tile.finalReveal();
    }

    public void reveal(int x, int y){
        getTile(x, y).reveal();
    }

    public void reveal(Pos pos){
        reveal(pos.getX(), pos.getY());
    }

    public void flood(int x, int y){
        if(x < 0) return;
        if(y < 0) return;
        if(x >= tiles.length) return;
        if(y >= tiles[x].length) return;
        if(tiles[x][y].isRevealed()) return;
        reveal(x, y);
        if(tiles[x][y].getNearbyBombs() != ' ') return;
        if(tiles[x][y].isBomb()) return;
        flood(x+1, y);
        flood(x-1, y);
        flood(x, y+1);
        flood(x, y-1);
    }

    public void flood(Pos pos){
        flood(pos.getX(), pos.getY());
    }

}
