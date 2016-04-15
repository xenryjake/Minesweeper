package com.xenry.minesweeper.game;

import com.xenry.minesweeper.Minesweeper;

/**
 * Minesweeper created by Henry Jake on February 02, 2016.
 * Copyright 2016 Henry Jake.
 * All content in this file may not be used without written consent of Henry Jake.
 */
public class Timer extends Thread {

    private int currentSecs = 0;
    private boolean active = false;

    @Override
    public void run() {
        try{
            Thread.sleep(1000);
            if(active)
                Minesweeper.getGame().getFrame().updateTime(++currentSecs);
        }catch(Exception ex){}
        run();
    }

    public void reset(){
        currentSecs = 0;
    }

    public int getCurrentSecs(){
        return currentSecs;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
