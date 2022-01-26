package com.mygdx.Abilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Board.Board;
import com.mygdx.Board.Squares;
import com.mygdx.Player.Player;

/**
 * @author Alex Phillips, Alex Chalakov
 * A class for our bombs that the player drops.
 */
public class Bomb extends Ability
{
    protected int time;
    private Squares square;
    private float elapsedTime = 0f;
    private int explosionRange;

    private Animation<TextureRegion> bombExplosion;
    private Animation<TextureRegion> explosionCenter;
    private Animation<TextureRegion> explosionLinesVertical;
    private Animation<TextureRegion> explosionLinesHorizontal;

    /**
     * Constructor for our bombs.
     * @param board The game board
     */
    public Bomb(Board board, float y, float x, Player player, int explosionRange) 
    {
        super("core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomb/Bomb_f01.png", board, x, y, player);
        this.explosionRange = explosionRange;
        createAnimations();
    }

    public void placeBomb(float x, float y, int time)
    {
        this.time = time;
        int tileX = (int)(x / 64);
        int tileY = (int)(y / 64);

        this.square = board.getGameSquare(tileX, tileY);
        square.addBomb(this);
    }

    public void explode()
    {
        player.decreasePlacedBombs();
        
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

        Texture bombs = new Texture("core/assets/Bomb.png");

        //Bomb placement 
        for (int y = 0; y < 4; y++)
        {
            for (int x = 0; x < 4; x++) 
            {
                frames.add(new TextureRegion(bombs, 64 * x, 64 * y, 64, 64));
            }
        }
  
        bombExplosion = new Animation<>(1/3f, frames);
        frames.clear();
        
        Texture explosions = new Texture("core/assets/Explosions.png");

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

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public float getElapsedTime() {
        return elapsedTime;
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
