package com.mygdx.Board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Abilities.Bomb;

/**
 * @author Alex Phillips
 * Each square on the board
 */
public class Squares 
{
    private int x;
    private int y; 
    private Tile tile;
    private Bomb bomb;
    
    /**
     * Creates a square 
     * @param x X position of this square
     * @param y Y position of this square 
     * @param tile Holds the tile for the square (image)
     */
    public Squares(int x, int y, Tile tile)
    {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }

    //Getters and setters
    public void setTile(Tile tile)
    {
        this.tile = tile;
    }

    public Tile getTile() 
    {
        return tile;
    }

    public Squares getSquare()
    {
        return this;
    }

    public Texture getTexture()
    {
        return tile.image;
    }

    public Animation<TextureRegion> getAnimation()
    {
        return tile.getAnimation();
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void addBomb(Bomb bomb)
    {
        this.bomb = bomb;
        setAnimation(bomb.getAnimation());
    }

    public void removeBomb()
    {
        this.bomb = null;
    }

    public void removeAnimation()
    {
        tile.removeAnimation();
    }

    public void setAnimation(Animation<TextureRegion> bombExplosion) 
    {
        tile.setAnimation(bombExplosion);
    }

    public void createExplosion(Animation<TextureRegion> animation )
    {
        tile.setAnimation(animation);
    }

    public Bomb getBomb() 
    {
        return bomb;
    }
}
