package com.mygdx.Player;

import com.mygdx.Board.Board;

public class Player extends Entity
{
    private PlayerController controller;
    private int health;
    private int bombsNumber;
    private int bombsMax;
    private int bombsRange;
    private boolean isAlive = true;
    private boolean isDead;

    public Player(Board board, float x, float y, float movementSpeed)
    {
        super("Player.jpg", board, x, y, movementSpeed);

        //providing a frame for the player with characteristics including bomb stash
        this.health = 3;
        this.bombsNumber = 0;
        this.bombsMax = 1;
        this.bombsRange = 1;
        this.isDead = false;

        controller = new PlayerController(this);
    }

    // Getters and Setters

    public int getHealth() {
        return health;
    }

    public int getBombsNumber() {
        return bombsNumber;
    }

    public int getBombsMax() {
        return bombsMax;
    }

    public int getBombsRange()
    {
        return bombsRange;
    }

    public void setHealth (int health) {
        this.health = health;
    }

    public void setBombsNumber (int bombsNumber) {
        this.bombsNumber = bombsNumber;
    }

    public void setBombsMax (int bombsMax) {
        this.bombsMax = bombsMax;
    }

    public void setBombsRange(int bombsRange) {
        this.bombsRange = bombsRange;
    }

    // Death function
    public void deathOccurs() {
        if (isDead == true){
            return;
        }

        this.health -= 1;
        this.isDead = true;
        isAlive = false;

        if ( health == 0){
            //GAME OVER
        }
    }

    public void checkInput() {
        controller.checkInput();
    }
}
