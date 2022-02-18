package com.mygdx.Abilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Board.Board;
import com.mygdx.Board.Squares;
import com.mygdx.Player.Entity;

/**
 * @author Alex Phillips, Alex Chalakov
 * A class for our bombs that the player drops.
 */
public class Bomb extends Ability
{
    private Squares square; //Square the bomb is placed on
    private int explosionRange; //Range of the explosion (1 tile, 2 tiles etc etc)

    private Animation<TextureRegion> bombExplosion;
    private Animation<TextureRegion> explosionCenter;
    private Animation<TextureRegion> explosionLinesVertical;
    private Animation<TextureRegion> explosionLinesHorizontal;

    /**
     * Constructor for our bombs.
     * @param board The game board
     */
    public Bomb(Board board, float x, float y, Entity entity, int explosionRange) 
    {
        super("Bombing_Chap_Sprite_Set/Sprites/Bomb/Bomb_f01.png", board, x, y, entity);
        this.explosionRange = explosionRange;
        createAnimations();
        placeBomb();
    }

    /**
     * Adds a bomb to the square tile at coordinate x y
     */
    public void placeBomb()
    {
        int tileX = (int)(this.x / 64);
        int tileY = (int)(this.y / 64);

        if (tileX < 0 || tileY < 0 || tileX >= 29 || tileY >= 15)   
        {
            System.out.println("ENTITY BOMB Attempted to get gamesquare x: " + tileX + " y: " + tileY);
        }

        this.square = board.getGameSquare(tileX, tileY);
        square.addBomb(this);
    }

    /**
     * Called when the bomb needs to expload
     * Allows player to replace bomb and removes from the square (Creates explosion)
     */
    public void explode()
    {
        entity.decreasePlacedBombs();
        square.removeBomb(); //removes bomb from the grid
    }

    /**
     * Loads the 3 images which are the different phases of the bomb from the images stored in assets.
     * Stores the icon URLs in a string[].
     * Adds them to a frame in a for loop.
     * Creates animation from those frames.
     */
    public void createAnimations()
    {
        Array<TextureRegion> frames = new Array<>();

        Texture bombs = new Texture("Bomb.png");

        //Bomb placement 
        for (int y = 0; y < 4; y++)
        {
            for (int x = 0; x < 4; x++) 
            {
                frames.add(new TextureRegion(bombs, 64 * x, 64 * y, 64, 64));
            }
        }
  
        bombExplosion = new Animation<>(1/6f, frames);
        frames.clear();
        
        Texture explosions = new Texture("Explosions.png");

        //Explosion centre
        for (int x = 0; x < 7; x++) 
        {
            frames.add(new TextureRegion(explosions, 64 * x, 64 * 0, 64, 64));
        }

        explosionCenter = new Animation<>(1/10f, frames);
        frames.clear();

        //Horizontal Lines
        for (int x = 0; x < 7; x++) 
        {
            frames.add(new TextureRegion(explosions, 64 * x, 64 * 1, 64, 64));
        }

        explosionLinesHorizontal = new Animation<>(1/10f, frames);
        frames.clear();

        //Vertiacal lines
        for (int x = 0; x < 7; x++) 
        {
            TextureRegion image = new TextureRegion(explosions, 64 * x, 64 * 2, 64, 64);
            frames.add(image);
        }

        explosionLinesVertical = new Animation<>(1/10f, frames);
        frames.clear();
    }

    public Animation<TextureRegion> getAnimation()
    {
        return bombExplosion;
    }

    public int getExplosionRange() {
        return explosionRange;
    }

    public Animation<TextureRegion> getCenterAnimation() 
    {
        return explosionCenter;
    }

    public Animation<TextureRegion> getExplosionLinesVerticalAnimation() 
    {
        return explosionLinesVertical;
    }

    public Animation<TextureRegion> getExplosionLinesHorizontalAnimation() 
    {
        return explosionLinesHorizontal;
    }
}
