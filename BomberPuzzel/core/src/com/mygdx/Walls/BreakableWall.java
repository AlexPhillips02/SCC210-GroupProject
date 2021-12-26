package com.mygdx.Walls;

import com.mygdx.Board.Tile;

public abstract class BreakableWall extends Tile
{
    int health;

    public BreakableWall(String imageURL, int health)
    {
        super(imageURL);
        this.health = health;
    }

    public void reduceHealth()
    {
        health = health - 1;
    }

    public int getHealth()
    {
        return health;
    }

    public boolean isBroken()
    {
        if(health <= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
