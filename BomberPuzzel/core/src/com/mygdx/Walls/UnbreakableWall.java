package com.mygdx.Walls;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.Board.Tile;

public class UnbreakableWall extends Tile
{
    public UnbreakableWall()
    {
        image = new Texture("Wall.jpg");
        isWalkable = false;
    }
}