package com.mygdx.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Board.Board;

/**
 * @author Alex Phillips
 */
public class Player extends Entity
{
    private PlayerController controller;
    private int bombsNumber;    //Current placed bombs
    private int bombsMax;       //Max amount of bombs placed
    private int bombsRange;
    private Sound damageSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Effects/damage.mp3"));

    /**
     * Constructor for the Player Model, the main character in the game.
     * @param board the Game Board
     * @param x X coordinate of the entity
     * @param y Y coordinate of the entity
     * @param movementSpeed Movement speed of the person
     */
    public Player(Board board, float x, float y, float movementSpeed)
    {
        super("Bomberman/BombermanDefault.png" , board, x, y, movementSpeed);
        createAnimations();

        //providing a frame for the player with characteristics including bomb stash
        this.health = 3;
        this.bombsNumber = 0; 
        this.bombsMax = 2;
        this.bombsRange = 2;

        controller = new PlayerController(this);
    }

    /**
     * Loads the 4 possible animations (Walking up, down, left and right) from the images stored in assets
     * Stores the icon URLs in a string[]
     * Adds them to a frame in a forloop
     * Creates animation from those frames
     */
    public void createAnimations()
    {
        //Walking up (Back of player)
        Array<TextureRegion> frames = new Array<TextureRegion>();
        Texture bombermanBack = new Texture("Bomberman/BombermanBack.png");

        for (int i = 0; i < 2; i++) 
        {
            frames.add(new TextureRegion(bombermanBack, 52 * i, 0, 52, 90));
        }

        //Number relates to speed of animation can be decreaed / sped up
        walkUp = new Animation<>(1/4f, frames);
        frames.clear();

        //Walking Down (Front of player)
        Texture bombermanFront = new Texture("Bomberman/BombermanFront.png");
        for (int i = 0; i < 2; i++) 
        {
            frames.add(new TextureRegion(bombermanFront, 52 * i, 0, 52, 90));
        }

        walkDown = new Animation<>(1/4f, frames);
        frames.clear();

        walkLeft = walkDown;
        walkRight = walkDown;

        //Standing Animation

        Texture bombermanStanding = new Texture("Bomberman/BombermanStanding.png");
        for (int i = 0; i < 2; i++) 
        {
            frames.add(new TextureRegion(bombermanStanding, 52 * i, 0, 52, 90));
        }

        standingAnimation = new Animation<>(1/3f, frames);
        frames.clear();
    }

    public void update()
    {
        increaseLastDamaged();
        controller.checkInput();
    }

    /**
     * Increasing the number of the placed Bombs
     */
    public void increasePlacedBombs()
    {
        this.bombsNumber++;
    }

    /**
     * Decreasing the number of the placed Bombs
     */
    public void decreasePlacedBombs()
    {
        this.bombsNumber--;
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

    public void setBombsNumber (int bombsNumber) {
        this.bombsNumber = bombsNumber;
    }

    public void setBombsMax (int bombsMax) {
        this.bombsMax = bombsMax;
    }

    public void setBombsRange(int bombsRange) {
        this.bombsRange = bombsRange;
    }

    public Sound getDamageSound()
    {
        return damageSound;
    }
}