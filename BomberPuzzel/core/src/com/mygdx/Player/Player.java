package com.mygdx.Player;

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
    private int health;
    private int bombsNumber;
    private int bombsMax;
    private int bombsRange;
    private boolean isAlive = true;
    private boolean isDead;

    private Animation<TextureRegion> walkDown;
    private Animation<TextureRegion> walkUp;
    private Animation<TextureRegion> walkLeft;
    private Animation<TextureRegion> walkRight;

    public Player(Board board, float x, float y, float movementSpeed)
    {
        super("core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Front/Bman_F_f00.png" , board, x, y, movementSpeed);
        createAnimations();
        setAnimation(walkUp);
        

        //providing a frame for the player with characteristics including bomb stash
        this.health = 3;
        this.bombsNumber = 0;
        this.bombsMax = 1;
        this.bombsRange = 1;
        this.isDead = false;

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
        //Walking downwards
        String[] downIcons = {"core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Front/Bman_F_f00.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Front/Bman_F_f01.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Front/Bman_F_f02.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Front/Bman_F_f03.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Front/Bman_F_f04.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Front/Bman_F_f05.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Front/Bman_F_f06.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Front/Bman_F_f07.png"};

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 0; i < downIcons.length; i++) 
        {
            frames.add(new TextureRegion(new Texture(downIcons[i])));
        }

        //Number relates to speed of animation can be decreaed / sped up
        walkDown = new Animation<>(0.2f, frames);
        frames.clear();

        //Walking Upwards (Back of player)
        String[] upIcons = {"core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Back/Bman_B_f00.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Back/Bman_B_f01.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Back/Bman_B_f02.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Back/Bman_B_f03.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Back/Bman_B_f04.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Back/Bman_B_f05.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Back/Bman_B_f06.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Back/Bman_B_f07.png"};

        for (int i = 0; i < upIcons.length; i++) 
        {
            frames.add(new TextureRegion(new Texture(upIcons[i])));
        }

        walkUp = new Animation<>(0.2f, frames);
        frames.clear();

        //Walking to the Right
        String[] rightIcons = {"core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Right/Bman_F_f00.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Right/Bman_F_f01.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Right/Bman_F_f02.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Right/Bman_F_f03.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Right/Bman_F_f04.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Right/Bman_F_f05.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Right/Bman_F_f06.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Right/Bman_F_f07.png"};

        for (int i = 0; i < rightIcons.length; i++) 
        {
            frames.add(new TextureRegion(new Texture(rightIcons[i])));
        }

        walkRight = new Animation<>(0.2f, frames);
        frames.clear();

        //Walking to the left
        String[] leftIcons = {"core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Left/Bman_F_f00.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Left/Bman_F_f01.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Left/Bman_F_f02.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Left/Bman_F_f03.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Left/Bman_F_f04.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Left/Bman_F_f05.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Left/Bman_F_f06.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomberman/Left/Bman_F_f07.png"};

        for (int i = 0; i < leftIcons.length; i++) 
        {
            frames.add(new TextureRegion(new Texture(leftIcons[i])));
        }

        walkLeft = new Animation<>(1/15f, frames);
    }

    /**
     * Called within player controller, sets the animation if walking in a direction
     * @param direction Direction of the player (If null standing still)
     */
    public void setAnimationDirection(String direction)
    {
        switch (direction) {
            case "UP":
                setAnimation(walkUp);
                break;
            case "DOWN":
                setAnimation(walkDown);
                break;
            case "LEFT":
                setAnimation(walkLeft);
                break;
            case "RIGHT":
                setAnimation(walkRight);
                break;
        
            default:
                setAnimation(null);
                break;
        }
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
