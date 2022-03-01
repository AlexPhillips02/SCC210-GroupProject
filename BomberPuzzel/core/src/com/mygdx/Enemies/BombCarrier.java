package com.mygdx.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Board.Board;
import com.mygdx.Abilities.Bomb;
import com.mygdx.Player.Player;

public class BombCarrier extends PlayerTrackingEnemy
{
    private int explosionRange = 2;
    private int bombsMax = 1;

    public BombCarrier(Board board, float x, float y, float movementSpeed, Player player)
    {
        super("Enemies/Spider enemy/spider(single).png", board, x, y, movementSpeed, player, 10);
        createAnimations();
        this.health = 1;
    }

    /**
     * Creates the animations of a creep (Stored within entity)
     */
    public void createAnimations()
    {
        //Walking up (Back Of bomb carrier
        Array<TextureRegion> frames = new Array<TextureRegion>();

        //Walking backwards
        Texture bombCarrierBack = new Texture("Enemies/Spider enemy/Spider(moving Back).png.png");
        for (int i = 0; i < 2; i++) 
        {
            frames.add(new TextureRegion(bombCarrierBack, 0, 56 * i, 50, 56));
        }
        walkUp = new Animation<>(1/3f, frames);
        frames.clear();

        //Walking forwards
        Texture bombCarrierFront = new Texture("Enemies/Spider enemy/Spider(moving).png");
        for (int i = 0; i < 2; i++) 
        {
            frames.add(new TextureRegion(bombCarrierFront, 0, 56 * i, 50, 56));
        }

        walkDown = new Animation<>(1/3f, frames);
        frames.clear();

        //For this enemy, the animations are the same for each direction
        walkLeft = walkDown;
        walkRight = walkDown;
    }

    public void Attack(Player player)
    {
        System.out.println("Bomb carrier hit player and placed bomb");
        if (bombsMax > 0) 
        {
            new Bomb(board, getX(), getY(), this, explosionRange);
            bombsMax--;   
        }

        this.reduceHealth();
    }
}
