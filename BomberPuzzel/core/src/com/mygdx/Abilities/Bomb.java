package com.mygdx.Abilities;

import com.mygdx.Board.Board;

public class Bomb extends Ability{

    protected int time;

    public enum State {
        IDLE,
        READY,
        EXPLODED
    }

    protected State state;

    public Bomb(Board board, float x, float y, int time) 
    {
        super("bomb_1.png", board, x, y);
        this.time = time;
        state = State.READY;
    }
}
