package com.mygdx.Player;

import com.mygdx.Board.Board;

public class Player extends Entity
{
    private PlayerController controller;

    public Player(Board board, float x, float y, int movementSpeed)
    {
        super("badlogic.jpg", board, x, y, movementSpeed);
        
        controller = new PlayerController(this);
    }

    public void checkInput() {
        controller.checkInput();
    }
}
