package com.wayruha.fitness;

import java.util.Random;

public enum Action {
    GO_TOP(0,-1),
    GO_RIGHT(1,0),
    GO_BOTT(0,1),
    GO_LEFT(-1,0),
    PICK_UP(0,0),
    MOVE_RANDOM(true);
    private int changeX, changeY;
    private boolean randomFlag;

    Action(int changeX, int changeY) {
        this.changeX = changeX;
        this.changeY = changeY;
    }

    Action(boolean randomFlag) {
        this.randomFlag = randomFlag;
    }

    public int getChangeX() {
        if(randomFlag) return new Random().nextInt(3)-1;
        return changeX;
    }

    public int getChangeY() {
        if(randomFlag) return new Random().nextInt(3)-1;
        return changeY;
    }
}
