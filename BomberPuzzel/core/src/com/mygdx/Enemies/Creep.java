package com.mygdx.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Board.Board;

/**
 * @author Alex Phillips
 * Creep is a type of enemy
 */
public class Creep extends Enemies
{
    /**
     * 
     * @param board Gameboard
     * @param x Starting x position
     * @param y Starting y position
     * @param movementSpeed Movement speed
     */
    public Creep(Board board, float x, float y, float movementSpeed)
    {
        super("Enemies/Creep/Alien(single).png", board, x, y, movementSpeed);
        createAnimations();
        this.health = 1;
    }

    /**
     * Creates the animations of a creep (Stored within entity)
     */
    public void createAnimations()
    {
        Array<TextureRegion> frames = new Array<TextureRegion>();
 
        //Standing Animation
        Texture creepStanding = new Texture("Enemies/Creep/Enemy Alien.png");
        
        for (int i = 0; i < 2; i++) 
        {
            frames.add(new TextureRegion(creepStanding, 52 * i, 0, 52, 76));
        }

        standingAnimation = new Animation<>(1/3f, frames);
        frames.clear();

        //For this enemy, the animations are the same for each direction
        walkLeft = standingAnimation;
        walkRight = standingAnimation;
        walkDown = standingAnimation;
        walkUp = standingAnimation;
    }
}